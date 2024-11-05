package sit.int202.demo.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sit.int202.demo.entities.Customer;
import sit.int202.demo.repositories.CustomerRepository;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @GetMapping("/all")
    public String allCustomers(ModelMap model) {
        List<Customer> customerList = repository.findAll();
        model.addAttribute("customers",customerList);
        return "customer_list";
    }
}
