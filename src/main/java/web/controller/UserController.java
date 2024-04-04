package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.servise.UserService;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String allUsers(ModelMap model) {
        model.addAttribute("users", userService.allUsers());
        return "users";
    }

    @GetMapping(value = "/edit")
    public String editPage(@RequestParam("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "editPage";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute User user) {
        userService.edit(user);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        User user = userService.getById(id);

        userService.delete(user);
        return "redirect:/";
    }


    @GetMapping(value = "/add")
    public String addPage(ModelMap model) {
        model.addAttribute("users", new User());
        return "add";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/";
    }

}
