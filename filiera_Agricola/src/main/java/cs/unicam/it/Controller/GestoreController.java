package cs.unicam.it.Controller;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Repository.CarrelloRepository;
import cs.unicam.it.Utenti.UtenteLog;
import cs.unicam.it.Repository.UtenteLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gestore")
public class GestoreController {

    @Autowired
    private UtenteLogRepository utenteLogRepository;
    @Autowired
    private CarrelloRepository carrelloRepository;

    // Ottieni il gestore piattaforma
    @GetMapping
    public ResponseEntity<UtenteLog> getGestore() {
        Optional<UtenteLog> gestoreOptional = utenteLogRepository.findGestori().stream().findFirst();
        return gestoreOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/utenti")
    public ResponseEntity<List<UtenteLog>> test() {
        return ResponseEntity.ok(utenteLogRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteLog> getUtenteById(@PathVariable int id) {
        Optional<UtenteLog> acquirenteOptional = utenteLogRepository.findById(id);
        return acquirenteOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ottieni la lista degli utenti in attesa di approvazione
    @GetMapping("/utenti-in-attesa")
    public ResponseEntity<List<UtenteLog>> getUtentiInAttesa() {
        List<UtenteLog> utentiInAttesa = utenteLogRepository.findUtenteLogByApprovato(false);
        return ResponseEntity.ok(utentiInAttesa);
    }

    // Ottieni la lista degli utenti approvati
    @GetMapping("/utenti-approvati")
    public ResponseEntity<List<UtenteLog>> getUtentiApprovati() {
        List<UtenteLog> utentiApprovati = utenteLogRepository.findUtenteLogByApprovato(true);
        return ResponseEntity.ok(utentiApprovati);
    }

    @GetMapping("/curatori")
    public ResponseEntity<List<UtenteLog>> getAllCuratori() {
        List<UtenteLog> curatori = utenteLogRepository.findCuratori();
        return ResponseEntity.ok(curatori);
    }

    @GetMapping("/animatori")
    public ResponseEntity<List<UtenteLog>> getAllAnimatori() {
        List<UtenteLog> animatori = utenteLogRepository.findAnimatori();
        return ResponseEntity.ok(animatori);
    }

    @GetMapping("/acquirenti")
    public ResponseEntity<List<UtenteLog>> getAllAcquirenti() {
        List<UtenteLog> acquirenti = utenteLogRepository.findAcquirenti();
        return ResponseEntity.ok(acquirenti); // Restituisce 200 OK con la lista delle aziende
    }

    @GetMapping("/aziende")
    public ResponseEntity<List<UtenteLog>> getAllAziende() {
        List<UtenteLog> aziende = utenteLogRepository.findAziende();
        return ResponseEntity.ok(aziende); // Restituisce 200 OK con la lista delle aziende
    }

    @GetMapping("/produttori")
    public ResponseEntity<List<UtenteLog>> getAllProduttori() {
        List<UtenteLog> produttori = utenteLogRepository.findProduttori();
        return ResponseEntity.ok(produttori); // Restituisce 200 OK con la lista dei produttori
    }

    @GetMapping("/trasformatori")
    public ResponseEntity<List<UtenteLog>> getAllTrasformatori() {
        List<UtenteLog> trasformatori = utenteLogRepository.findTrasformatori();
        return ResponseEntity.ok(trasformatori); // Restituisce 200 OK con la lista dei trasformatori
    }

    @GetMapping("/distributori")
    public ResponseEntity<List<UtenteLog>> getAllDistributori() {
        List<UtenteLog> distributori = utenteLogRepository.findDistributori();
        return ResponseEntity.ok(distributori); // Restituisce 200 OK con la lista dei distributori
    }

    @GetMapping("/carrelli")
    public List<Carrello> getAllCarrelli() {
        return carrelloRepository.findAll();
    }

    // Approva tutti gli utenti in attesa
    @PostMapping("/approva-tutti")
    public ResponseEntity<?> approvaTuttiUtenti() {
        List<UtenteLog> utentiDaApprovare = utenteLogRepository.findUtenteLogByApprovato(false);
        if (!utentiDaApprovare.isEmpty()) {
            for (UtenteLog utente : utentiDaApprovare) {
                utente.setApprovato(true);
                utenteLogRepository.save(utente);
            }
            return ResponseEntity.ok("Tutti gli utenti in attesa sono stati approvati.");
        } else {
            return ResponseEntity.ok("Non sono presenti utenti da approvare.");
        }
    }

    // Approva utenti selezionati
    @PostMapping("/approva-parziale/{idDaApprovare}")
    public ResponseEntity<String> approvaParziale(@PathVariable List<Integer> idDaApprovare) {
        List<UtenteLog> utentiDaApprovare = utenteLogRepository.findUtenteLogByApprovato(false);

        if (utentiDaApprovare.isEmpty()) {
            return ResponseEntity.ok("Non sono presenti utenti da approvare.");
        }

        for (UtenteLog utente : utentiDaApprovare) {
            if (idDaApprovare.contains(utente.getID())) {
                utente.setApprovato(true);
                utenteLogRepository.save(utente); // Salva il prodotto aggiornato
            }
        }
        utenteLogRepository.deleteAll(utenteLogRepository.findUtenteLogByApprovato(false));
        return ResponseEntity.ok("Utenti approvati parzialmente.");
    }

    @DeleteMapping("/non-approva")
    public ResponseEntity<String> rimuoviUtente() {
        List<UtenteLog> utentiDaApprovare = utenteLogRepository.findUtenteLogByApprovato(false);
        if (utentiDaApprovare.isEmpty()) {
            return ResponseEntity.ok("Non sono presenti utenti da approvare.");
        }

        utenteLogRepository.deleteAll(utenteLogRepository.findUtenteLogByApprovato(false));
        return ResponseEntity.ok("Tutti gli utenti sono stati rifiutati.");
    }
}