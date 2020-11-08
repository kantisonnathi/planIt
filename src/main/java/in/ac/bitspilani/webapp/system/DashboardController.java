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
        if (user.userNew) {
            user = customisingDashboard(user);
            user.userNew = false;
            user = userRepository.save(user);
        }
        model.put("user", user);
        model.put("selections", user.getCategories());
        return "dashboard/dashboard";
    }





    private User customisingDashboard(User user) {


        //workout category
        Category workout = new Category();
        workout.setName("Workout");
        workout.setUser(user);
        workout = categoryRepository.save(workout);
        Item pushup = new Item();
        pushup.setName("Push Ups");
        pushup.setQuantity(10);
        workout.addItem(pushup);
        pushup = itemRepository.save(pushup);


        //groceries category
        Category grocery = new Category();
        grocery.setName("Groceries");
        user.addCategory(grocery);
        grocery = categoryRepository.save(grocery);
        Item tomatoes = new Item();
        tomatoes.setName("Tomatoes");
        tomatoes.setQuantity(6);
        grocery.addItem(tomatoes);
        tomatoes = itemRepository.save(tomatoes);


        switch(user.getProfession().toLowerCase()) {
            case "student":
                //adding a category and two items for assignments
                Category assignments = new Category();
                assignments.setName("Assignments");
                user.addCategory(assignments);
                assignments = categoryRepository.save(assignments);
                Item assignment1 = new Item();
                assignment1.setName("Assignment 1");
                assignment1.setQuantity(1);
                assignments.addItem(assignment1);
                Item assignment2 = new Item();
                assignment2.setName("Assignment 2");
                assignment1.setQuantity(1);
                assignments.addItem(assignment2);
                assignment1 = itemRepository.save(assignment1);
                assignment2 = itemRepository.save(assignment2);

                //Tests schedule
                Category tests = new Category();
                tests.setName("Tests");
                user.addCategory(tests);
                tests = categoryRepository.save(tests);
                Item OOP = new Item();
                OOP.setName("OOP test");
                OOP.setQuantity(1);
                tests.addItem(OOP);
                Item Logic = new Item();
                Logic.setName("Logic test");
                Logic.setQuantity(1);
                tests.addItem(Logic);
                Logic = itemRepository.save(Logic);
                OOP = itemRepository.save(OOP);



                //Resources Page
                Category resources = new Category();
                resources.setName("Resources");
                user.addCategory(resources);
                resources = categoryRepository.save(resources);
                Item oop = new Item();
                oop.setName("baeldung tutorials");
                oop.setQuantity(1);
                resources.addItem(oop);
                oop = itemRepository.save(oop);
                resources = categoryRepository.save(resources);



                break;


            case "youtuber":
                //idea category
                Category ideas = new Category();
                ideas.setName("Vlog Ideas");
                user.addCategory(ideas);
                ideas = categoryRepository.save(ideas);
                Item idea1 = new Item();
                idea1.setName("What I eat in a day");
                idea1.setQuantity(1);
                ideas.addItem(idea1);
                Item idea2 = new Item();
                idea2.setName("My daily routine");
                idea2.setQuantity(1);
                ideas.addItem(idea2);
                idea2 = itemRepository.save(idea2);
                idea1 = itemRepository.save(idea1);

                //collab schedule
                Category collab = new Category();
                user.addCategory(collab);
                collab.setName("Collaboration schedule");
                collab = categoryRepository.save(collab);
                Item item1 = new Item();
                item1.setName("Meet with Saloni");
                item1.setQuantity(1);
                collab.addItem(item1);
                item1 = itemRepository.save(item1);


                break;

            case "software engineer":
                //meeting category
                Category meetings = new Category();
                meetings.setName("Meetings");
                user.addCategory(meetings);
                meetings = categoryRepository.save(meetings);
                Item meeting1 = new Item();
                meeting1.setName("Meet with Brian");
                meeting1.setQuantity(1);
                meetings.addItem(meeting1);
                meeting1 = itemRepository.save(meeting1);


                //travel schedule
                Category travel = new Category();
                travel.setName("Travel Schedule");
                user.addCategory(travel);
                travel = categoryRepository.save(travel);
                Item trip1 = new Item();
                trip1.setName("Trip to US");
                trip1.setQuantity(1);
                travel.addItem(trip1);
                trip1 = itemRepository.save(trip1);
                Item trip2 = new Item();
                trip2.setName("Trip to Singapore");
                trip2.setQuantity(1);
                travel.addItem(trip2);
                trip2 = itemRepository.save(trip2);


                break;


        }
        return user;
    }
}




