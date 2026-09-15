package org.example.banktest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Transfer {

    DatabaseSpringboot databaseSpringboot = new DatabaseSpringboot();


    @PostMapping("/Transfer")
    public String transfer(@RequestParam int IBAN, double amount) {




        return "ada";
    }

}
    
    

