package org.example.banktest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseSpringbootRepository extends JpaRepository<DatabaseSpringboot, String> {
    // Hier drinnen lassen wir alles leer. Spring Boot erledigt den Rest!
}
