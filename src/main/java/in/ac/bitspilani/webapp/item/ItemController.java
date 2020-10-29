package in.ac.bitspilani.webapp.item;

import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.category.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class ItemController {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    public ItemController(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }
    @GetMapping("/{categoryId}/itemDetails")
    public String ItemDetails(@PathVariable("categoryId") int categoryId, Map<String, Object> map) {
        ModelAndView mav = new ModelAndView("dashboard/categoryDetails");
        Category category = categoryRepository.findById(categoryId);
        map.put("selections", category.getItems());
        return "dashboard/itemDetails";
    }

    @GetMapping("/items/{itemId}/edit")
    public String showEditDetailsForm(@PathVariable int itemId, Map<String,Object> model) {
        Item item = itemRepository.findById(itemId);
        model.put("item", item);
        return "dashboard/createOrUpdateItem";
    }

    @PostMapping("/items/{itemId}/edit")
    public String addItem(@Valid Item item, BindingResult result, Model model, @PathVariable String itemId) {
        if (result.hasErrors()) {
            return "dashboard/createOrUpdateItem";
        }
        itemRepository.save(item);
        model.addAttribute("items", itemRepository.findAll());
        return "redirect:/dashboard";
    }


}