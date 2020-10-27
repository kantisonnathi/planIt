package in.ac.bitspilani.webapp.system;


import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.category.CategoryRepository;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    final CategoryRepository categoryRepository;
    final UserRepository userRepository;

    public DashboardController(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String dashboard(Map<String, Object> model) {
        /*Collection<Category> set = new HashSet<>();
        Category category = new Category();
        category.setName("category 1");
        category.setId(1);
        set.add(category);


//        List<Category> categoryList = categoryRepository.findCategoriesByUser(1);*/

        User user = userRepository.findById(1);
        model.put("selections", user.getListOfCategories());

        return "dashboard/dashboard";
    }

}
