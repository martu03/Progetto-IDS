package cs.unicam.it.Mappa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/mappa")
public class MappaController {

    private final MappaService mappaService;

    @GetMapping("/")
    public String index() {
        return "index"; // Serve il file index.html dalla cartella static/
    }

    @GetMapping("/marker")
    public String markerLogo() {
        return "markerLogo"; // Serve il file markerLogo.png dalla cartella static/
    }

    public MappaController(MappaService mappaService) {
        this.mappaService = mappaService;
    }

    @GetMapping("/punti")
    public List<Geolocalizzazione> getPunti() {
        List<Geolocalizzazione> punti = mappaService.getAllPunti();
        System.out.println("Punti recuperati dal servizio: " + punti);
        return punti;
    }

//    // Endpoint per aggiungere un nuovo punto geografico
//    @PostMapping("/aggiungi")
//    public ResponseEntity<Geolocalizzazione> aggiungiPunto(@RequestBody Geolocalizzazione punto) {
//        Geolocalizzazione savedPunto = mappaService.aggiungiPunto(punto);
//        return ResponseEntity.ok(savedPunto);
//    }

//    // Endpoint per rimuovere un punto geografico
//    @DeleteMapping("/rimuovi/{id}")
//    public ResponseEntity<Void> rimuoviPunto(@PathVariable int id) {
//        mappaService.rimuoviPunto(id);
//        return ResponseEntity.noContent().build();
//    }

}