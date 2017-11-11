package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tracker.models.Project;
import tracker.services.NotificationService;
import tracker.services.ProjectService;
import tracker.services.UserService;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectListController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notifyService;

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projects (Model model) {
        List<Project> findAll = projectService.findAll(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
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
            notifyService.addInfoMessage("Project has been added succesfully");
            //modelAndView.addObject("successMessage", "Project has been added succesfully");
            modelAndView.addObject("project", new Project());
            modelAndView.setViewName("projects");
            List<Project> findAll = projectService.findAll(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
            modelAndView.addObject("findAll", findAll);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/projects/remove", method = RequestMethod.POST)
    public String removeP(@RequestParam("project") int projectId) {
        projectService.deleteById(projectId);
        notifyService.addInfoMessage("Project has been removed succesfully");
        return "redirect:/projects";
    }
    @RequestMapping(value = "/projects/edit", method = RequestMethod.POST)
    public String editP(@RequestParam("project") Project project, String projectName, String mainURL, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        {
            notifyService.addErrorMessage("Project couldn't be edited");
            return "redirect:/projects";
        }
        else {
            System.out.println(project.getId() + " " + projectName + " " + mainURL);
            projectService.edit(project, projectName, mainURL);
            notifyService.addInfoMessage("Project has been edited");
        }
        return "redirect:/projects";
    }
}