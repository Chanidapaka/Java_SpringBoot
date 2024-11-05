package sit.int202.demo.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//สร้างอันนี้อันดับแรก
@Controller
public class AppController {
    //เมื่อเรียกด้วย/home ก็จะเข้าตรงนี้
    @RequestMapping("/home")
    public String homePage(){
        return "home";
    };

    @GetMapping("/theme")
    public String selectTheme(){
        return "theme";
    };

    @PostMapping("/theme")
    public String applyTheme(@RequestParam String theme, HttpServletResponse response){
        Cookie cookie = new Cookie("theme", theme);
        cookie.setMaxAge(60*60*8);
        response.addCookie(cookie);
        return "home";

        cookie  = new Cookie("fg", theme.equalsIgnoreCase("cadetblue")?"yeellow":"black");
        cookie.setMaxAge(60*60*8);
        response.addCookie(cookie);
        return "home";
    };

}
