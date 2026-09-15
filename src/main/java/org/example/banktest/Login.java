package org.example.banktest;


import org.apache.catalina.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController


public class Login {

    public static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }


    private final DatabaseSpringbootRepository repo;
    DatabaseSpringboot databaseSpringboot = new DatabaseSpringboot();

    public Login(DatabaseSpringbootRepository repo) {

        this.repo = repo;

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
                return  "iban: DE" + databaseSpringboot.getIban();

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


        neuerNutzer.setIban(generateRandom(12));

        return"Konto wurde erstellt";




    }



}
