package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tracker.models.Task;
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
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String index(Model model) {
        List<Task> findAll = taskService.findAll();
        model.addAttribute("findAllTasks", findAll);
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByEmail(currentUser));
        return "tasks";
    }
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public ModelAndView createNewProject(@Valid Task task, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("tasks");
        } else {
            taskService.create(task);
            modelAndView.addObject("successMessage", "Task has been added succesfully");
            modelAndView.addObject("task", new Task());
            modelAndView.setViewName("tasks");
            List<Task> findAll = taskService.findAll();
            System.out.println(findAll);
            modelAndView.addObject("findAllTasks", findAll);
        }
        return modelAndView;
    }
}