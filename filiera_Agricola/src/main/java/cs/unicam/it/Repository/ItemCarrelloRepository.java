package cs.unicam.it.Repository;

import cs.unicam.it.Carrello.ItemCarrello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCarrelloRepository extends JpaRepository<ItemCarrello, Integer> {
}