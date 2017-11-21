package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tracker.models.Project;
import tracker.models.Task;
import tracker.models.User;
import tracker.services.ProjectService;
import tracker.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class AccountController extends MainController{

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(Model model) {
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ModelAndView accountEdit(HttpServletRequest request) {

        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String username = request.getParameter("username");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("account");

        if (!email.isEmpty()) {
            user.setEmail(email);
        }
        if (!username.isEmpty()) {
            user.setUsername(username);
        }
        if (!newPassword.isEmpty() && !confirmPassword.isEmpty()) {
            if (currentPassword.length() < 8) {
                modelAndView.addObject("errorMessage", "Password must be at least 8 characters");
                return modelAndView;
            }
            if (currentPassword.isEmpty()) {
                modelAndView.addObject("errorMessage", "Введите текущий пароль");
                return modelAndView;
            }
            if (bCryptPasswordEncoder.matches(currentPassword, user.getPassword())) {
                if (!newPassword.equals(confirmPassword)) {
                    modelAndView.addObject("errorMessage", "Пароли не совпадают");
                    return modelAndView;
                }
                user.setPassword(newPassword);
                userService.saveUser(user);
            }
            else {
                modelAndView.addObject("errorMessage", "Введенный пароль не совпадает");
                return modelAndView;
            }
        }
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}