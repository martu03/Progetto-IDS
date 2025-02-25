package cs.unicam.it.Repository;

import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.Stato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    @Query("SELECT p FROM Prodotto p WHERE p.stato = :stato")
    List<Prodotto> findProdottoByStato(@Param("stato") Stato stato);

    @Query("SELECT p FROM Prodotto p WHERE p.categoria = :categoria")
    List<Prodotto> findProdottoByCategoria(@Param("categoria") Categoria categoria);
}
