package in.ac.bitspilani.webapp.system;

import in.ac.bitspilani.webapp.category.CategoryRepository;
import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.item.ItemRepository;
import in.ac.bitspilani.webapp.registration.EmailService;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledTasks {


    /*@Value("${twilio.account-ssid:te}")
    final private String AUTH_SID;

    @Value("${twilio.auth-token}")
    final private String AUTH_TOKEN;
*/
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;


    public ScheduledTasks(ItemRepository itemRepository, UserRepository userRepository, EmailService emailService) {

        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Scheduled(fixedDelay = 86400 * 1000)
    public void sendSMSNotifications() {

    }

    @Scheduled(fixedDelay = 86400 * 1000)
    public void sendEmailNotifications() {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        List<Item> itemsDueTomorrow = itemRepository.findAllByDueDate(tomorrowDate);
        for (Item item : itemsDueTomorrow) {
            User currentUser = item.getCategory().getUser();
            SimpleMailMessage itemEmail = new SimpleMailMessage();
            itemEmail.setTo(currentUser.getEmail());
            itemEmail.setSubject("You have Items due soon!");
            itemEmail.setText("Hello" + currentUser.getName() + "!\n\nYou have items due tomorrow!\nYou have " +
                    item.getName() + " due tomorrow");
            emailService.sendEmail(itemEmail);
        }

    }
}
