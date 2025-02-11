package cs.unicam.it.Repository;

import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MarketplaceRepository extends JpaRepository<Prodotto, Integer> {

    // Ottiene tutti i prodotti disponibili (quantità > 0)
    List<Prodotto> findByQuantitaGreaterThan(int quantita);

//    // Ottiene tutti i prodotti scaduti (scadenza precedente alla data corrente)
//    @Query("SELECT p FROM Prodotto p WHERE p.scadenza < :currentDate")
//    List<Prodotto> findProdottiScaduti(@Param("currentDate") Date currentDate);
//
//    // Ottiene tutti i prodotti in offerta
//    @Query("SELECT p FROM Prodotto p WHERE p.prezzoOfferta IS NOT NULL")
//    List<Prodotto> findProdottiInOfferta();

    // Ottiene tutti i prodotti di una certa azienda
    List<Prodotto> findByAzienda_Id(int aziendaId);
}