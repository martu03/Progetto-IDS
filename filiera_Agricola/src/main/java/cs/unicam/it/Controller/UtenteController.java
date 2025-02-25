package cs.unicam.it.Controller;

import cs.unicam.it.Accesso.GetUtenteByToken;
import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.Stato;
import cs.unicam.it.Repository.EventoRepository;
import cs.unicam.it.Repository.ProdottoRepository;
import cs.unicam.it.Repository.UtenteLogRepository;
import cs.unicam.it.Utenti.Acquirente;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cs.unicam.it.Prodotto.Categoria.COMPONENTE_PACCHETTO;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteLogRepository utenteLogRepository;
    @Autowired
    private GetUtenteByToken getUtenteByToken;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;

    @PutMapping
    public ResponseEntity<?> updateUtente(@RequestHeader("Authorization") String authorizationHeader, @RequestBody Acquirente acquirenteDetails) {
        UtenteLog utente = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (utente != null) {
            utente.setNome(acquirenteDetails.getNome());
            utente.setPassword(acquirenteDetails.getPassword());

            utenteLogRepository.save(utente);
            return ResponseEntity.ok("Utente aggiornato con successo."); // Restituisce 200 OK con un messaggio di successo
        } else {
            return ResponseEntity.status(404).body("Utente non trovato."); // Restituisce 404 Not Found con un messaggio di errore
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUtente(@RequestHeader("Authorization") String authorizationHeader) {
        UtenteLog utente = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (utente != null) {
            utenteLogRepository.delete(utente);
            return ResponseEntity.ok("Utente eliminato con successo."); // Restituisce 200 OK con un messaggio di successo
        } else {
            return ResponseEntity.status(404).body("Utente non trovato."); // Restituisce 404 Not Found con un messaggio di errore
        }
    }

    @GetMapping("/prodotti-nel-marketplace")
    public ResponseEntity<List<Prodotto>> getAllProdotti() {
        List<Prodotto> prodotti = prodottoRepository.findProdottoByStato(Stato.IN_VENDITA);

        prodotti.removeIf(prodotto -> prodotto.getCategoria().equals(COMPONENTE_PACCHETTO));
        return ResponseEntity.ok(prodotti);
    }

    @GetMapping("/eventi")
    public ResponseEntity<List<EventoFiliera>> getAllEventi() {
        List<EventoFiliera> eventi = eventoRepository.findAll();
        return ResponseEntity.ok(eventi);
    }

}
