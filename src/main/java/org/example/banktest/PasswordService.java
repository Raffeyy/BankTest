package org.example.banktest;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class PasswordService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // Über Konstruktor injiziert

    public PasswordService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String forgotPassword(String user, String password) {
        var optionalerNutzer = userRepository.findById(user);

        if (optionalerNutzer.isEmpty()) {
            return "Dieses Konto existiert nicht";
        } else {
            DatabaseSpringboot bestehenderNutzer = optionalerNutzer.get();

            // HIER WIRD GEHASHT
            String gehashtesPasswort = passwordEncoder.encode(password);
            bestehenderNutzer.setPasswort(gehashtesPasswort);

            userRepository.save(bestehenderNutzer);
        }

        return "Neues passwort wurde erstellt";
    }
}
