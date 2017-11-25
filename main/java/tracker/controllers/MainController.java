package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import tracker.models.Project;
import tracker.models.User;
import tracker.services.ProjectService;
import tracker.services.UserService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @ModelAttribute(value = "findAllProjects")
    public void mainObjects (Model model) {
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user != null) {
            List<Project> findAll = projectService.findAll(user);
            model.addAttribute("findAllProjects", findAll);
            model.addAttribute("watchedProject", user.getWatched_project());
        }
    }
}
