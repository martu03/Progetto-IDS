package cs.unicam.it.Repository;

import cs.unicam.it.Carrello.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {
    // Metodi personalizzati qui
}