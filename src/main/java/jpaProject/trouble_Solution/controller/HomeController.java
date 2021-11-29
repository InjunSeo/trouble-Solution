package jpaProject.trouble_Solution.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/hello")
    public String index(){
        return "hello";
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("data", "Hello, GENIE");
        log.info("home Controller");
        return "home";
    }
}
