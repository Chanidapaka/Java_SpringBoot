package sit.int202.demo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sit.int202.demo.entities.Office;
import sit.int202.demo.services.OfficeService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/offices") //เรียก /offices จะเข้ามาใน file นี้
public class OfficeController {
    @Autowired
    private OfficeService service;

    @GetMapping("/all")
    public String allOffices(ModelMap model) {
        List<Office> officeList = service.getAllOffices();
        model.addAttribute("offices",officeList);
        return "offices/office_list";
    }

    @GetMapping("")
    public String getOfficeById(@RequestParam String officeCode, ModelMap model){
        Office office = service.getOfficeByCode(officeCode);
        model.addAttribute("office", office);
        return "offices/office_detail";
    }

    @GetMapping("/delete")
    public String deleteOfficeById(@RequestParam String officeCode, ModelMap model){
        Office office = service.deleteOffice(officeCode);
        model.addAttribute("office", office);
        model.addAttribute("message", "Office deleted successfully");
        return "offices/office_detail";
    }

    @GetMapping("/create")
    public String createForm(){
        return "offices/create_form";
    }

    @PostMapping("/create")
    public void createOffice(Office office, HttpServletResponse response) throws IOException {
        service.createOffice(office);
        response.sendRedirect("/offices/all");
    }

    @GetMapping("/update")
    public String updateFrom(@RequestParam String officeCode, ModelMap model){
        Office office = service.getOfficeByCode(officeCode);
        model.addAttribute("office",office);
        return "offices/update_form";
    }

    @PostMapping("/update")
    public void updateOffice(Office office, HttpServletResponse response) throws IOException {
        service.updateOffice(office);
        response.sendRedirect("/offices/all");
    }
}
