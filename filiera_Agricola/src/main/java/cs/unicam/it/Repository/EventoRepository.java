package cs.unicam.it.Repository;

import cs.unicam.it.Eventi.EventoFiliera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<EventoFiliera, Integer> {
}
