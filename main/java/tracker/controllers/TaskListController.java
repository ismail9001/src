package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tracker.models.Project;
import tracker.models.Task;
import tracker.models.TaskStatus;
import tracker.repositories.TaskStatusRepository;
import tracker.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TaskListController extends MainController{

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private NotificationService notifyService;
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @RequestMapping(value = "/{project}/tasks", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index(@PathVariable ("project")Project project) {
        ModelAndView modelAndView = new ModelAndView();
        List<Task> findAll = taskService.findAll(project);
        List<TaskStatus> findAllStatus = taskStatusService.findAll();
        modelAndView.addObject("findAllTasks", findAll);
        modelAndView.addObject("findAllStatus", findAllStatus);
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.setWatchedProject(userService.findByEmail(currentUser), project);
        modelAndView.addObject("watchedProject",  userService.findByEmail(currentUser).getWatched_project());
        modelAndView.addObject("user", userService.findByEmail(currentUser));
        modelAndView.setViewName("tasks");
        return modelAndView;
    }

    @RequestMapping(value = "/{project}/tasks", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView createNewProject(@Valid Task task, BindingResult bindingResult, @PathVariable ("project")Project project ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("project", project);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("tasks");
        } else {
            taskService.create(task, project);
            notifyService.addInfoMessage("Task has been added succesfully");
            modelAndView.addObject("task", new Task());
            modelAndView.setViewName("tasks");
            List<Task> findAll = taskService.findAll(project);
            List<TaskStatus> findAllStatus = taskStatusService.findAll();
            modelAndView.addObject("findAllStatus", findAllStatus);
            modelAndView.addObject("findAllTasks", findAll);
        }
        return modelAndView;
    }
    @RequestMapping(value = "/{project}/tasks/remove", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView removeT(@RequestParam("task") int taskId, @PathVariable ("project")Project project ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("project", project);
        taskService.deleteById(taskId);
        notifyService.addInfoMessage("Task has been removed succesfully");
        modelAndView.setViewName("tasks");
        List<TaskStatus> findAllStatus = taskStatusService.findAll();
        List<Task> findAll = taskService.findAll(project);
        modelAndView.addObject("findAllTasks", findAll);
        modelAndView.addObject("findAllStatus", findAllStatus);
        return modelAndView;
    }

    @RequestMapping(value = "/{project}/tasks/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editT(HttpServletRequest request, @RequestParam("task") int taskId,
                              @RequestParam("TaskStatus") String taskStatus, @PathVariable ("project")Project project) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("project", project);
        taskService.edit(taskService.findById(taskId), taskStatusRepository.findByStatus(taskStatus));
        notifyService.addInfoMessage("Task has been edited succesfully");
        modelAndView.setViewName("tasks");
        List<Task> findAll = taskService.findAll(project);
        modelAndView.addObject("findAllTasks", findAll);
        List<TaskStatus> findAllStatus = taskStatusService.findAll();
        modelAndView.addObject("findAllStatus", findAllStatus);
        return modelAndView;
    }
}