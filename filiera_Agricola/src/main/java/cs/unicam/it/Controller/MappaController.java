package cs.unicam.it.Controller;

import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Mappa.Mappa;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mappa")
public class MappaController {

    // Aggiungi un punto sulla mappa
    @PostMapping("/aggiungi-punto")
    public String aggiungiPunto(@RequestBody Geolocalizzazione punto) {
        Mappa.getInstance().aggiungiPunto(punto);
        return "Punto aggiunto alla mappa.";
    }

    // Rimuovi un punto dalla mappa
    @PostMapping("/rimuovi-punto")
    public String rimuoviPunto(@RequestBody Geolocalizzazione punto) {
        Mappa.getInstance().rimuoviPunto(punto);
        return "Punto rimosso dalla mappa.";
    }

    // Ottieni tutti i punti sulla mappa
    @GetMapping("/punti")
    public List<Geolocalizzazione> getPuntiSullaMappa() {
        return Mappa.getInstance().getPuntiSullaMappa();
    }
}