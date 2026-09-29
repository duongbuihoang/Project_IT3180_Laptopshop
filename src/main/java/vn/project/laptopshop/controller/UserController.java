package vn.project.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.project.laptopshop.domain.User;
import vn.project.laptopshop.repository.UserRepository;
import vn.project.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        // String test = this.userService.handleHello();
        List<User> arrUsers = this.userService.getAllUsersByEmail("duongbui28052006@gmail.com");
        System.out.println(arrUsers);

        model.addAttribute("duongbui", "test");
        model.addAttribute("test", "from controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);

        // System.out.println(">>> Check users: " + users);
        return "admin/user/table-user";

        // String test = this.userService.handleHello();
        // model.addAttribute("newUser", new User());
        // model.addAttribute("test", "from controller with model");
        // return "admin/user/create";
    }

    @RequestMapping("/admin/user/{id}") // GET method
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        // System.out.println(">>> Check path id: " + id);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/update/{id}") // GET method
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser); // contact variable between jsp(view) and controller
        // System.out.println(">>> Check path id: " + id);
        return "admin/user/update";
    }

    @PostMapping("/admin/user/update") // POST method
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User updatedUser) {
        // TODO: process POST request
        User currentUser = this.userService.getUserById(updatedUser.getId());
        if (currentUser != null) {
            // System.out.println("run here");
            currentUser.setAddress(updatedUser.getAddress());
            currentUser.setFullName(updatedUser.getFullName());
            currentUser.setPhone(updatedUser.getPhone());

            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/create") // GET method
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User duongbui) {
        // System.out.println("run here " + duongbui);
        // return "hello";
        this.userService.handleSaveUser(duongbui);
        return "redirect:/admin/user";
    }
}

// DI: dependency injection
// @RestController
// public class UserController {

// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("")
// public String getHomePage() {
// return this.userService.handleHello();
// }
// }
