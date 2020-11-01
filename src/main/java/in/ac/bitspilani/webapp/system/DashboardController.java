package in.ac.bitspilani.webapp.system;


import in.ac.bitspilani.webapp.category.CategoryRepository;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;

@Controller
public class DashboardController {

    final CategoryRepository categoryRepository;
    final UserRepository userRepository;

    public DashboardController(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Map<String, Object> model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(principal.getName());
        model.put("user", user);
        model.put("selections", user.getCategories());
        return "dashboard/dashboard";
    }
}
