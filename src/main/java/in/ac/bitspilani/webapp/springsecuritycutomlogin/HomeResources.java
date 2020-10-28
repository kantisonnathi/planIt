package in.ac.bitspilani.webapp.springsecuritycutomlogin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {
    @GetMapping("/")
    public String home()
    {
        return("<h1> Welcome</h1>");
    }
    @GetMapping("/user")
    public String user()
    {
        return "dashboard/dashboard";
    }
    @GetMapping("/admin")
    public String admin()
    {
        return ("<h1>Welcome Admin</h1>");
    }
}

