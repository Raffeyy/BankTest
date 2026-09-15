package org.example.banktest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/log")
    public String logControll(@RequestParam String user, @RequestParam String pass) {

        return userService.login(user, pass);
    }

    @PostMapping("/register")
    public String registerControll (@RequestParam String user, @RequestParam String pass) {



        return userService.register(user, pass);
    }
}
