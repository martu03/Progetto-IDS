package cs.unicam.it.Repository;

import cs.unicam.it.Prodotto.ProdottoPacchetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProdottoPacchettoRepository extends JpaRepository<ProdottoPacchetto, Integer> {
    // Trova un pacchetto tramite ID
    ProdottoPacchetto findById(int id);

    // Filtra i pacchetti per categoria
    List<ProdottoPacchetto> findByCategoria(cs.unicam.it.Prodotto.Categoria categoria);

    // Ottiene tutti i pacchetti disponibili (quantità > 0)
    List<ProdottoPacchetto> findByQuantitaGreaterThan(int quantita);

//    // Ottiene tutti i pacchetti scaduti (scadenza precedente alla data corrente)
//    @Query("SELECT p FROM Pacchetto p WHERE p.scadenza < :currentDate")
//    List<ProdottoPacchetto> findPacchettiScaduti(@Param("currentDate") Date currentDate);
//
//    // Ottiene tutti i pacchetti pubblicati sui social
//    @Query("SELECT p FROM Pacchetto p WHERE p.pubblicatoSuSocial = true")
//    List<ProdottoPacchetto> findPacchettiConSocial();
}