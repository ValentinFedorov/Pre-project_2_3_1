package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/")
    public String showAllUsers (Model model) {
        List<User> allUsers = userDao.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "all-users";
    }

    @RequestMapping(value = "/addUsers")
    public String addUsers (Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @RequestMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userDao.add(user);
        return "redirect:/";
    }

    @RequestMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDao.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/changeUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute(userDao.getUserById(id));
        return "updateUsers";
    }

    @PatchMapping("/changeUser/{id}")
    public String editUser(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        userDao.updateUser(id, user);
        return "redirect:/";
    }
}
