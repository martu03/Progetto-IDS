package cs.unicam.it.Repository;

import cs.unicam.it.Utenti.GestorePiattaforma;
import cs.unicam.it.Utenti.Ruolo;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtenteLogRepository extends JpaRepository<UtenteLog, Integer> {

    boolean existsByEmail(String email);
    Optional<UtenteLog> findByEmail(String email);

    @Query("SELECT u FROM UtenteLog u WHERE u.approvato = :approvato")
    List<UtenteLog> findUtenteLogByApprovato(@Param("approvato") Boolean approvato);

    @Query("SELECT u FROM UtenteLog u WHERE u.ruolo = :ruolo")
    List<UtenteLog> findUtenteLogByRuolo(@Param("ruolo") Ruolo ruolo);

    default List<UtenteLog> findGestori() {
        return findUtenteLogByRuolo(Ruolo.GESTORE);
    }

    default List<UtenteLog> findCuratori() {
        return findUtenteLogByRuolo(Ruolo.CURATORE);
    }

    default List<UtenteLog> findAnimatori() {
        return findUtenteLogByRuolo(Ruolo.ANIMATORE);
    }

    default List<UtenteLog> findAcquirenti() {
        return findUtenteLogByRuolo(Ruolo.ACQUIRENTE);
    }

    default List<UtenteLog> findAziende() {
        return findUtenteLogByRuolo(Ruolo.AZIENDA);
    }

    default List<UtenteLog> findProduttori() {
        return findUtenteLogByRuolo(Ruolo.PRODUTTORE);
    }

    default List<UtenteLog> findTrasformatori() {
        return findUtenteLogByRuolo(Ruolo.TRASFORMATORE);
    }

    default List<UtenteLog> findDistributori() {
        return findUtenteLogByRuolo(Ruolo.DISTRIBUTORE);
    }
}
