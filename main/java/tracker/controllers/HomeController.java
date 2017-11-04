package tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.RequestMapping;
        import tracker.models.Task;
        import tracker.services.TaskService;
import tracker.services.UserService;

import java.util.List;
        import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    //вывод на главной странице списка из 3 и 5 последних задач
    public String index(Model model) {
        List<Task> latest5Tasks = taskService.findLatest5();
        model.addAttribute("latest5tasks", latest5Tasks);
        List<Task> latest3Tasks = latest5Tasks.stream()
                .limit(3).collect(Collectors.toList());
        model.addAttribute("latest3tasks", latest3Tasks);
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.findByEmail(currentUser));
        return "index";
    }
}