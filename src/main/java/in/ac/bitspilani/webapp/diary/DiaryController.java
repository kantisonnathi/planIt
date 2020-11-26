package in.ac.bitspilani.webapp.diary;

import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user/{userId}/")
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

    @GetMapping("diary")
    public String personalDiaryHome(User user, ModelMap modelMap) {
        List<DiaryEntry> entries = diaryEntryRepository.findAllByUser(user);
        modelMap.put("user", user);
        modelMap.put("entries", entries);
        return "diary/diary";
    }

    @GetMapping("entry/{entryId}")
    public String personalDiaryDisplay(@PathVariable("entryId") int entryId, User user, ModelMap modelMap) {
        modelMap.put("user",user);
        DiaryEntry diaryEntry = diaryEntryRepository.findById(entryId);
        modelMap.put("entry",diaryEntry);
        return "diary/diaryEntryDisplay";
    }

    @GetMapping("entry/{entryId}/edit")
    public String personalDiaryEdit(@PathVariable("entryId") int entryId, User user, ModelMap modelMap) {
        DiaryEntry diaryEntry = diaryEntryRepository.findById(entryId);
        modelMap.put("user",user);
        modelMap.put("entry", diaryEntry);
        return "diary/createOrUpdateDiaryEntry";
    }

    @PostMapping("entry/{entryId}/edit")
    public String editedEntry(@Valid DiaryEntry diaryEntry, BindingResult result, User user, ModelMap model) {
        if (result.hasErrors()) {
            diaryEntry.setUser(user);
            model.put("entry", diaryEntry);
            model.put("user",user);
            return "diary/createOrUpdateDiaryEntry";
        } else {
            diaryEntry.setUser(user);
            this.diaryEntryRepository.save(diaryEntry);
            return "redirect:/user/{userId}/diary";
        }
    }

    @GetMapping("entry/new")
    public String newEntryCreation(User user, ModelMap modelMap) {
        DiaryEntry diaryEntry = new DiaryEntry();
        user.addDiaryEntry(diaryEntry);
        modelMap.put("entry", diaryEntry);
        return "diary/createOrUpdateDiaryEntry";
    }

    @PostMapping("entry/new")
    public String processCreationFormCategory(User user, @Valid DiaryEntry diaryEntry, BindingResult result, ModelMap model) {
        diaryEntry.setUser(user);
        if (result.hasErrors()) {
            model.put("entry", diaryEntry);
            return "diary/error";
        }
        else {
            this.diaryEntryRepository.save(diaryEntry);
            return "redirect:/user/{userId}/diary";
        }
    }

    @GetMapping("entry/{entryId}/delete")
    public String removeCategory(@PathVariable("entryId") int entryId) {
        diaryEntryRepository.deleteById(entryId);
        return  "redirect:/user/{userId}/diary";
    }

}
