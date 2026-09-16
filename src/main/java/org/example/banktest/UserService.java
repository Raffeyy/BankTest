package org.example.banktest;

import org.springframework.stereotype.Service;


@Service

public class UserService {
    private final UserRepository userRepository;
    DatabaseSpringboot databaseSpringboot = new DatabaseSpringboot();


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String username, String password) {
        var optionalerNutzer = userRepository.findById(username);
        DatabaseSpringboot echterNutzer = optionalerNutzer.get();


        if (optionalerNutzer.isEmpty()) {
            return "Fehlgeschlagen: Benutzername nicht gefunden!";
        } else {

            if (!authPassword(username, password)) {
                return "falsche daten";
            }

            return "eingeloggt";
        }
    }

    public String register(String username, String password) {

        DatabaseSpringboot neuerNutzer = new DatabaseSpringboot(username, password);

        if (authPassword(username, password)) {
            return "Nutzer existiert bereits";
        }

        userRepository.save(neuerNutzer);
        return "registriert";
    }


    public boolean authPassword(String username, String password) {

        var nutzer = userRepository.findById(username);

        if (nutzer.isEmpty()) {
            return false;
        } else {

            return nutzer.get().getPasswort().equals(password);
        }
    }

    public double getBalance(String username) {
        return userRepository.findById(username)
                .map(DatabaseSpringboot::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));
    }

}
