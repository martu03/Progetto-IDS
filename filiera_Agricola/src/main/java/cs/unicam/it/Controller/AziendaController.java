package cs.unicam.it.Controller;

import cs.unicam.it.Utenti.Azienda;
import cs.unicam.it.Repository.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aziende")
public class AziendaController {

    @Autowired
    private AziendaRepository aziendaRepository;

    // GET /api/aziende
    @GetMapping
    public ResponseEntity<List<Azienda>> getAllAziende() {
        List<Azienda> aziende = aziendaRepository.findAll();
        return ResponseEntity.ok(aziende); // Restituisce 200 OK con la lista delle aziende
    }

    // GET /api/aziende/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Azienda> getAziendaById(@PathVariable Integer id) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(id);
        // Restituisce 200 OK con l'azienda
        // Restituisce 404 Not Found
        return aziendaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/aziende
    @PostMapping
    public ResponseEntity<Azienda> createAzienda(@RequestBody Azienda azienda) {
        Azienda savedAzienda = aziendaRepository.save(azienda);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAzienda); // Restituisce 201 Created
    }

    // PUT /api/aziende/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Azienda> updateAzienda(@PathVariable Integer id, @RequestBody Azienda aziendaDetails) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(id);
        if (aziendaOptional.isPresent()) {
            Azienda azienda = aziendaOptional.get();
            azienda.setNome(aziendaDetails.getNome());
            azienda.setEmail(aziendaDetails.getEmail());
            azienda.setPassword(aziendaDetails.getPassword());
            // Aggiungi altri setter necessari
            Azienda updatedAzienda = aziendaRepository.save(azienda);
            return ResponseEntity.ok(updatedAzienda); // Restituisce 200 OK con l'azienda aggiornata
        } else {
            return ResponseEntity.notFound().build(); // Restituisce 404 Not Found
        }
    }

    // DELETE /api/aziende/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAzienda(@PathVariable Integer id) {
        if (aziendaRepository.existsById(id)) {
            aziendaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Restituisce 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Restituisce 404 Not Found
        }
    }
}