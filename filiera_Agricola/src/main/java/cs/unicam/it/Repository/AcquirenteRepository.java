package cs.unicam.it.Repository;

import cs.unicam.it.Utenti.Acquirente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AcquirenteRepository extends JpaRepository<Acquirente, Integer> {

    // Trova un acquirente tramite email
    Acquirente findByEmail(String email);

    // Ottiene tutti gli acquirenti con carrelli attivi
    @Query("SELECT a FROM Acquirente a WHERE SIZE(a.carrello.prodottiCarrello) > 0")
    List<Acquirente> findAcquirentiConCarrelliAttivi();
}