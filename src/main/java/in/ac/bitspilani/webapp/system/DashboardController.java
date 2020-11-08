package in.ac.bitspilani.webapp.system;


import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.category.CategoryRepository;
import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.item.ItemRepository;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class DashboardController {

    final CategoryRepository categoryRepository;
    final ItemRepository itemRepository;
    final UserRepository userRepository;

    public DashboardController(CategoryRepository categoryRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Map<String, Object> model, Principal principal) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        model.put("user", user);
        model.put("selections", user.getCategories());
        return "dashboard/dashboard";
    }





    private User customisingDashboard(User user) {


        //workout category
        Category workout = new Category();
        workout = categoryRepository.save(workout);
        workout.setName("Workout");
        Item pushup = new Item();
        pushup = itemRepository.save(pushup);
        pushup.setName("Push Ups");
        pushup.setQuantity(10);
        workout.addItem(pushup);
        pushup.setCategory(workout);
        user.addCategory(workout);
        workout.setUser(user);


        switch(user.getProfession().toLowerCase()) {
            case "student":
                //adding a category and two items for assignments
                Category assignments = new Category();
                assignments = categoryRepository.save(assignments);
                assignments.setName("Assignments");
                Item assignment1 = new Item();
                assignment1 = itemRepository.save(assignment1);
                assignment1.setName("Assignment 1");
                assignment1.setQuantity(1);
                Item assignment2 = new Item();
                assignment2 = itemRepository.save(assignment2);
                assignment2.setName("Assignment 2");
                assignment1.setQuantity(1);
                assignments.addItem(assignment2);
                assignment2.setCategory(assignments);
                assignments.addItem(assignment1);
                assignment1.setCategory(assignments);
                user.addCategory(assignments);
                assignments.setUser(user);

                //Tests schedule
                Category tests = new Category();
                tests = categoryRepository.save(tests);
                tests.setName("Tests");
                Item OOP = new Item();
                OOP = itemRepository.save(OOP);
                OOP.setName("OOP test");
                OOP.setCategory(tests);
                OOP.setQuantity(1);
                tests.addItem(OOP);
                Item Logic = new Item();
                Logic = itemRepository.save(Logic);
                Logic.setName("Logic test");
                Logic.setQuantity(1);
                Logic.setCategory(tests);
                tests.addItem(Logic);
                user.addCategory(tests);
                tests.setUser(user);


                //Resources Page
                Category resources = new Category();
                resources = categoryRepository.save(resources);
                resources.setName("Resources");
                Item oop = new Item();
                oop = itemRepository.save(oop);
                oop.setName("baeldung tutorials");
                oop.setQuantity(1);
                resources.addItem(oop);
                oop.setCategory(resources);
                user.addCategory(resources);
                resources.setUser(user);


                break;


            case "youtuber":
                //idea category
                Category ideas = new Category();
                ideas = categoryRepository.save(ideas);
                Item idea1 = new Item();
                idea1 = itemRepository.save(idea1);
                idea1.setName("What I eat in a day");
                idea1.setQuantity(1);
                Item idea2 = new Item();
                idea2 = itemRepository.save(idea2);
                idea2.setName("My daily routine");
                idea2.setQuantity(1);
                ideas.addItem(idea1);
                ideas.addItem(idea2);
                idea1.setCategory(ideas);
                idea2.setCategory(ideas);
                user.addCategory(ideas);
                ideas.setUser(user);

                //collab schedule
                Category collab = new Category();
                collab = categoryRepository.save(collab);
                collab.setName("Collaboration schedule");
                Item item1 = new Item();
                item1 = itemRepository.save(item1);
                item1.setName("Meet with Saloni");
                item1.setQuantity(1);
                item1.setCategory(collab);
                collab.addItem(item1);
                collab.setUser(user);
                user.addCategory(collab);


                break;

            case "software engineer":
                //meeting category
                Category meetings = new Category();
                meetings = categoryRepository.save(meetings);
                meetings.setName("Meetings");
                Item meeting1 = new Item();
                meeting1 = itemRepository.save(meeting1);
                meeting1.setName("Meet with Brian");
                meeting1.setQuantity(1);
                meetings.addItem(meeting1);
                meeting1.setCategory(meetings);
                user.addCategory(meetings);
                meetings.setUser(user);

                //travel schedule
                Category travel = new Category();
                travel = categoryRepository.save(travel);
                travel.setName("Travel Schedule");
                Item trip1 = new Item();
                trip1 = itemRepository.save(trip1);
                trip1.setName("Trip to US");
                trip1.setQuantity(1);
                trip1.setCategory(travel);
                travel.addItem(trip1);
                Item trip2 = new Item();
                trip2 = itemRepository.save(trip2);
                trip2.setName("Trip to Singapore");
                trip2.setQuantity(1);
                trip2.setCategory(travel);
                travel.addItem(trip2);
                travel.setUser(user);
                user.addCategory(travel);


                break;

            case "home":
                //groceries category
                Category grocery = new Category();
                grocery = categoryRepository.save(grocery);
                grocery.setName("Groceries");
                Item tomatoes = new Item();
                tomatoes = itemRepository.save(tomatoes);
                tomatoes.setName("Tomatoes");
                tomatoes.setQuantity(6);
                grocery.addItem(tomatoes);
                tomatoes.setCategory(grocery);
                user.addCategory(grocery);
                grocery.setUser(user);


                break;


        }
        return user;
    }
}




