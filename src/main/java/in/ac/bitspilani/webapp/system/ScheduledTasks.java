package in.ac.bitspilani.webapp.system;

import in.ac.bitspilani.webapp.category.CategoryRepository;
import in.ac.bitspilani.webapp.item.ItemRepository;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {


    @Value("${}")
    final private String AUTH_SID;

    @Value("${}")
    final private String AUTH_TOKEN;

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public ScheduledTasks(ItemRepository itemRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public ScheduledTasks(ItemRepository itemRepository) {

        this.itemRepository = itemRepository;
    }

    @Scheduled(fixedDelay = 86400 * 1000)
    public void sendSMSNotifications() {

    }
}
