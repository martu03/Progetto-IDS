package cs.unicam.it.Repository;

import cs.unicam.it.Carrello.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

    // Trova un carrello tramite ID
    Carrello findById(int id);

    // Ottiene tutti i carrelli associati a un acquirente
    List<Carrello> findByAcquirente_Id(int acquirenteId);

    // Ottiene i carrelli scaduti (timestamp precedente alla data corrente)
    @Query("SELECT c FROM Carrello c WHERE c.timestamp < CURRENT_TIMESTAMP")
    List<Carrello> findCarrelliScaduti();
}