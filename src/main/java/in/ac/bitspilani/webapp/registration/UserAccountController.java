package in.ac.bitspilani.webapp.registration;
import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Controller
public class UserAccountController {
       private String email;
    @Autowired
    private UserRepository userRepository;

    @Qualifier("confirmationTokenRepository")
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user) {
        modelAndView.addObject("userEntity", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user) throws AddressException {
        email=user.getEmail();
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            modelAndView.addObject("message", "This email already exists!");

            modelAndView.setViewName("error");
        }
        else {
            // user = customisingDashboard(user);
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8080/confirm-account?token=" + confirmationToken.getConfirmationToken());
           User test=new User();
            emailService.sendEmail(mailMessage);
            modelAndView.addObject("userEntity",test);

            modelAndView.addObject("emailId", user.getEmail());

            modelAndView.setViewName("succesfulRegistration");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView,User user, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User use = userRepository.findByEmail(token.getUser().getEmail());
            use.setEmailVerified(true);
            //user = customisingDashboard(user);
            modelAndView.addObject("userEntity",user);
            userRepository.save(use);
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            User  use = userRepository.findByEmail(token.getUser().getEmail());
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }


    @RequestMapping(value = "/phonereg",  method = { RequestMethod.GET})
    public ModelAndView registerPhone(ModelAndView modelAndView, User user)
    {
        modelAndView.addObject("userEntity",user);
        modelAndView.setViewName("accountVerified");

       return modelAndView;
    }

    @RequestMapping(value = "/phonereg",  method = { RequestMethod.POST})
    public void galeez(ModelAndView modelAndView,User user)
    {
        User fin=userRepository.findByEmail(email);
        fin.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(fin);

    }
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ModelAndView displaySuccess(ModelAndView modelAndView, User user) {
        user=userRepository.findByEmail(email);
        user.setPhoneVerified(true);
        if(user.isEmailVerified() && user.isPhoneVerified())
        {
            user.setUserComplete(true);
        }
       userRepository.save(user);
        modelAndView.setViewName("suceess");
        return modelAndView;
    }




}