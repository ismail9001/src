package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tracker.models.Project;
import tracker.models.AjaxResponses.TasksListGroupByStatus;
import tracker.models.User;
import tracker.services.ProjectService;
import tracker.services.TaskService;
import tracker.services.UserService;

import java.util.HashMap;
import java.util.List;

@Controller
public class DashboardController extends MainController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public Model dashboard(Model model) {
        long zero = 0;
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Project project = user.getWatched_project();
        List<TasksListGroupByStatus> test = taskService.groupByStatus(project);
        HashMap<String, Long> hashMap= new HashMap<String, Long>();
        for (TasksListGroupByStatus taskGroups : test) {
            hashMap.put(taskGroups.getStatus(), taskGroups.getCount());
        }
        if(hashMap.get("New") == null) {
            hashMap.put("New", zero);
        }
        if(hashMap.get("In work") == null) {
            hashMap.put("In work", zero);
        }
        if(hashMap.get("Done") == null) {
            hashMap.put("Done", zero);
        }
        model.addAttribute("newTasks", hashMap.get("New"));
        model.addAttribute("inWorkTasks", hashMap.get("In work"));
        model.addAttribute("doneTasks", hashMap.get("Done"));
        return model;
    }

    @RequestMapping(value = "/dashboardProjectsTaskGroups", method = RequestMethod.GET)
    public @ResponseBody
    HashMap<String, Long>  dashboardProjectsTaskGroups(@RequestParam String projectId) {
        HashMap<String, Long> hashMap= new HashMap<String, Long>();
        int id;
        try {
            id = Integer.parseInt(projectId);
            Project project = projectService.findOne(id);
            List<TasksListGroupByStatus> test = taskService.groupByStatus(project);
            for (TasksListGroupByStatus taskGroups : test) {
                hashMap.put(taskGroups.getStatus(), taskGroups.getCount());
            }
        }
        catch (Exception e) {
            System.out.println("Error sorry");
        }

        return hashMap;
    }
}
