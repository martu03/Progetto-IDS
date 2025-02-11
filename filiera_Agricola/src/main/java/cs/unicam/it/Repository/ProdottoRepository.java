package cs.unicam.it.Repository;

import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    // Trova un prodotto tramite ID
    Prodotto findById(int id);

    // Filtra i prodotti per categoria
    List<Prodotto> findByCategoria(cs.unicam.it.Prodotto.Categoria categoria);

    // Ottiene tutti i prodotti disponibili (quantità > 0)
    List<Prodotto> findByQuantitaGreaterThan(int quantita);

    // Ottiene tutti i prodotti scaduti (scadenza precedente alla data corrente)
    @Query("SELECT p FROM Prodotto p WHERE p.scadenza < :currentDate")
    List<Prodotto> findProdottiScaduti(@Param("currentDate") Date currentDate);

//    // Ottiene tutti i prodotti pubblicati sui social
//    @Query("SELECT p FROM HandlerProdottiInVendita h WHERE p = h.prodotto AND h.social = true")
//    List<Prodotto> findProdottiConSocial();
}