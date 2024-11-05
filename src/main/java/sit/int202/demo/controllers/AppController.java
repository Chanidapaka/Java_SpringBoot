package sit.int202.demo.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//สร้างอันนี้อันดับแรก
@Controller
public class AppController {
    //เมื่อเรียกด้วย/home ก็จะเข้าตรงนี้
        @RequestMapping("/home")
        public String homePage(@CookieValue("bg") String bg,
                               @CookieValue("fg") String fg,
                               Model model) {
            model.addAttribute("bg", bg);
            model.addAttribute("fg", fg);
            return "home"; //ต้องมี home.html อยู่ใน templete
        }

        @GetMapping("/theme")
        public String selectTheme() {
            return "theme";
        }

        @PostMapping("/theme")
        public String applyTheme(@RequestParam String theme, HttpServletResponse response) {
            Cookie cookie = new Cookie("bg", theme);
            cookie.setMaxAge(60 * 60 * 24 * 8);
            response.addCookie(cookie);

            cookie = new Cookie("fg", theme.equalsIgnoreCase("cadetblue") ? "yellow" : "black");
            cookie.setMaxAge(60 * 60 * 24 * 8);
            response.addCookie(cookie);
            return "redirect:/home";
        }
    }
