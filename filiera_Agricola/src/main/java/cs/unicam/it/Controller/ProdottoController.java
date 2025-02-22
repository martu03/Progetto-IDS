package cs.unicam.it.Controller;

import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.ProdottoPacchetto;
import cs.unicam.it.Prodotto.ProdottoSingolo;
import cs.unicam.it.Repository.ProdottoRepository;
import cs.unicam.it.Request.ProdottoPacchettoRequest;
import cs.unicam.it.Request.ProdottoSingoloRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoRepository prodottoRepository;

    // GET /api/prodotti
    @GetMapping
    public ResponseEntity<List<Prodotto>> getAllProdotti() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        return ResponseEntity.ok(prodotti); // Restituisce 200 OK con la lista dei prodotti
    }

    // GET /api/prodotti/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProdottoById(@PathVariable int id) {
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        return prodottoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); // Restituisce 404 Not Found se non trovato
    }

    @PostMapping("/crea-prodotto-singolo")
    public ResponseEntity<ProdottoSingolo> createProdottoSingolo(@RequestBody ProdottoSingoloRequest prodottoSingoloRequest) {
        ProdottoSingolo prodottoSingolo = new ProdottoSingolo();

        // Impostazione dei campi del prodotto singolo
        prodottoSingolo.setNome(prodottoSingoloRequest.getNome());
        prodottoSingolo.setQuantita(prodottoSingoloRequest.getQuantita());
        prodottoSingolo.setDescrizione(prodottoSingoloRequest.getDescrizione());
        prodottoSingolo.setCategoria(prodottoSingoloRequest.getCategoria());
        prodottoSingolo.setCertificazione(prodottoSingoloRequest.getCertificazione());
        prodottoSingolo.setScadenza(prodottoSingoloRequest.getScadenza());
        prodottoSingolo.setPrezzoTotale(prodottoSingoloRequest.getPrezzo());

        // Salva il prodotto nel database
        ProdottoSingolo savedProdotto = (ProdottoSingolo) prodottoRepository.save(prodottoSingolo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProdotto);
    }

    @PostMapping("/crea-pacchetto")
    public ResponseEntity<ProdottoPacchetto> createProdottoPacchetto(@RequestBody ProdottoPacchettoRequest pacchettoRequest) {
        ProdottoPacchetto pacchetto = new ProdottoPacchetto();

        // Impostazione dei campi del pacchetto
        pacchetto.setNome(pacchettoRequest.getNome());
        pacchetto.setQuantita(pacchettoRequest.getQuantita());
        pacchetto.setDescrizione(pacchettoRequest.getDescrizione());
        pacchetto.setCategoria(pacchettoRequest.getCategoria());
        pacchetto.setCertificazione(pacchettoRequest.getCertificazione());
        pacchetto.setPrezzoTotale(pacchettoRequest.getPrezzoTotale());

        // Aggiungi prodotti singoli al pacchetto
        List<ProdottoSingoloRequest> prodottiSingoli = pacchettoRequest.getProdottiSingoli();
        if (prodottiSingoli != null) {
            for (ProdottoSingoloRequest prodottoSingoloRequest : prodottiSingoli) {
                ProdottoSingolo prodottoSingolo = new ProdottoSingolo();
                prodottoSingolo.setNome(prodottoSingoloRequest.getNome());
                prodottoSingolo.setQuantita(prodottoSingoloRequest.getQuantita());
                prodottoSingolo.setDescrizione(prodottoSingoloRequest.getDescrizione());
                prodottoSingolo.setCategoria(prodottoSingoloRequest.getCategoria());
                prodottoSingolo.setCertificazione(prodottoSingoloRequest.getCertificazione());
                prodottoSingolo.setScadenza(prodottoSingoloRequest.getScadenza());
                prodottoSingolo.setPrezzo(prodottoSingoloRequest.getPrezzo());
                pacchetto.setPrezzo(pacchetto.getPrezzoTotale());
                pacchetto.addProdotto(prodottoSingolo);
            }
        }


        // Salva il pacchetto nel database
        ProdottoPacchetto savedPacchetto = (ProdottoPacchetto) prodottoRepository.save(pacchetto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPacchetto);
    }

//    // POST /api/prodotti
//    @PostMapping("/crea-prodotto")
//    public ResponseEntity<Prodotto> createProdotto(@RequestBody ProdottoRequest prodottoRequest) {
//        Prodotto prodotto;
//
//        if ("singolo".equalsIgnoreCase(prodottoRequest.getType())) {
//            prodotto = new ProdottoSingolo();
//        } else if ("pacchetto".equalsIgnoreCase(prodottoRequest.getType())) {
//            prodotto = new ProdottoPacchetto();
//        } else {
//            return ResponseEntity.badRequest().body(null); // Tipo di prodotto non valido
//        }
//
//        // Impostazione dei campi comuni
//        prodotto.setNome(prodottoRequest.getNome());
//        prodotto.setQuantita(prodottoRequest.getQuantita());
//        prodotto.setDescrizione(prodottoRequest.getDescrizione());
//        prodotto.setCategoria(prodottoRequest.getCategoria());
//        prodotto.setCertificazione(prodottoRequest.getCertificazione());
//        prodotto.setScadenza(prodottoRequest.getScadenza());
//        prodotto.setPrezzo(prodottoRequest.getPrezzo());
//
//        if (prodotto instanceof ProdottoPacchetto pacchetto) {
//            // Aggiungi prodotti singoli al pacchetto
//            List<ProdottoRequest> prodottiSingoli = prodottoRequest.getProdottiSingoli();
//            if (prodottiSingoli != null) {
//                for (ProdottoRequest prodottoSingoloRequest : prodottiSingoli) {
//                    ProdottoSingolo prodottoSingolo = new ProdottoSingolo();
//                    prodottoSingolo.setNome(prodottoSingoloRequest.getNome());
//                    prodottoSingolo.setQuantita(prodottoSingoloRequest.getQuantita());
//                    prodottoSingolo.setDescrizione(prodottoSingoloRequest.getDescrizione());
//                    prodottoSingolo.setCategoria(prodottoSingoloRequest.getCategoria());
//                    prodottoSingolo.setCertificazione(prodottoSingoloRequest.getCertificazione());
//                    prodottoSingolo.setScadenza(prodottoSingoloRequest.getScadenza());
//                    prodottoSingolo.setPrezzo(prodottoSingoloRequest.getPrezzo());
//                    pacchetto.addProdotto(prodottoSingolo);
//                }
//            }
//        }
//
//        Prodotto savedProdotto = prodottoRepository.save(prodotto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedProdotto);
//    }

    // DELETE /api/prodotti/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdotto(@PathVariable int id) {
        if (prodottoRepository.existsById(id)) {
            prodottoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Restituisce 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Restituisce 404 Not Found
        }
    }

    // PUT /api/prodotti/{id}/modifica-quantita/{prodottoId}
    @PutMapping("/{id}/modifica-quantita/{prodottoId}")
    public ResponseEntity<Void> modificaQuantita(@PathVariable int id, @PathVariable int prodottoId, @RequestParam int nuovaQuantita) {
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(prodottoId);
        if (prodottoOptional.isPresent()) {
            Prodotto prodotto = prodottoOptional.get();
            prodotto.setQuantita(nuovaQuantita);
            prodottoRepository.save(prodotto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/prodotti/{id}/rimuovi-prodotto/{prodottoId}
    @DeleteMapping("/{id}/rimuovi-prodotto/{prodottoId}")
    public ResponseEntity<Void> rimuoviProdotto(@PathVariable int id, @PathVariable int prodottoId) {
        if (prodottoRepository.existsById(prodottoId)) {
            prodottoRepository.deleteById(prodottoId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/prodotti/{id}/pubblica-su-social/{prodottoId}
    @PostMapping("/{id}/pubblica-su-social/{prodottoId}")
    public ResponseEntity<Void> pubblicaSuSocial(@PathVariable int id, @PathVariable int prodottoId) {
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(prodottoId);
        if (prodottoOptional.isPresent()) {
            Prodotto prodotto = prodottoOptional.get();
            // Implementa la logica per pubblicare il prodotto sui social
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}