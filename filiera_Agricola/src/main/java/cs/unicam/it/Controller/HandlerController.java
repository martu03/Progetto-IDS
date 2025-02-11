package cs.unicam.it.Controller;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Handler.*;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Mappa.Mappa;
import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.Recensione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/handler")
public class HandlerController {

    @Autowired
    private HandlerAcquisti handlerAcquisti;

    @Autowired
    private HandlerCarrelli handlerCarrelli;

    @Autowired
    private HandlerEventi handlerEventi;

    @Autowired
    private HandlerProdottiInVendita handlerProdottiInVendita;

    @Autowired
    private HandlerScadenzaCarrello handlerScadenzaCarrello;

    @Autowired
    private HandlerScadenzaProdotto handlerScadenzaProdotto;

    // Funzionalità per il carrello
    @PostMapping("/carrelli/aggiungi")
    public String aggiungiCarrello(@RequestBody Carrello carrello) {
        handlerCarrelli.aggiungiCarrello(carrello);
        return "Carrello aggiunto.";
    }

    @PostMapping("/carrelli/rimuovi")
    public String rimuoviCarrello(@RequestBody Carrello carrello) {
        handlerCarrelli.rimuoviCarrello(carrello);
        return "Carrello rimosso.";
    }

    @GetMapping("/carrelli")
    public List<Carrello> getCarrelliAttivi() {
        return handlerCarrelli.getCarrelliAttivi();
    }

    @PostMapping("/acquisti/conferma")
    public String confermaAcquisto(@RequestBody Carrello carrello) {
        if (handlerAcquisti.confermaAcquisto(carrello)) {
            return "Acquisto confermato.";
        } else {
            return "Acquisto annullato.";
        }
    }

    // Funzionalità per gli eventi
    @PostMapping("/eventi/aggiungi")
    public String aggiungiEvento(@RequestBody EventoFiliera evento) {
        handlerEventi.aggiungiEvento(evento);
        return "Evento aggiunto.";
    }

    @PostMapping("/eventi/rimuovi")
    public String eliminaEvento(@RequestParam int IDEvento) {
        handlerEventi.eliminaEvento(IDEvento);
        return "Evento eliminato.";
    }

    @GetMapping("/eventi")
    public List<EventoFiliera> getEventi() {
        return handlerEventi.getEventi();
    }

    // Funzionalità per i prodotti in vendita
    @PostMapping("/prodotti/aggiungi")
    public String aggiungiProdotto(@RequestBody Prodotto prodotto) {
        handlerProdottiInVendita.aggiungiProdotto(prodotto);
        return "Prodotto aggiunto.";
    }

    @PostMapping("/prodotti/rimuovi")
    public String rimuoviProdotto(@RequestParam int IDProdotto) {
        handlerProdottiInVendita.rimuoviProdotto(IDProdotto);
        return "Prodotto rimosso.";
    }

    @GetMapping("/prodotti")
    public List<Prodotto> getProdottiInVendita() {
        return handlerProdottiInVendita.getProdottiInVendita();
    }

    @GetMapping("/prodotti/filtra-per-categoria")
    public List<Prodotto> filtraPerCategoria(@RequestParam Categoria categoria) {
        return Marketplace.getInstance().filtraPerCategoria(categoria);
    }

    @PostMapping("/prodotti/recensione")
    public String aggiungiRecensione(@RequestParam int IDProdotto, @RequestBody Recensione recensione) {
        Prodotto prodotto = handlerProdottiInVendita.getProdottoById(IDProdotto);
        if (prodotto != null) {
            prodotto.aggiungiRecensione(recensione);
            return "Recensione aggiunta.";
        }
        return "Prodotto non trovato.";
    }

    // Funzionalità per la scadenza del carrello
    @PostMapping("/scadenza-carrello/start")
    public String startMonitoraggioScadenzaCarrello(@RequestParam int timeoutMinuti) {
        handlerScadenzaCarrello.monitoraScadenze();
        return "Monitoraggio scadenza carrello avviato.";
    }

    @PostMapping("/scadenza-carrello/stop")
    public String stopMonitoraggioScadenzaCarrello() {
        handlerScadenzaCarrello.stopMonitoraggioScadenze();
        return "Monitoraggio scadenza carrello fermato.";
    }

    // Funzionalità per la scadenza dei prodotti
    @PostMapping("/scadenza-prodotto/start")
    public String startMonitoraggioScadenzaProdotto(@RequestParam int timeoutMinuti) {
        handlerScadenzaProdotto.monitoraScadenze();
        return "Monitoraggio scadenza prodotto avviato.";
    }

    @PostMapping("/scadenza-prodotto/stop")
    public String stopMonitoraggioScadenzaProdotto() {
        handlerScadenzaProdotto.stopMonitoraggioScadenze();
        return "Monitoraggio scadenza prodotto fermato.";
    }

    // Funzionalità per la geolocalizzazione
    @PostMapping("/geolocalizzazione/aggiungi-punto")
    public String aggiungiPunto(@RequestBody Geolocalizzazione punto) {
        Mappa.getInstance().aggiungiPunto(punto);
        return "Punto aggiunto alla mappa.";
    }

    @PostMapping("/geolocalizzazione/rimuovi-punto")
    public String rimuoviPunto(@RequestBody Geolocalizzazione punto) {
        Mappa.getInstance().rimuoviPunto(punto);
        return "Punto rimosso dalla mappa.";
    }

    @GetMapping("/geolocalizzazione/punti")
    public List<Geolocalizzazione> getPuntiSullaMappa() {
        return Mappa.getInstance().getPuntiSullaMappa();
    }
}