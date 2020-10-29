package in.ac.bitspilani.webapp.springsecuritycutomlogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/custom_login")
    public String addingNewCategory() {
        return "registration/Registration";
    }
}
