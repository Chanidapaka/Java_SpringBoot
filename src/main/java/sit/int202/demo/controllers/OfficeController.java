package sit.int202.demo.controllers;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final OfficeService repository;

    public OfficeController(OfficeService repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public String getOfficeById(@RequestParam String officeCode, Model model) {
        Office office = OfficeService.getOffice(officeCode);
        model.addAttribute("office", office);
        return "office_details";
    }
    @GetMapping("/all")
    public String getAllOffices(Model model) {
        List<Office> officeList = repository.getAllOffices();
        model.addAttribute("offices", officeList);
        return "office_list";
    }

    @GetMapping("/delete-office")
    public String deleteOfficeById(@RequestParam String officeCode, Model model) {
        Office office = OfficeService.deleteOffice(officeCode);
        model.addAttribute("office", office);
        model.addAttribute("message", "Office deleted successfully");
        return "office_detail";
    }

    @GetMapping("/create")
    public String createForm() {
        return "create_form";
    }

    @PostMapping("/create")
    public String createOffice(Office office, HttpServletResponse response) throws IOException {
        repository.addOffice(office);
        response.sendRedirect("/offices/all");
        return "create_form";
    }

    @GetMapping("/update")
    public String getOfficeForm(@RequestParam String officeCode, Model model) {
        Office office = repository.getOffice(officeCode);
        model.addAttribute("office", office);
        return "update_form";
    }
    @PostMapping("/update")
    public void updateOffice(Office office, HttpServletResponse response) throws IOException {
        repository.updateOffice(office) {
            response.sendRedirect("/office/all");
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
            return;
            //return "office_detail";
        }


    }
}
