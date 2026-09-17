package org.example.banktest;


import org.springframework.stereotype.Service;

@Service

public class PasswordService {

    private final UserRepository userRepository;

    DatabaseSpringboot databaseSpringboot = new DatabaseSpringboot();


    public PasswordService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String forgotPassword(String user, String password) {
        DatabaseSpringboot newPassword = new DatabaseSpringboot(user, password);

        var optionalerNutzer = userRepository.findById(user);

        if (optionalerNutzer.isEmpty()) {

            return "Dieses Konto existiert nicht";

        } else {
            userRepository.save(newPassword);
        }

        return "Neues passwort wurde erstellt";
    }

}




