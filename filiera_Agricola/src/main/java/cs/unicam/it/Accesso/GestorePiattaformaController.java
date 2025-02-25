package cs.unicam.it.Accesso;

import cs.unicam.it.Utenti.GestorePiattaforma;
import cs.unicam.it.Utenti.UtenteLog;
import cs.unicam.it.Repository.GestorePiattaformaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gestore")
public class GestorePiattaformaController {

    @Autowired
    private GestorePiattaformaRepository gestorePiattaformaRepository;

    @Autowired
    private UtenteLogRepository utenteLogRepository;


    // Ottieni il gestore piattaforma
    @GetMapping
    @PreAuthorize("hasRole('GESTORE')")
    public ResponseEntity<GestorePiattaforma> getGestore() {
        Optional<GestorePiattaforma> gestoreOptional = gestorePiattaformaRepository.findAll().stream().findFirst();
        return gestoreOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ottieni la lista degli utenti in attesa di approvazione
    @GetMapping("/utenti-in-attesa")
    @PreAuthorize("hasRole('GESTORE')")
    public ResponseEntity<List<UtenteLog>> getUtentiInAttesa() {
        Optional<GestorePiattaforma> gestoreOptional = gestorePiattaformaRepository.findAll().stream().findFirst();
        if (gestoreOptional.isPresent()) {
            return ResponseEntity.ok(gestoreOptional.get().getUtentiInAttesa());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Approva tutti gli utenti in attesa
    @PostMapping("/approva-tutti")
    @PreAuthorize("hasRole('GESTORE')")
    public ResponseEntity<String> approvaTuttiUtenti() {
        Optional<GestorePiattaforma> gestoreOptional = gestorePiattaformaRepository.findAll().stream().findFirst();
        if (gestoreOptional.isPresent()) {
            GestorePiattaforma gestore = gestoreOptional.get();
            gestore.approvaUtenti();
            gestorePiattaformaRepository.save(gestore);
            return ResponseEntity.ok("Tutti gli utenti in attesa sono stati approvati.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestore non trovato.");
        }
    }



    // Rimuovi un utente dalla piattaforma
    @DeleteMapping("/rimuovi-utente/{id}")
    @PreAuthorize("hasRole('GESTORE')")
    public ResponseEntity<String> rimuoviUtente(@PathVariable int id) {
        Optional<UtenteLog> utenteOptional = utenteLogRepository.findById(id);
        if (utenteOptional.isPresent()) {
            Optional<GestorePiattaforma> gestoreOptional = gestorePiattaformaRepository.findAll().stream().findFirst();
            if (gestoreOptional.isPresent()) {
                GestorePiattaforma gestore = gestoreOptional.get();
                gestore.rimuoviUtente(utenteOptional.get());
                gestorePiattaformaRepository.save(gestore);
                return ResponseEntity.ok("Utente rimosso con successo.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gestore non trovato.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utente non trovato.");
        }
    }

}