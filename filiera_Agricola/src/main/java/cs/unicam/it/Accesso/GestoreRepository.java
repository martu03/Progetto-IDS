package cs.unicam.it.Accesso;

import cs.unicam.it.Utenti.GestorePiattaforma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GestoreRepository extends JpaRepository<GestorePiattaforma, Long> {
    Optional<GestorePiattaforma> findByEmail(String email);
}