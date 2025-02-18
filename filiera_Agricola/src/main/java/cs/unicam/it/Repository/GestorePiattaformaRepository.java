package cs.unicam.it.Repository;

import cs.unicam.it.Utenti.GestorePiattaforma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestorePiattaformaRepository extends JpaRepository<GestorePiattaforma, Integer> {
    // Puoi aggiungere metodi personalizzati qui
}