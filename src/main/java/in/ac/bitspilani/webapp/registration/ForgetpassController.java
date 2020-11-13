package in.ac.bitspilani.webapp.registration;

import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.AddressException;

@Controller
public class ForgetpassController {
    String email;

    @Autowired
    private UserRepository userRepository;

    @Qualifier("confirmationTokenRepository")
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user) {
        modelAndView.addObject("userEntity", user);
        modelAndView.setViewName("forgot");
        return modelAndView;
    }


    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user) throws AddressException {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            modelAndView.addObject("message", "This email Does not exist!");

            modelAndView.setViewName("error");
        } else {
            // user = customisingDashboard(user);
            email=existingUser.getEmail();
            user=userRepository.findByEmail(existingUser.getEmail());
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Request for Password change ");
            mailMessage.setText("Please click this link to change your password : "
                    + "http://localhost:8080/change-password?token=" + confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);
            modelAndView.addObject("emailId", user.getEmail());
            modelAndView.setViewName("Passwordchange");
        }

        return modelAndView;
    }

    @RequestMapping(value ="/change-password", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail());
            modelAndView.addObject(user);
            modelAndView.setViewName("change");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            User user = userRepository.findByEmail(token.getUser().getEmail());
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    public ModelAndView display(ModelAndView modelAndView, User user) {
        User change=userRepository.findByEmail(email);
        change.setPassword(user.getPassword());
        userRepository.save(change);
        modelAndView.setViewName("Succesfulchange");
        return modelAndView;
    }
}
