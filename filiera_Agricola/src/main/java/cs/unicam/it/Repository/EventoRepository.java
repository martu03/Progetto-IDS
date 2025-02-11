package cs.unicam.it.Repository;

import cs.unicam.it.Eventi.EventoFiliera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface EventoRepository extends JpaRepository<EventoFiliera, Integer> {

    // Trova un evento tramite ID
    EventoFiliera findById(int id);

    // Ottiene tutti gli eventi in una determinata data
    List<EventoFiliera> findByData(Date data);

    // Ottiene tutti gli eventi in un determinato luogo
    List<EventoFiliera> findByLuogoEvento_LatitudineAndLuogoEvento_Longitudine(double latitudine, double longitudine);

    // Ottiene tutti gli eventi scaduti (data precedente alla data corrente)
    @Query("SELECT e FROM EventoFiliera e WHERE e.data < :currentDate")
    List<EventoFiliera> findEventiScaduti(@Param("currentDate") Date currentDate);

    // Ottiene tutti gli eventi di una certa tipologia
    List<EventoFiliera> findByTipologia(cs.unicam.it.Eventi.TipologiaEvento tipologia);
}