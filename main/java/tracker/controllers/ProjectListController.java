package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tracker.models.Project;
import tracker.models.TaskStatus;
import tracker.models.User;
import tracker.services.NotificationService;
import tracker.services.ProjectService;
import tracker.services.TaskStatusService;
import tracker.services.UserService;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectListController extends MainController{

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notifyService;

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String projects (Model model) {
        return "projects";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public String createNewProject(@Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/projects";
        } else {
            User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            projectService.create(project);
            userService.setWatchedProject(user, project);
            notifyService.addInfoMessage("Project has been added succesfully");
            //modelAndView.addObject("successMessage", "Project has been added succesfully"); Для примера на будущее
        }
        return "redirect:/projects";
    }
    @RequestMapping(value = "/projects/remove", method = RequestMethod.POST)
    public String removeP(@RequestParam("project") int projectId, Model model) {
        Project project = projectService.findOne(projectId);
        projectService.deleteById(projectId);
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Project> findAll = projectService.findAll(user);
        //проверка для отображения проекта в шапке быстрого доступа
        if (user.getWatched_project().getId() == project.getId() && findAll.size() != 0) {
                userService.setWatchedProject(user, findAll.get(0));
            }
        notifyService.addInfoMessage("Project has been removed succesfully");
        model.addAttribute("findAll", findAll);
        return "redirect:/projects";
    }
    @RequestMapping(value = "/projects/edit", method = RequestMethod.POST)
    public String editP(@RequestParam("project") Project project, String projectName, String mainURL) {
        projectService.edit(project, projectName, mainURL);
        notifyService.addInfoMessage("Project has been edited");
        return "redirect:/projects";
    }
}