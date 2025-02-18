package cs.unicam.it.Repository;

import cs.unicam.it.Utenti.Animatore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimatoreRepository extends JpaRepository<Animatore, Integer> {
    // Puoi aggiungere metodi personalizzati qui
}