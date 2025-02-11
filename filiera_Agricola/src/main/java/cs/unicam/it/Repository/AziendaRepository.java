package cs.unicam.it.Repository;

import cs.unicam.it.Utenti.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AziendaRepository extends JpaRepository<Azienda, Integer> {

    // Trova un'azienda tramite email
    Azienda findByEmail(String email);

    // Ottiene tutte le aziende di un certo tipo
    List<Azienda> findByRuolo(cs.unicam.it.Gestori.Ruolo ruolo);

//    // Ottiene tutte le aziende con prodotti nel marketplace
//    @Query("SELECT a FROM Azienda a WHERE SIZE(a.prodotti) > 0")
//    List<Azienda> findAziendeConProdotti();
}