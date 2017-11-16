package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tracker.models.Project;
import tracker.models.Task;
import tracker.services.TaskService;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/{project}/dashboard")
    public String account(@PathVariable("id") Project project, Model model) {
        List<Task> findAllTasks = taskService.findAll(project);
        model.addAttribute(findAllTasks);
        return "/dashboard";
    }
}
