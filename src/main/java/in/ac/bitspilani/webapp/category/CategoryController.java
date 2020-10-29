package in.ac.bitspilani.webapp.category;

import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;


    public CategoryController(CategoryRepository categoryService, ItemRepository items) {
        this.categoryRepository = categoryService;
        this.itemRepository = items;
    }

    @GetMapping("categories/{categoryId}")
    public ModelAndView showCategory(@PathVariable("categoryId") int categoryId) {
        ModelAndView mav = new ModelAndView("dashboard/categoryDetails");
        Category category =this.categoryRepository.findById(categoryId);
        mav.addObject(category);
        return mav;
    }

    @GetMapping("/categories/{categoryId}/edit")
    public String showEditDetailsFormCategory(@PathVariable int categoryId, Map<String, Object> model) {
        Category category = categoryRepository.findById(categoryId);
        model.put("category", category);
        return "dashboard/createOrUpdateCategory";
    }
    @PostMapping("/categories/{categoryId}/edit")
    public String addCategory(@Valid Category category, BindingResult result, Model model, @PathVariable String categoryId) {
        if (result.hasErrors()) {
            return "dashboard/createOrUpdateCategory";
        }
        categoryRepository.save(category);
        model.addAttribute("categories", categoryRepository.findAll());
        return "redirect:/dashboard";
    }

    @GetMapping("/category/new")
    public String addingNewCategory() {
        return "";
    }

}
