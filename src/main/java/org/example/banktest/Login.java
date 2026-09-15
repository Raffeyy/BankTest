package org.example.banktest;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class Login {
    
    private final DatabaseSpringbootRepository repo;
    DatabaseSpringboot databaseSpringboot = new DatabaseSpringboot();

    public Login(DatabaseSpringbootRepository repo) {
        this.repo = repo; // Hier wird die Variable sauber initialisiert

    }

    @PostMapping("/log")
    public String login(@RequestParam String user, @RequestParam String pass) {
        var optionalerNutzer = repo.findById(user);

        if (optionalerNutzer.isEmpty()) {
            return "Fehlgeschlagen: Benutzername nicht gefunden!";
        }

        if (repo.existsById(user)) {
            DatabaseSpringboot echterNutzer = optionalerNutzer.get();

            if (echterNutzer.getPasswort().equals(pass)) {
                return "Eingeloggt! Dein Kontostand beträgt: " + echterNutzer.getBalance();
            } else {
                return "Fehlgeschlagen: Falsches Passwort!";
            }

        }

        return "Fehlgeschlagen";
    }

    @PostMapping("/register")
    public String register(@RequestParam String user, @RequestParam String pass) {

        DatabaseSpringboot neuerNutzer = new DatabaseSpringboot(user, pass);


        if (repo.existsById(user)) {
            return "benutzer existiert bereits";
        }
        repo.save(neuerNutzer);


        return"Konto wurde erstellt";
    }



}
