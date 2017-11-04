package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tracker.models.Project;
import tracker.services.ProjectService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectListController {
    @Autowired
    private ProjectService projectService;
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projects (Model model) {
        List<Project> findAll = projectService.findAll();

        model.addAttribute("findAll", findAll);
        return "projects";
    }
    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public ModelAndView createNewProject(@Valid Project project, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("projects");
        } else {
            projectService.create(project);
            modelAndView.addObject("successMessage", "Project has been added succesfully");
            modelAndView.addObject("project", new Project());
            modelAndView.setViewName("projects");
            List<Project> findAll = projectService.findAll();
            modelAndView.addObject("findAll", findAll);
        }
        return modelAndView;
    }
}