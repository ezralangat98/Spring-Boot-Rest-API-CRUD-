package org.flemics.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/")
    public String greeting(){
        return "Hurray!";
    }
}
