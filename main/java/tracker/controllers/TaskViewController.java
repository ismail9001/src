package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tracker.models.Task;
import tracker.services.NotificationService;
import tracker.services.TaskService;

@Controller
public class TaskViewController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private NotificationService notifyService;

    @RequestMapping("/tasks/view/{id}")
    public String view(@PathVariable("id") int id, Model model) {
        Task task = taskService.findById(id);
        if (task == null) {
            notifyService.addErrorMessage("Cannot find task #" + id);
            return "redirect:/";
        }
        model.addAttribute("task", task);
        return "tasks/view";
    }
}