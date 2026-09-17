package org.example.banktest;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String username, String password) {
        if (!authPassword(username, password)) {
            return "falsche daten";
        }
        return "eingeloggt";
    }

    public String register(String username, String password) {
        if (userRepository.existsById(username)) {
            return "Nutzer existiert bereits";
        }


        String gehashtesPasswort = passwordEncoder.encode(password);

        DatabaseSpringboot neuerNutzer = new DatabaseSpringboot(username, gehashtesPasswort);
        userRepository.save(neuerNutzer);
        return "registriert";
    }

    public boolean authPassword(String username, String password) {
        var nutzerOpt = userRepository.findById(username);
        if (nutzerOpt.isEmpty()) {
            return false;
        }

        DatabaseSpringboot nutzer = nutzerOpt.get();

        return passwordEncoder.matches(password, nutzer.getPasswort());
    }

    public double getBalance(String username) {
        return userRepository.findById(username)
                .map(DatabaseSpringboot::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));
    }

    public String setDeposit(String username, double amount) {
        DatabaseSpringboot nutzer = userRepository.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("benutzer nicht gefunden"));
        nutzer.setBalance(getBalance(username) + amount);
        userRepository.save(nutzer);
        return "erfolgreich eingezahlt";
    }
}
