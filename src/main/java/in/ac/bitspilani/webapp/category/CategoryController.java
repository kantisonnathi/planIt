package in.ac.bitspilani.webapp.category;

import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    private final CategoryRepository categories;
    private final ItemRepository items;


    public CategoryController(CategoryRepository categoryService, ItemRepository items) {
        this.categories = categoryService;
        this.items = items;
    }

    @GetMapping("categories/{categoryId}")
    public ModelAndView showCategory(@PathVariable("categoryId") int categoryId) {
        ModelAndView mav = new ModelAndView("dashboard/dashboard");
        Category category =this.categories.findById(categoryId);
        mav.addObject(category);
        return mav;
    }

    @GetMapping("/category/new")
    public String addingNewCategory() {
        return "";
    }

}
