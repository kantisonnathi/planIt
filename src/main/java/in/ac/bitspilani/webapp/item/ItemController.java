package in.ac.bitspilani.webapp.item;

import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.category.CategoryRepository;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user/{userId}/categories/{categoryId}")
public class ItemController {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;


    public ItemController(CategoryRepository categoryRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
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

    @ModelAttribute("category")
    public Category findCategory(@PathVariable("categoryId") int categoryId) {
        return this.categoryRepository.findById(categoryId);
    }

    @InitBinder("category")
    public void initCategoryBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("item")
    public void initItemBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new ItemValidator());
    }

    @GetMapping("/")
    public String defaultMethod() {
        return "redirect:/user/{userId}/categories/{categoryId}/itemDetails";
    }

    @GetMapping("/itemDetails")
    public String ItemDetails(@PathVariable("categoryId") int categoryId,User user, Map<String, Object> map) {
        Category category = categoryRepository.findById(categoryId);
        map.put("selections", category.getItems());
        map.put("user", user);
        return "dashboard/itemDetails";
    }

    @GetMapping("/items/{itemId}/edit")
    public String showEditDetailsFormItem(@PathVariable("itemId") int itemId, ModelMap model) {
        Item item = itemRepository.findById(itemId);
        model.put("item", item);
        return "dashboard/createOrUpdateItem";

    }

    @PostMapping("/items/{itemId}/edit")
    public String addItem(Category category,@Valid Item item,BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            item.setCategory(category);
            model.put("item", item);
            return "dashboard/createOrUpdateItem";
        }
        else {
            item.setCategory(category);
            category.addItem(item);
            this.itemRepository.save(item);
            return "redirect:/user/{userId}/categories/{categoryId}/itemDetails";
        }
    }

    @GetMapping("/items/{itemId}/delete")
    @Transactional
    public String deleteItem(@PathVariable("itemId") int itemId) {
        /*itemRepository.deleteById(itemId);*/

        Item item = itemRepository.findById(itemId);
        Category category = item.getCategory();
        category.removeItem(item);
        itemRepository.delete(item);
        return "redirect:/user/{userId}/categories/{categoryId}/itemDetails";
    }

    @GetMapping("/item/new")
    public String initItemCreation(Category category, ModelMap model) {
        Item item = new Item();
        category.addItem(item);
        item.setCategory(category);
        model.put("item", item);
        return "dashboard/createOrUpdateItem";
    }

    @PostMapping("/item/new")
    public String processCreationItem(Category category, @Valid Item item, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(item.getName()) && item.isNew() && category.getItem(item.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        item.setCategory(category);
        if (result.hasErrors()) {
            return "dashboard/createOrUpdateItem";
        }
        else {
            this.itemRepository.save(item);
            return "redirect:/user/{userId}/categories/{categoryId}/itemDetails";
        }
    }


}