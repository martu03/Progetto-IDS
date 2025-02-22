package cs.unicam.it.Repository;

import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
}
