package in.ac.bitspilani.webapp.item;

import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.category.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
public class ItemController {

    private final CategoryRepository categoryRepository;

    public ItemController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @GetMapping("/{categoryId}/itemDetails")
    public String ItemDetails(@PathVariable("categoryId") int categoryId, Map<String, Object> map) {
        ModelAndView mav = new ModelAndView("dashboard/categoryDetails");
        Category category = categoryRepository.findById(categoryId);
        map.put("selections", category.getItems());
        return "dashboard/itemDetails";
    }

    @GetMapping("/{itemId}/edit")
    public String editDetails(@PathVariable String itemId) {
        return "dashboard/createOrUpdateItem";
    }
}