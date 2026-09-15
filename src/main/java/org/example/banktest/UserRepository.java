package org.example.banktest;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface UserRepository extends JpaRepository<DatabaseSpringboot, String> {
    // Hier drinnen lassen wir alles leer. Spring Boot erledigt den Rest!
}
