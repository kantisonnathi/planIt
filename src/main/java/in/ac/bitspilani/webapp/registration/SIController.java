package in.ac.bitspilani.webapp.registration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SIController {

    @GetMapping("/login")
    public String showLogin(Model model)
    {
        SignIn si=new SignIn();
        model.addAttribute("signin",si);
        return "Registration";
    }
    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String getData(@ModelAttribute("signin") SignIn signin){
       return "";
    }
}
