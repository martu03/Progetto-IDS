package cs.unicam.it.Accesso;

import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UtenteLogRepository extends JpaRepository<UtenteLog, Integer> {
    boolean existsByEmail(String email);
    Optional<UtenteLog> findByEmail(String email);
}