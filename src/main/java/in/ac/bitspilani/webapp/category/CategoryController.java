package in.ac.bitspilani.webapp.category;

import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.item.ItemRepository;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user/{userId}")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;


    public CategoryController(CategoryRepository categoryService, ItemRepository items, UserRepository userRepository) {
        this.categoryRepository = categoryService;
        this.itemRepository = items;
        this.userRepository = userRepository;
    }


   @ModelAttribute("user")
   public User findUser(@PathVariable("userId") int userId) {
       return this.userRepository.findById(userId);
   }

    @InitBinder("user")
    public void initUserBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("category")
    public void initCategoryBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new CategoryValidator());
    }


    @GetMapping("categories/{categoryId}")
    public String showCategory(@PathVariable("categoryId") int categoryId, Map<String, Object> map) {
        Category category = categoryRepository.findById(categoryId);
        map.put("selections", category.getItems());
        map.put("category", category);
        return "dashboard/itemDetails";
    }

    @GetMapping("/categories/{categoryId}/edit")
    public String showEditDetailsFormCategory(@PathVariable("categoryId") int categoryId, ModelMap model) {
        Category category = categoryRepository.findById(categoryId);
        model.put("category", category);
        return "dashboard/createOrUpdateCategory";
    }

    @PostMapping("/categories/{categoryId}/edit")
    public String addCategory(@Valid Category category, BindingResult result, User user, ModelMap model) {
        if (result.hasErrors()) {
            category.setUser(user);
            model.put("category", category);
            return "dashboard/createOrUpdateCategory";
        }
        else {
            category.setUser(user);
            user.addCategory(category);
            this.categoryRepository.save(category);
            return "redirect:/dashboard";
        }
    }

    @GetMapping("/category/new")
    public String addingNewCategory(User user, ModelMap model) {
        Category category = new Category();
        user.addCategory(category);
        category.setUser(user);
        model.put("category", category);
        return "dashboard/createOrUpdateCategory";
    }


    @PostMapping("/category/new")
    public String processCreationFormCategory(User user, @Valid Category category, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(category.getName()) && category.isNew() && user.getCategory(category.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        category.setUser(user);
        user.addCategory(category);
        if (result.hasErrors()) {
            model.put("category", category);
            return "dashboard/createOrUpdateCategory";
        }
        else {
            this.categoryRepository.save(category);
            return "redirect:/dashboard";
        }
    }

/*
    @Transactional
*/
    @GetMapping("/categories/{categoryId}/delete")
    public String removeCategory(@PathVariable("categoryId") int categoryId) {
        categoryRepository.deleteById(categoryId);
        return "redirect:/dashboard";
    }
}

