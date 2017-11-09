package tracker.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tracker.models.User;
import tracker.services.EmailService;
import tracker.services.NotificationService;
import tracker.services.UserService;

@Controller
public class PasswordController{

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private NotificationService notifyService;

    // Display forgotPassword page
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }

    // Process form submission from forgotPassword page
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {

        // Lookup user in database by e-mail
        User user = userService.findByEmail(userEmail);

        if (user == null) {
            notifyService.addErrorMessage("We didn't find an account for that e-mail address.");
            //modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
        } else {

            // Generate random 36-character string token for reset password
            user.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            userService.saveUser(user);

            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/resetPassword?token=" + user.getResetToken());

            emailService.sendEmail(passwordResetEmail);
            // Add success message to view
            notifyService.addInfoMessage("A password reset link has been sent to " + userEmail);
            //modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        }
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    // Display form to reset password
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        Optional<User> user = userService.findUserByResetToken(token);
        if (user.isPresent()) { // Token found in DB
            modelAndView.addObject("resetToken", token);
        } else { // Token not found in DB
            notifyService.addErrorMessage("Oops!  This is an invalid password reset link.");
            //modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
        }

        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    // Process reset password form
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
        // Find the user associated with the reset token
        Optional<User> user = userService.findUserByResetToken(requestParams.get("token"));
        // This should always be non-null but we check just in case
        if (user.isPresent()) {

            User resetUser = user.get();
            // Set new password
            resetUser.setPassword(requestParams.get("password"));
            // Set the reset token to null so it cannot be used again
            resetUser.setResetToken(null);
            // Save user
            userService.saveUser(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            notifyService.addInfoMessage("You have successfully reset your password.  You may now login.");
            //redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("redirect:login");
            return modelAndView;

        } else {
            notifyService.addErrorMessage("Oops!  This is an invalid password reset link.");
            //modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("resetPassword");
        }

        return modelAndView;
    }
    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        ModelAndView modelAndView = new ModelAndView("redirect:login");
        notifyService.addErrorMessage("No redirect token.");
        //modelAndView.addObject("errorMessage", "Ooooo fuck.");
        return modelAndView;
    }
}
