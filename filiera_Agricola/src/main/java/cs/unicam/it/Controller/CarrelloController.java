package cs.unicam.it.Controller;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Handler.HandlerAcquisti;
import cs.unicam.it.Handler.HandlerCarrelli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrelli")
public class CarrelloController {

    @Autowired
    private HandlerCarrelli handlerCarrelli;

    @Autowired
    private HandlerAcquisti handlerAcquisti;

    // Aggiungi un carrello
    @PostMapping("/aggiungi")
    public String aggiungiCarrello(@RequestBody Carrello carrello) {
        handlerCarrelli.aggiungiCarrello(carrello);
        return "Carrello aggiunto.";
    }

    // Rimuovi un carrello
    @PostMapping("/rimuovi")
    public String rimuoviCarrello(@RequestBody Carrello carrello) {
        handlerCarrelli.rimuoviCarrello(carrello);
        return "Carrello rimosso.";
    }

    // Ottieni tutti i carrelli attivi
    @GetMapping
    public List<Carrello> getCarrelliAttivi() {
        return handlerCarrelli.getCarrelliAttivi();
    }

    // Conferma un acquisto
    @PostMapping("/conferma-acquisto")
    public String confermaAcquisto(@RequestBody Carrello carrello) {
        if (handlerAcquisti.confermaAcquisto(carrello)) {
            return "Acquisto confermato.";
        } else {
            return "Acquisto annullato.";
        }
    }
}