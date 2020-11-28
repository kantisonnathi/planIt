package in.ac.bitspilani.webapp.system;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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



    final private String AUTH_SID;
    final private String AUTH_TOKEN;

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;


    public ScheduledTasks(ItemRepository itemRepository, UserRepository userRepository, EmailService emailService) {

        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        AUTH_SID="ACc63f3b4b4be59964b3f675061bdf5a06";
        AUTH_TOKEN="2e85596ee9b7fcfc0bc45875340eea46";
    }

    @Scheduled(initialDelay =1500*1000 ,fixedDelay = 86400 * 1000)
    public void sendDailyNotif() {
        List<User> users = userRepository.findAllByIdGreaterThan(0);
        for (User user : users) {
            SimpleMailMessage notifEmail = new SimpleMailMessage();
            notifEmail.setTo(user.getEmail());
            notifEmail.setSubject("Please update your inventory!");
            notifEmail.setText("Hello " + user.getName() + "!\n\nPlease update your items!\nhttp://localhost:8080/dashboard\n" +
                    "Please click on the above link to access your items.");
            emailService.sendEmail(notifEmail);
        }
    }
    @Scheduled(initialDelay =1500*1000 ,fixedDelay = 86400 * 1000)
    public void phonedaily()
    {
        List<User> users = userRepository.findAllByIdGreaterThan(0);
        for (User user : users) {
            Twilio.init(AUTH_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber(user.getPhoneNumber()),
                    new PhoneNumber("+16097986441"),
                    "Hello " + user.getName() + "!\n\nPlease update your items!\nhttp://localhost:8080/dashboard\n" +
                            "Please click on the above link to access your items.")
                    .create();
        }
    }
    @Scheduled(initialDelay = 900*1000,fixedDelay = 86400*1000)
    public void itemphoneover()
    {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        List<Item> itemsover=itemRepository.findAllByQuantityIs(0);
        Twilio.init(AUTH_SID, AUTH_TOKEN);
        for(Item item :itemsover) {
            User currentUser = item.getCategory().getUser();
            Message message = Message.creator(
                    new PhoneNumber(currentUser.getPhoneNumber()),
                    new PhoneNumber("+16097986441"),
                    "Hello " + currentUser.getName() + "!\n\nThere are no more " +
                            item.getName() + " in the house! ")
                    .create();
        }
    }
    @Scheduled(initialDelay = 60*1000,fixedDelay = 86400*1000)

       public void itemOver()
    {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
         List<Item> itemsover=itemRepository.findAllByQuantityIs(0);
         for(Item item :itemsover)
         {
        User currentUser = item.getCategory().getUser();
        SimpleMailMessage itemEmail = new SimpleMailMessage();
        itemEmail.setTo(currentUser.getEmail());
        itemEmail.setSubject("Time to stock up!");
        itemEmail.setText("Hello " + currentUser.getName() + "!\n\nThere are no more " +
                item.getName() + " in the house! ");
        emailService.sendEmail(itemEmail);

    }
    }



    @Scheduled(initialDelay = 600 * 1000,fixedDelay = 86400 * 1000)
    public void sendSMSNotifications() {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        List<Item> itemsDueTomorrow = itemRepository.findAllByDueDate(tomorrowDate);
        for (Item item : itemsDueTomorrow) {
            User currentUser = item.getCategory().getUser();
            Twilio.init(AUTH_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber(currentUser.getPhoneNumber()),
                    new PhoneNumber("+16097986441"),
                    "Hello " + currentUser.getName() + "!\n\nYou have items due tomorrow!\nYou have " +
                            item.getName() + " due tomorrow")
                    .create();
        }

    }

    @Scheduled(initialDelay = 600 * 1000,fixedDelay = 86400 * 1000)
    public void sendEmailNotifications() {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        List<Item> itemsDueTomorrow = itemRepository.findAllByDueDate(tomorrowDate);
        for (Item item : itemsDueTomorrow) {
            User currentUser = item.getCategory().getUser();
            SimpleMailMessage itemEmail = new SimpleMailMessage();
            itemEmail.setTo(currentUser.getEmail());
            itemEmail.setSubject("You have Items due soon!");
            itemEmail.setText("Hello " + currentUser.getName() + "!\n\nYou have items due tomorrow!\nYou have " +
                    item.getName() + " due tomorrow");
            emailService.sendEmail(itemEmail);
        }

    }
}
