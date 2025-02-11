package cs.unicam.it.Repository;

import cs.unicam.it.Prodotto.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecensioneRepository extends JpaRepository<Recensione, Integer> {

    // Ottiene tutte le recensioni per un prodotto specifico
    List<Recensione> findByProdotto_Id(int prodottoId);

    // Ottiene tutte le recensioni di un acquirente specifico
    List<Recensione> findByAcquirente_Id(int acquirenteId);

    // Conta il numero di recensioni per un prodotto
    long countByProdotto_Id(int prodottoId);

//    // Calcola la media dei voti per un prodotto
//    @Query("SELECT AVG(r.voto) FROM Recensione r WHERE r.prodotto.id = :prodottoId")
//    Double findMediaVotiByProdotto_Id(@Param("prodottoId") int prodottoId);
}