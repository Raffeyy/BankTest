package org.example.banktest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Transfer {
    private final DatabaseSpringbootRepository repo;
    DatabaseSpringboot databaseSpringboot = new DatabaseSpringboot();
    
    @PostMapping("/Transfer")
    public String transfer(@RequestParam int IBAN, double amount) {
        
        if (repo.exists(IBAN)) {
            
            if (amount <= this.databaseSpringboot.getBalance()) {
                double newBalance = this.databaseSpringboot.getBalance() + amount;
                return "Erfolgreich überwiesen " + amount + "€";
            }
            else {
                return "ihr geld reicht nicht aus";
            }
        }
            
        
    }
    
    
}
