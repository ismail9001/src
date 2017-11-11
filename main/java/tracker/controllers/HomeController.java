package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tracker.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @RequestMapping("/")

    public String index(Model model) {
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByEmail(currentUser));
        return "index";
    }
}