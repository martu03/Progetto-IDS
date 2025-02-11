package cs.unicam.it.Repository;

import cs.unicam.it.Prodotto.ProdottoSingolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProdottoSingoloRepository extends JpaRepository<ProdottoSingolo, Integer> {

    // Trova un prodotto singolo tramite ID
    ProdottoSingolo findById(int id);

    // Filtra i prodotti singoli per categoria
    List<ProdottoSingolo> findByCategoria(cs.unicam.it.Prodotto.Categoria categoria);

    // Ottiene tutti i prodotti singoli disponibili (quantità > 0)
    List<ProdottoSingolo> findByQuantitaGreaterThan(int quantita);

    // Ottiene tutti i prodotti singoli scaduti (scadenza precedente alla data corrente)
    @Query("SELECT p FROM ProdottoSingolo p WHERE p.scadenza < :currentDate")
    List<ProdottoSingolo> findProdottiScaduti(@Param("currentDate") Date currentDate);

//    // Ottiene tutti i prodotti singoli pubblicati sui social
//    @Query("SELECT p FROM ProdottoSingolo p WHERE p.pubblicatoSuSocial = true")
//    List<ProdottoSingolo> findProdottiConSocial();
}