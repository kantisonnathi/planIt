package in.ac.bitspilani.webapp.registration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignUp {
    @GetMapping("/signup")
    public String page()
    {
        return "registration/signup";
    }
}
