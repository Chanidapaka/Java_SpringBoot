package sit.int202.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    //เมื่อเรียกด้วย/home ก็จะเข้าตรงนี้
    @RequestMapping("/home")
    public String homePage(){
        return "home";
    };
}
