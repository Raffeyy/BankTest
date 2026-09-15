package org.example.banktest;

import org.springframework.stereotype.Service;



@Service

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

        public String login(String username, String password) {
            var optionalerNutzer = userRepository.findById(username);
            DatabaseSpringboot echterNutzer = optionalerNutzer.get();

            if (optionalerNutzer.isEmpty()) {
                return "Fehlgeschlagen: Benutzername nicht gefunden!";
            }

            if (userRepository.existsById(username)) {

                if (echterNutzer.getPasswort().equals(password)) {
                    return "Fehlgeschlagen: Falsches Passwort!";
                } else {

                    return "iban: DE" + echterNutzer.getIban();

                }
            }

            return "ok";
        }

        public String register(String username, String password) {

            DatabaseSpringboot neuerNutzer = new DatabaseSpringboot();


            if (userRepository.existsById(username)) {
                return username;

            }

            userRepository.save(neuerNutzer);
            new DatabaseSpringboot();


            userRepository.save(neuerNutzer);

            return username;
        }
    }


