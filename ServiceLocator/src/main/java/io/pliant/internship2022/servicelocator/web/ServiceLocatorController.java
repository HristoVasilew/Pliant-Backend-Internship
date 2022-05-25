package io.pliant.internship2022.servicelocator.web;

import io.pliant.internship2022.servicelocator.model.entity.ServiceEntity;
import io.pliant.internship2022.servicelocator.service.locator.ServiceLocator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ServiceLocatorController {
    private final ServiceLocator serviceLocator;


    public ServiceLocatorController(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @GetMapping()
    public String index(Model model){

        List<ServiceEntity> ships = shipService.findAllShips();

        model.addAttribute("ships",ships);

        model.addAttribute("users", users);

        return "home";

    }
}
