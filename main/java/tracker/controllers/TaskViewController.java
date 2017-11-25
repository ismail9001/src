package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tracker.models.AjaxResponses.TaskView;
import tracker.models.Task;
import tracker.services.TaskService;


@Controller
public class TaskViewController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/task/view", method = RequestMethod.GET)
    public @ResponseBody
    TaskView getTask(@RequestParam String taskId) {

        int id;
        TaskView result = new TaskView();
        Task task;

        try {
            id = Integer.parseInt(taskId);
        } catch (Exception e) {
            result.setMsg("Invalid task number");
            return result;
        }

        task = taskService.findById(id);

        if (task == null) {
            result.setMsg("Task not found");
            return result;
        }
        result.setUser(task.getAuthor().getUsername());
        result.setTask(task);
        return result;
    }
}