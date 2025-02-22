package cs.unicam.it.Controller;

import cs.unicam.it.Utenti.Acquirente;
import cs.unicam.it.Repository.AcquirenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/acquirenti")
public class AcquirenteController {

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    // GET /api/acquirenti
    @GetMapping
    public ResponseEntity<List<Acquirente>> getAllAcquirenti() {
        List<Acquirente> acquirenti = acquirenteRepository.findAll();
        return ResponseEntity.ok(acquirenti); // Restituisce 200 OK con la lista delle aziende
    }

    // GET /api/acquirenti/{id}
    // Ottiene un acquirente specifico tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<Acquirente> getAcquirenteById(@PathVariable int id) {
        Optional<Acquirente> acquirenteOptional = acquirenteRepository.findById(id);
        return acquirenteOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Restituisce 404 Not Found se non trovato
    }

    @PostMapping("/crea-acquirente")
    public ResponseEntity<Acquirente> createAcquirente(@RequestBody Acquirente acquirente) {
        try {
            // Salva l'acquirente nel database
            // Se il carrello è già inizializzato, verrà salvato automaticamente grazie a CascadeType.ALL
            Acquirente savedAcquirente = acquirenteRepository.save(acquirente);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAcquirente);
        } catch (Exception e) {
            // Gestisci eventuali errori
            e.printStackTrace(); // Oppure registra l'errore in modo appropriato
            return ResponseEntity.badRequest().body(null);
        }
    }

    // PUT /api/acquirenti/{id}
    // Aggiorna un acquirente esistente
    @PutMapping("/{id}")
    public ResponseEntity<Acquirente> updateAcquirente(@PathVariable int id, @RequestBody Acquirente acquirenteDetails) {
        Optional<Acquirente> acquirenteOptional = acquirenteRepository.findById(id);

        if (acquirenteOptional.isPresent()) {
            Acquirente acquirente = acquirenteOptional.get();
            acquirente.setNome(acquirenteDetails.getNome());
            acquirente.setPassword(acquirenteDetails.getPassword());
            // Aggiungi altri setter necessari per gli attributi aggiuntivi

            Acquirente updatedAcquirente = acquirenteRepository.save(acquirente);
            return ResponseEntity.ok(updatedAcquirente); // Restituisce 200 OK con l'acquirente aggiornato
        } else {
            return ResponseEntity.notFound().build(); // Restituisce 404 Not Found
        }
    }

    // DELETE /api/acquirenti/{id}
    // Elimina un acquirente specifico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcquirente(@PathVariable int id) {
        if (acquirenteRepository.existsById(id)) {
            acquirenteRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Restituisce 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Restituisce 404 Not Found
        }
    }
}