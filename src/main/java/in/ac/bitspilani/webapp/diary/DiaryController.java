package in.ac.bitspilani.webapp.diary;

import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import jdk.jfr.Registered;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user/{userId}/diary")
public class DiaryController {

    private final DiaryEntryRepository diaryEntryRepository;
    private final UserRepository userRepository;

    public DiaryController(DiaryEntryRepository diaryEntryRepository, UserRepository userRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("user")
    public User findUser(@PathVariable("userId") int userId) {
        return this.userRepository.findById(userId);
    }

    @InitBinder("user")
    public void initUserBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public String personalDiaryHome(User user, ModelMap modelMap) {
        List<DiaryEntry> entries = diaryEntryRepository.findAllByUser(user);
        modelMap.put("user", user);
        modelMap.put("entries", entries);
        return "diary/diary";
    }
}
