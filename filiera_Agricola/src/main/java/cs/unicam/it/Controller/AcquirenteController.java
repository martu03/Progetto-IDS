package cs.unicam.it.Controller;

import cs.unicam.it.Accesso.GetUtenteByToken;
import cs.unicam.it.Accesso.JwtUtil;
import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Repository.ItemCarrelloRepository;
import cs.unicam.it.Repository.ProdottoRepository;
import cs.unicam.it.Repository.UtenteLogRepository;
import cs.unicam.it.Request.AggiungiProdottoRequest;
import cs.unicam.it.Service.AcquirenteService;
import cs.unicam.it.Utenti.Acquirente;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/acquirenti")
public class AcquirenteController {

    @Autowired
    private UtenteLogRepository utenteLogRepository;
    @Autowired
    private AcquirenteService acquirenteService;
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private ItemCarrelloRepository itemCarrelloRepository;
    @Autowired
    private GetUtenteByToken getUtenteByToken;

    // Aggiunge un prodotto al carrello dell'acquirente
    @PostMapping("/aggiungi-prodotto-al-carrello")
    public ResponseEntity<String> aggiungiProdottoAlCarrello(@RequestHeader("Authorization") String authorizationHeader, @RequestBody AggiungiProdottoRequest request) {
        UtenteLog acquirente = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(acquirente instanceof Acquirente acquirente1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un acquirente.");
        }

        Prodotto prodotto = prodottoRepository.findById(request.getIdProdotto()).orElse(null);

        if (prodotto == null) {
            return ResponseEntity.badRequest().body("Prodotto non trovato.");
        } else if (prodottoRepository.findProdottoByCategoria(Categoria.COMPONENTE_PACCHETTO).contains(prodotto)) {
            return ResponseEntity.badRequest().body("Impossibile aggiungere un componente di un pacchetto al carrello.");
        }

        try {
            acquirenteService.aggiungiProdottoAlCarrello(acquirente.getID(), request.getIdProdotto(), request.getQuantita());
            return ResponseEntity.ok("Prodotto aggiunto al carrello con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante l'aggiunta del prodotto: " + e.getMessage());
        }
    }

    // Rimuove un prodotto dal carrello dell'acquirente
    @DeleteMapping("/rimuovi-prodotto-dal-carello/{prodottoId}")
    public ResponseEntity<?> rimuoviProdottoDalCarrello(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int prodottoId) {
        System.out.println("Inizio metodo rimuoviProdottoDalCarrello. Prodotto ID: " + prodottoId);

        UtenteLog acquirente = getUtenteByToken.getCurrentUtente(authorizationHeader);
        System.out.println("Utente ottenuto dal token: " + acquirente);

        if (!(acquirente instanceof Acquirente acquirente1)) {
            System.out.println("Utente non autorizzato o non è un acquirente.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un acquirente.");
        }

        System.out.println("Utente autorizzato. ID Acquirente: " + acquirente1.getID());

        try {
            acquirenteService.rimuoviProdottoDalCarrello(acquirente1.getID(), prodottoId);
            System.out.println("Prodotto rimosso dal carrello con successo. Prodotto ID: " + prodottoId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Errore durante la rimozione del prodotto dal carrello: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la rimozione del prodotto: " + e.getMessage());
        }
    }

    // Svuota il carrello dell'acquirente
    @DeleteMapping("/svuota-carrello")
    public ResponseEntity<?> svuotaCarrello(@RequestHeader("Authorization") String authorizationHeader) {
        UtenteLog acquirente = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(acquirente instanceof Acquirente acquirente1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un acquirente.");
        }

        acquirenteService.svuotaCarrello(acquirente1.getID());
        return ResponseEntity.ok().build();
    }

    // Conferma l'acquisto
    @PostMapping("/conferma-acquisto")
    public ResponseEntity<String> confermaAcquisto(@RequestHeader("Authorization") String authorizationHeader) {
        UtenteLog acquirente = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(acquirente instanceof Acquirente acquirente1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un acquirente.");
        }

        if (acquirente1.getCarrello().getProdottiCarrello().isEmpty()) {
            return ResponseEntity.badRequest().body("Il carrello è vuoto. Impossibile completare il pagamento.");
        }

        acquirenteService.confermaAcquisto(acquirente1.getID());
        return ResponseEntity.ok("Pagamento avvenuto con successo.");
    }

    // GET /api/acquirenti/{id}/carrello
    // Restituisce il carrello dell'acquirente
    @GetMapping("/carrello")
    public ResponseEntity<?> getCarrello(@RequestHeader("Authorization") String authorizationHeader) {
        UtenteLog acquirente = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(acquirente instanceof Acquirente acquirente1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un acquirente.");
        }

        return ResponseEntity.ok(acquirente1.getCarrello());
    }
}