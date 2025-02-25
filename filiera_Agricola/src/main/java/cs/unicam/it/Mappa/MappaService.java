package cs.unicam.it.Mappa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappaService {

    private final GeolocalizzazioneRepository geolocalizzazioneRepository;

    public MappaService(GeolocalizzazioneRepository geolocalizzazioneRepository) {
        this.geolocalizzazioneRepository = geolocalizzazioneRepository;
    }

    // Ottiene tutti i punti geografici dal database
    public List<Geolocalizzazione> getAllPunti() {
        List<Geolocalizzazione> punti = geolocalizzazioneRepository.findAll();
        System.out.println("Punti recuperati dal repository: " + punti);
        return punti;
    }

    // Rimuove un punto geografico dal database
    public void rimuoviPunto(int id) {
        geolocalizzazioneRepository.deleteById(id);
    }
}