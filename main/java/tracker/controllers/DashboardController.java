package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tracker.models.AjaxResponses.TaskAnalitycs;
import tracker.services.TaskService;

import java.util.ArrayList;

@Controller
public class DashboardController extends MainController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        return "dashboard";
    }

    @RequestMapping(value = "/dashboardProjectsTaskGroups", method = RequestMethod.GET)
    public @ResponseBody
    TaskAnalitycs dashboardProjectsTaskGroups(@RequestParam String projectId) {

        System.out.println(projectId);
        TaskAnalitycs taskAnalitycs = new TaskAnalitycs();


        taskAnalitycs.setDoneTasks(45);
        taskAnalitycs.setInProgressTasks(145);
        taskAnalitycs.setNewTasks(5);

        return taskAnalitycs;
    }
}
