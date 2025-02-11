package cs.unicam.it.Repository;

import cs.unicam.it.Mappa.Geolocalizzazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeolocalizzazioneRepository extends JpaRepository<Geolocalizzazione, String> {

    // Trova una geolocalizzazione tramite latitudine e longitudine
    Geolocalizzazione findByLatitudineAndLongitudine(double latitudine, double longitudine);

    // Ottiene tutte le geolocalizzazioni vicine a un punto specifico
    @Query("SELECT g FROM Geolocalizzazione g WHERE g.latitudine BETWEEN :latMin AND :latMax " +
            "AND g.longitudine BETWEEN :lonMin AND :lonMax")
    List<Geolocalizzazione> findPuntiVicini(
            @Param("latMin") double latMin,
            @Param("latMax") double latMax,
            @Param("lonMin") double lonMin,
            @Param("lonMax") double lonMax
    );
}
