package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tracker.models.Project;
import tracker.models.User;
import tracker.services.ProjectService;
import tracker.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/account", method = RequestMethod.GET)

    public ModelAndView account() {
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("account");
        return modelAndView;
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)

    public ModelAndView accountEdit(@RequestParam("account") String user1, HttpServletRequest request) {
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("account");

        String email = request.getParameter("email");
        if (!email.isEmpty()) {
            user.setEmail(email);
        }
        String username = request.getParameter("name");
        if (!username.isEmpty()) {
            user.setUsername(username);
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.isEmpty() && !confirmPassword.isEmpty()) {
            if(!newPassword.equals(confirmPassword)) {
                modelAndView.addObject("errorMessage", "Пароли не совпадают");
                return modelAndView;
            }
            user.setPassword(newPassword);
            userService.saveUser(user);
            return modelAndView;
        }

        modelAndView.addObject("user", user);
        return modelAndView;
    }
}