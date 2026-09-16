package org.example.banktest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;


    @PostMapping("/log")
    public String logControll(@RequestParam String user, @RequestParam String pass) {

        return userService.login(user, pass);
    }


    @PostMapping("/register")
    public String registerControll(@RequestParam String user, @RequestParam String pass) {


        return userService.register(user, pass);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String user, @RequestParam String pass) {

        return passwordService.forgotPassword(user, pass);
    }

    @PostMapping("/balance")
    public double currentBalance(@RequestParam String user) {
        return userService.getBalance(user);
    }


}
