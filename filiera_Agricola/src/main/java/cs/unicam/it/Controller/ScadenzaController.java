package cs.unicam.it.Controller;

import cs.unicam.it.Handler.HandlerScadenzaCarrello;
import cs.unicam.it.Handler.HandlerScadenzaProdotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scadenze")
public class ScadenzaController {

    @Autowired
    private HandlerScadenzaCarrello handlerScadenzaCarrello;

    @Autowired
    private HandlerScadenzaProdotto handlerScadenzaProdotto;

    // Avvia il monitoraggio della scadenza dei carrelli
    @PostMapping("/carrello/start")
    public String startMonitoraggioScadenzaCarrello(@RequestParam int timeoutMinuti) {
        handlerScadenzaCarrello.monitoraScadenze();
        return "Monitoraggio scadenza carrello avviato.";
    }

    // Ferma il monitoraggio della scadenza dei carrelli
    @PostMapping("/carrello/stop")
    public String stopMonitoraggioScadenzaCarrello() {
        handlerScadenzaCarrello.stopMonitoraggioScadenze();
        return "Monitoraggio scadenza carrello fermato.";
    }

    // Avvia il monitoraggio della scadenza dei prodotti
    @PostMapping("/prodotto/start")
    public String startMonitoraggioScadenzaProdotto(@RequestParam int timeoutMinuti) {
        handlerScadenzaProdotto.monitoraScadenze();
        return "Monitoraggio scadenza prodotto avviato.";
    }

    // Ferma il monitoraggio della scadenza dei prodotti
    @PostMapping("/prodotto/stop")
    public String stopMonitoraggioScadenzaProdotto() {
        handlerScadenzaProdotto.stopMonitoraggioScadenze();
        return "Monitoraggio scadenza prodotto fermato.";
    }
}