package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tracker.services.TaskService;

@Controller
public class DashboardController extends MainController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        return "dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(@RequestParam("rate") String projectId, Model model) {
        return "dashboard";
    }
}
