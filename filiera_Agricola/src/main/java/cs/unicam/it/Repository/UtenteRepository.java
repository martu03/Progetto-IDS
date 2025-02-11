package cs.unicam.it.Repository;

import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtenteRepository extends JpaRepository<UtenteLog, Integer> {

    // Trova un utente tramite email
    UtenteLog findByEmail(String email);

    // Trova un utente tramite ID
    UtenteLog findById(int id);

    // Ottiene tutti gli utenti di un certo ruolo
    List<UtenteLog> findByRuolo(cs.unicam.it.Gestori.Ruolo ruolo);

    // Controlla se un'email è già registrata
    boolean existsByEmail(String email);

//    // Ottiene tutti gli utenti in attesa di approvazione
//    @Query("SELECT u FROM HandlerGestorePiattaforma h WHERE u.approvato = false")
//    List<UtenteLog> findUtentiInAttesa();
//
//    // Ottiene tutti gli utenti approvati
//    @Query("SELECT u FROM UtenteLog u WHERE u.approvato = true")
//    List<UtenteLog> findUtentiApprovati();
}