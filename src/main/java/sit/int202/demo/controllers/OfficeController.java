package sit.int202.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sit.int202.demo.entities.Office;
import sit.int202.demo.repositories.OfficeRepository;
import sit.int202.demo.services.OfficeService;
import java.util.List;
//@Controller@RequestMapping("/offices") //รับรีเควสที่เข้ามาใน officespublic class OfficeController {
//    private OfficeRepository repository;
//    private OfficeService service;
//    @GetMapping("/all") //รับเฉพาะ get
//    public String allOffices(Model model) {
//    List<Office> officeList = repository.findAll();
//    model.addAttribute("offices", officeList);
//    return "offices_list";    }
//    @GetMapping("/delete") // เมธอดที่จะรับคำขอ GET ที่ /offices
//    public String deleteOfficeById(@RequestParam String officeCode, Model model) {
// ดึงข้อมูลออฟฟิศตาม officeCode        Office office = service.deleteOffice(officeCode);
// model.addAttribute("office", office);
// model.addAttribute("message", office.getOfficeCode());
// return "office_details";    }
// @GetMapping("") // เมธอดที่จะรับคำขอ GET ที่ /offices
// public String getOfficeById(@RequestParam String officeCode, Model model) {
// ดึงข้อมูลออฟฟิศตาม officeCode
// Office office = service.getOffice(officeCode);
// model.addAttribute("office", office);
// return "office_details";    }}