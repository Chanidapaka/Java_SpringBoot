package sit.int202.demo.controllers;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sit.int202.demo.entities.Office;
import sit.int202.demo.services.OfficeService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/offices")
public class OfficeController {
    @Autowired
    private  OfficeService repository;

    @GetMapping("/all")
    public String getAllOffices(Model model) {
        List<Office> officeList = repository.findAll();
        model.addAttribute("offices", officeList);
        return "office_list";
    }

    @GetMapping("")
    public String getOfficeById(@RequestParam String officeCode, ModelMap modelMap) {
        Office office = OfficeService.getOfficeByCode(officeCode);
        modelMap.addAttribute("office", office);
        return "office_detail";
    }

    @GetMapping("/delete-office")
    public String deleteOffice(@RequestParam String officeCode, ModelMap modelMap) {
        Office office = OfficeService.deleteOffice(officeCode);
        modelMap.addAttribute("office", office);
        return "office_detail";
    }

    @PostMapping("/create-office")
    public String createOffice(Office office, ModelMap modelMap) {
        Office newOffice = OfficeService.createOffice(office);
        modelMap.addAttribute("office", newOffice);
        return "office_detail";
    }
    @PostMapping("/update-office")
    public void updateOffice(HttpServletRequest request
            , ModelMap modelMap, HttpServletResponse response) throws IOException {
        Office office = new Office();
        office.setOfficeCode(request.getParameter("officeCode"));
        office.setCity(request.getParameter("city"));
        office.setCountry(request.getParameter("country"));
        office.setPhone(request.getParameter("phone"));
        office.setPostalCode(request.getParameter("postalCode"));
        office.setTerritory(request.getParameter("territory"));
        office.setAddressLine1(request.getParameter("addressLine1"));
        Office newOffice = OfficeService.updateOffice(office);
        modelMap.addAttribute("office", newOffice);
        response.sendRedirect("/offices/all");
        return ;
        //return "office_detail";
    }

    @GetMapping("/office_form")
    public String getOfficeForm() {
        return "office_form";
    }
    @GetMapping("/update_form")
    public String getOfficeForm(@RequestParam String officeCode, ModelMap modelMap) {
        Office office = OfficeService.getOfficeByCode(officeCode);
        modelMap.addAttribute("office", office);
        return "update_form";
    }
}
