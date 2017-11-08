package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tracker.models.Project;
import tracker.models.Task;
import tracker.services.ProjectService;
import tracker.services.TaskService;
import tracker.services.UserService;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TaskListController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @RequestMapping(value = "/tasks/{project}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index(@PathVariable Project project) {
        ModelAndView modelAndView = new ModelAndView();
        List<Task> findAll = taskService.findAll(project);
        modelAndView.addObject("findAllTasks", findAll);
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        modelAndView.addObject("user", userService.findByEmail(currentUser));
        modelAndView.setViewName("tasks");
        return modelAndView;
    }
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createNewProject(@Valid Task task, BindingResult bindingResult, @PathVariable ("id") int id ) {
        Project project = projectService.findOne(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("tasks");
        } else {
            taskService.create(task, project);
            modelAndView.addObject("successMessage", "Task has been added succesfully");
            modelAndView.addObject("task", new Task());
            modelAndView.setViewName("tasks");
            List<Task> findAll = taskService.findAll(project);
            modelAndView.addObject("findAllTasks", findAll);
        }
        return modelAndView;
    }
}