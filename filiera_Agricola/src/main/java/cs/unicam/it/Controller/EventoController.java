package cs.unicam.it.Controller;

import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Handler.HandlerEventi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventi")
public class EventoController {

    @Autowired
    private HandlerEventi handlerEventi;

    // Crea un nuovo evento
    @PostMapping("/crea")
    public String creaEvento(@RequestBody EventoFiliera evento) {
        handlerEventi.aggiungiEvento(evento);
        return "Evento creato.";
    }

    // Rimuovi un evento
    @PostMapping("/rimuovi")
    public String rimuoviEvento(@RequestParam int id) {
        handlerEventi.eliminaEvento(id);
        return "Evento eliminato.";
    }

    // Ottieni tutti gli eventi
    @GetMapping
    public List<EventoFiliera> getEventi() {
        return handlerEventi.getEventi();
    }
}