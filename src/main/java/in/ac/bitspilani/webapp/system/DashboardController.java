package in.ac.bitspilani.webapp.system;


import in.ac.bitspilani.webapp.category.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String dashboard(Map<String, Object> model) {
        Collection<Category> set = new HashSet<>();
        Category category = new Category();
        category.setName("category 1");
        set.add(category);
        model.put("selections",set);
        return "dashboard/dashboard";
    }

}
