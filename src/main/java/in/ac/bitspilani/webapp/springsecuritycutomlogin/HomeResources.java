package in.ac.bitspilani.webapp.springsecuritycutomlogin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {
    @GetMapping("/")
    public String home()
    {
        return "registration/WelcomePage";
    }
    @GetMapping("/user")
    public String user()
    {
        return "dashboard/dashboard";
    }

}

