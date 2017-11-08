package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import tracker.models.Project;
import tracker.services.ProjectService;
import tracker.services.UserService;

import java.util.List;

@ControllerAdvice
public class ProjectListControllerAdvice {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @ModelAttribute
    public String projects (Model model) {
        List<Project> findAll = projectService.findAll(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("findAll", findAll);
        return "projects";
    }
}