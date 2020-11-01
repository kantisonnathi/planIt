package in.ac.bitspilani.webapp.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("/user/{userId}/edit")
    public String showEditDetailsFormUser(@PathVariable("userId") int userId, ModelMap model) {
        User user = this.userRepository.findById(userId);
        model.put("user",user);
        return "dashboard/createOrUpdateUser";
    }

    @PostMapping("user/{userId}/edit")
    public String processUserUpdateForm(@Valid User user, BindingResult result, @PathVariable("userId") int userId, ModelMap model) {
        if (result.hasErrors()) {
            model.put("user", user);
            return "dashboard/createOrUpdateUser";
        }
        else {
            user.setId(userId);
            this.userRepository.save(user);
            return "redirect:/dashboard";
        }
    }
}
