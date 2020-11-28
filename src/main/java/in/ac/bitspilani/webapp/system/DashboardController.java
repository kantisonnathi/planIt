package in.ac.bitspilani.webapp.system;


import in.ac.bitspilani.webapp.category.Category;
import in.ac.bitspilani.webapp.category.CategoryRepository;
import in.ac.bitspilani.webapp.diary.DiaryEntry;
import in.ac.bitspilani.webapp.item.Item;
import in.ac.bitspilani.webapp.item.ItemRepository;
import in.ac.bitspilani.webapp.user.User;
import in.ac.bitspilani.webapp.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.time.LocalDate;
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
        String email= principal.getName();
        if(email.matches("[0-9]+")) {
            email = principal.toString();

            int start = email.indexOf("email=");
            int end = email.indexOf("}", start);
            email = email.substring(start + 6, end);
        }
        User user = userRepository.findByEmail(email);//    String email = oauth2user.getEmail();
        //User user = new User();
        if(user==null)
        {

            User fin=new User();
            fin.setEmail(email);
            fin.setUserComplete(true);
            fin.setProfession("default");
            fin= userRepository.save(fin);
            fin = customisingDashboard(fin);
            fin.userNew=false;
            fin= userRepository.save(fin);

            model.put("user", fin);
            model.put("selections", fin.getCategories());

        }
        else {
            if (user.userNew) {
                user = customisingDashboard(user);
                user.userNew = false;
                user = userRepository.save(user);
            }
            model.put("user", user);
            model.put("selections", user.getCategories());
        }
        return "dashboard/dashboard";
    }

    @GetMapping("user/{userId}/trigger")
    public String trigger(@PathVariable("userId") int userId, ModelMap map) {
        List<Category> categoryList = categoryRepository.findByUserId(userId);
        User user = userRepository.findById(userId);
        System.out.println(categoryList);
        List<Item> itemList = new ArrayList<>();
        for (Category category : categoryList) {
            List<Item> list = itemRepository.findByCategoryId(category.getId());
            itemList.addAll(list);
        }
        //System.out.println(itemList);
        LocalDate today = LocalDate.now();
        List<Item> finalItems = new ArrayList<>();
        for (Item item : itemList) {
            LocalDate dueDate = item.getDueDate();
            if (dueDate.compareTo(today) > 0) {
                finalItems.add(item);
            }
        }
        map.put("user",user);
        map.put("items",finalItems);
        return "dashboard/trigger";
    }

    @GetMapping("user/{userId}/about")
    public String aboutPage(@PathVariable("userId") int userId, ModelMap modelMap) {
        User user = userRepository.findById(userId);
        modelMap.put("user",user);
        return "dashboard/about";
    }





    private User customisingDashboard(User user) {

        LocalDate today = LocalDate.now();

        //kitchen appliances category
        Category kitchenAppliances= new Category();
        kitchenAppliances.setName("Kitchen Appliances");
        user.addCategory(kitchenAppliances);
        kitchenAppliances = categoryRepository.save(kitchenAppliances);
        Item grinder = new Item();
        grinder.setName("Grinder");
        grinder.setQuantity(1);
        grinder.setDueDate(today);
        kitchenAppliances.addItem(grinder);
        grinder = itemRepository.save(grinder);

        //to - do list
        Category todoList = new Category();
        todoList.setName("To-do List");
        user.addCategory(todoList);
        todoList = categoryRepository.save(todoList);
        Item dogwalk = new Item();
        dogwalk.setName("Walk the dog");
        dogwalk.setQuantity(1);
        dogwalk.setDueDate(today);
        todoList.addItem(dogwalk);
        itemRepository.save(dogwalk);



        //workout category
        Category workout = new Category();
        workout.setName("Workout");
        user.addCategory(workout);
        workout = categoryRepository.save(workout);
        Item pushup = new Item();
        pushup.setName("Push Ups");
        pushup.setQuantity(10);
        pushup.setDueDate(today);
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
        tomatoes.setDueDate(today);
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
                assignment1.setDueDate(today);
                assignment1.setName("Assignment 1");
                assignment1.setQuantity(1);
                assignments.addItem(assignment1);
                Item assignment2 = new Item();
                assignment2.setName("Assignment 2");
                assignment2.setDueDate(today);
                assignment1.setQuantity(2);
                assignments.addItem(assignment2);
                assignment1 = itemRepository.save(assignment1);
                assignment2 = itemRepository.save(assignment2);

                //Tests schedule
                Category tests = new Category();
                tests.setName("Tests");
                user.addCategory(tests);
                tests = categoryRepository.save(tests);
                Item OOP = new Item();
                OOP.setDueDate(today);
                OOP.setName("OOP test");
                OOP.setQuantity(1);
                tests.addItem(OOP);
                Item Logic = new Item();
                Logic.setName("Logic test");
                Logic.setDueDate(today);
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
                oop.setDueDate(today);
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
                idea1.setDueDate(today);
                idea1.setName("What I eat in a day");
                idea1.setQuantity(1);
                ideas.addItem(idea1);
                Item idea2 = new Item();
                idea2.setName("My daily routine");
                idea2.setDueDate(today);
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
                item1.setDueDate(today);
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
                meeting1.setDueDate(today);
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
                trip1.setDueDate(today);
                trip1.setQuantity(1);
                travel.addItem(trip1);
                trip1 = itemRepository.save(trip1);
                Item trip2 = new Item();
                trip2.setDueDate(today);
                trip2.setName("Trip to Singapore");
                trip2.setQuantity(1);
                travel.addItem(trip2);
                trip2 = itemRepository.save(trip2);


                break;
            case "business-man":
                Category travel1 = new Category();
                travel1.setName("Travel Schedule");
                user.addCategory(travel1);
                travel1 = categoryRepository.save(travel1);
                Item trip11 = new Item();
                trip11.setName("Trip to US");
                trip11.setDueDate(today);
                trip11.setQuantity(1);
                travel1.addItem(trip11);
                trip1 = itemRepository.save(trip11);
                Item trip21 = new Item();
                trip21.setDueDate(today);
                trip21.setName("Trip to Singapore");
                trip21.setQuantity(1);
                travel1.addItem(trip21);
                trip2 = itemRepository.save(trip21);

            case "professor":

                Category adminMeetings = new Category();
                adminMeetings.setName("Meetings");
                user.addCategory(adminMeetings);
                meetings = categoryRepository.save(adminMeetings);
                Item meeting3 = new Item();
                meeting3.setDueDate(today);
                meeting3.setName("Meet with AUGSD");
                meeting3.setQuantity(1);
                adminMeetings.addItem(meeting3);
                meeting1 = itemRepository.save(meeting3);

                Category gradeAssignments = new Category();
                gradeAssignments.setName("Assignments");
                user.addCategory(gradeAssignments);
                gradeAssignments = categoryRepository.save(gradeAssignments);
                Item oopProject = new Item();
                oopProject.setName("Grade OOP projects");
                oopProject.setDueDate(today.plusDays(4));
                oopProject.setQuantity(100);
                gradeAssignments.addItem(oopProject);
                oopProject = itemRepository.save(oopProject);


        }
        return user;
    }
}




