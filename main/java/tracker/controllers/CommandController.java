package tracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommandController  extends MainController{

    @RequestMapping("/command")
    public String index(Model model) {
        return "command";
    }
}