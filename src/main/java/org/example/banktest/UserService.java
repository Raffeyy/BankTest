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
                    return "eingeloggt!";
                } else {

                    return "passwort";

                }
            }
            return "ok";
        }

        public String register(String username, String password) {

            DatabaseSpringboot neuerNutzer = new DatabaseSpringboot(username, password);

            if (userRepository.existsById(username)) {

                userRepository.save(neuerNutzer);
            }
            else {

                userRepository.save(neuerNutzer);
                return "erfolgreich registriert";
            }

            return "registriert ";
        }
    }
