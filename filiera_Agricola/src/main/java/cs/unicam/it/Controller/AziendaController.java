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
    // Ottiene un'azienda specifica tramite ID
    @GetMapping("/{id}")
    public ResponseEntity<Azienda> getAziendaById(@PathVariable int id) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(id);
        return aziendaOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Restituisce 404 Not Found se non trovata
    }

    @PostMapping("/crea-azienda")
    public ResponseEntity<Azienda> createAzienda(@RequestBody Azienda azienda) {
        try {
            // Salva l'azienda nel database
            Azienda savedAzienda = aziendaRepository.save(azienda);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAzienda);
        } catch (Exception e) {
            // Gestisci eventuali errori
            return ResponseEntity.badRequest().body(null);
        }
    }

    // PUT /api/aziende/{id}
    // Aggiorna un'azienda esistente
    @PutMapping("/{id}")
    public ResponseEntity<Azienda> updateAzienda(@PathVariable int id, @RequestBody Azienda aziendaDetails) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(id);

        if (aziendaOptional.isPresent()) {
            Azienda azienda = aziendaOptional.get();
            azienda.setNome(aziendaDetails.getNome());
            azienda.setPassword(aziendaDetails.getPassword());
            // Aggiungi altri setter necessari per gli attributi aggiuntivi

            Azienda updatedAzienda = aziendaRepository.save(azienda);
            return ResponseEntity.ok(updatedAzienda); // Restituisce 200 OK con l'azienda aggiornata
        } else {
            return ResponseEntity.notFound().build(); // Restituisce 404 Not Found
        }
    }

    // DELETE /api/aziende/{id}
    // Elimina un'azienda specifica
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAzienda(@PathVariable int id) {
        if (aziendaRepository.existsById(id)) {
            aziendaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Restituisce 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Restituisce 404 Not Found
        }
    }

    // PUT /api/aziende/{id}/modifica-quantita/{prodottoId}
    @PutMapping("/{id}/modifica-quantita/{prodottoId}")
    public ResponseEntity<Void> modificaQuantita(@PathVariable int id, @PathVariable int prodottoId, @RequestParam int nuovaQuantita) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(id);
        if (aziendaOptional.isPresent()) {
            Azienda azienda = aziendaOptional.get();
            azienda.modificaQuantita(prodottoId, nuovaQuantita);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/aziende/{id}/rimuovi-prodotto/{prodottoId}
    @DeleteMapping("/{id}/rimuovi-prodotto/{prodottoId}")
    public ResponseEntity<Void> rimuoviProdotto(@PathVariable int id, @PathVariable int prodottoId) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(id);
        if (aziendaOptional.isPresent()) {
            Azienda azienda = aziendaOptional.get();
            azienda.rimuoviProdotto(prodottoId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/aziende/{id}/pubblica-su-social/{prodottoId}
    @PostMapping("/{id}/pubblica-su-social/{prodottoId}")
    public ResponseEntity<Void> pubblicaSuSocial(@PathVariable int id, @PathVariable int prodottoId) {
        Optional<Azienda> aziendaOptional = aziendaRepository.findById(id);
        if (aziendaOptional.isPresent()) {
            Azienda azienda = aziendaOptional.get();
            azienda.pubblicaSuSocial(prodottoId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}