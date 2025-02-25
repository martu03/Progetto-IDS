package cs.unicam.it.Controller;

import cs.unicam.it.Accesso.GetUtenteByToken;
import cs.unicam.it.Handler.HandlerProdottiCuratore;
import cs.unicam.it.Prodotto.*;
import cs.unicam.it.Repository.ProdottoRepository;
import cs.unicam.it.Repository.UtenteLogRepository;
import cs.unicam.it.Request.ProdottoPacchettoRequest;
import cs.unicam.it.Request.ProdottoSingoloRequest;
import cs.unicam.it.Utenti.Azienda;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aziende")
public class AziendaController {

    @Autowired
    private UtenteLogRepository utenteLogRepository;
    @Autowired
    private HandlerProdottiCuratore handlerProdottiCuratore;
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private GetUtenteByToken getUtenteByToken;

    @PostMapping("/crea-prodotto-singolo")
    public ResponseEntity<?> createProdottoSingolo(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProdottoSingoloRequest prodottoSingoloRequest) {
        UtenteLog azienda = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(azienda instanceof Azienda azienda1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un azienda.");
        }

        if (prodottoSingoloRequest.getScadenza().before(new Date())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        ProdottoSingoloBuilder prodottoSingoloBuilder = ProdottoSingoloBuilder.getInstance();

        prodottoSingoloBuilder.setNome(prodottoSingoloRequest.getNome())
                .setQuantita(prodottoSingoloRequest.getQuantita())
                .setDescrizione(prodottoSingoloRequest.getDescrizione())
                .setCategoria(prodottoSingoloRequest.getCategoria())
                .setCertificazione(prodottoSingoloRequest.getCertificazione())
                .setScadenza(prodottoSingoloRequest.getScadenza())
                .setPrezzoUnitario(prodottoSingoloRequest.getPrezzo())
                .setPrezzoTotale(prodottoSingoloRequest.getPrezzo() * prodottoSingoloRequest.getQuantita())
                .setStato((Stato.IN_ATTESA));

        ProdottoSingolo prodottoSingolo = prodottoSingoloBuilder.build();

        handlerProdottiCuratore.aggiungiProdotto(prodottoSingolo);
        azienda1.getIdProdottiCreati().add(prodottoSingolo.getId());
        utenteLogRepository.save(azienda1); // Salva l'azienda nel repository

        return ResponseEntity.status(HttpStatus.CREATED).body(prodottoSingolo);
    }

    @PostMapping("/crea-pacchetto")
    public ResponseEntity<?> createProdottoPacchetto(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProdottoPacchettoRequest pacchettoRequest) {
        UtenteLog azienda = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(azienda instanceof Azienda azienda1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un azienda.");
        }

        PacchettoBuilder pacchettoBuilder = PacchettoBuilder.getInstance();

        pacchettoBuilder.setNome(pacchettoRequest.getNome())
                .setQuantita(pacchettoRequest.getQuantita())
                .setDescrizione(pacchettoRequest.getDescrizione())
                .setCategoria(pacchettoRequest.getCategoria())
                .setCertificazione(pacchettoRequest.getCertificazione())
                .setStato(Stato.IN_ATTESA);

        ProdottoPacchetto pacchetto = pacchettoBuilder.build();

        ProdottoSingoloBuilder prodottoSingoloBuilder = ProdottoSingoloBuilder.getInstance();

        // Aggiungi prodotti singoli al pacchetto
        List<ProdottoSingoloRequest> prodottiSingoli = pacchettoRequest.getProdottiSingoli();
        if (prodottiSingoli != null) {
            for (ProdottoSingoloRequest prodottoSingoloRequest : prodottiSingoli) {

                prodottoSingoloBuilder.setNome(prodottoSingoloRequest.getNome())
                        .setQuantita(prodottoSingoloRequest.getQuantita())
                        .setDescrizione(prodottoSingoloRequest.getDescrizione())
                        .setCategoria(Categoria.COMPONENTE_PACCHETTO)
                        .setCertificazione(prodottoSingoloRequest.getCertificazione())
                        .setScadenza(prodottoSingoloRequest.getScadenza())
                        .setPrezzoUnitario(prodottoSingoloRequest.getPrezzo())
                        .setPrezzoTotale(prodottoSingoloRequest.getPrezzo() * prodottoSingoloRequest.getQuantita())
                        .setPrezzoTotale(prodottoSingoloRequest.getPrezzo() * prodottoSingoloRequest.getQuantita())
                        .setStato((Stato.IN_ATTESA));

                ProdottoSingolo prodottoSingolo = prodottoSingoloBuilder.build();
                pacchetto.addProdotto(prodottoSingolo);
            }
        }

        if (pacchetto.getScadenza().before(new Date())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        handlerProdottiCuratore.aggiungiProdotto(pacchetto);
        azienda1.getIdProdottiCreati().add(pacchetto.getId());
        utenteLogRepository.save(azienda1); // Salva l'azienda nel repository

        return ResponseEntity.status(HttpStatus.CREATED).body(pacchetto);
    }

    @GetMapping("/prodotti-creati")
    public ResponseEntity<?> getProdottiCreati(@RequestHeader("Authorization") String authorizationHeader) {
        UtenteLog azienda = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(azienda instanceof Azienda azienda1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un'azienda.");
        }

        List<Integer> idProdottiInVendita = azienda1.getIdProdottiCreati();
        if (idProdottiInVendita.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nessun prodotto in vendita trovato.");
        }

        List<Prodotto> prodotti = prodottoRepository.findAllById(idProdottiInVendita);
        if (prodotti.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prodotti non trovati nel repository.");
        }

        return ResponseEntity.ok(prodotti);
    }

    // PUT /api/aziende/{id}/modifica-quantita/{prodottoId}
    @PutMapping("/modifica-quantita/{prodottoId}")
    public ResponseEntity<?> modificaQuantita(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int prodottoId, @RequestParam int nuovaQuantita) {
        if (nuovaQuantita <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La quantità deve essere maggiore di zero.");
        }

        UtenteLog azienda = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(azienda instanceof Azienda azienda1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non autorizzato o non è un'azienda.");
        }

        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(prodottoId);
        if (prodottoOptional.isPresent()) {
            Prodotto prodotto = prodottoOptional.get();
            if (azienda1.getIdProdottiCreati().contains(prodottoId) && prodotto.getStato() == Stato.IN_VENDITA) {
                prodotto.setQuantita(nuovaQuantita);
                prodottoRepository.save(prodotto);
                return ResponseEntity.ok("Il prodotto è stato modificato con successo.");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Non autorizzato a modificare questo prodotto o il prodotto è ancora in attesa.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/prodotti/{id}")
    public ResponseEntity<Void> deleteProdotto(@RequestHeader("Authorization") String authorizationHeader, @PathVariable int id) {
        UtenteLog azienda = getUtenteByToken.getCurrentUtente(authorizationHeader);

        if (!(azienda instanceof Azienda azienda1)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        if (prodottoOptional.isPresent()) {
            if (azienda1.getIdProdottiCreati().contains(id)) {
                prodottoRepository.deleteById(id);
                return ResponseEntity.noContent().build(); // Restituisce 204 No Content
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Restituisce 403 Forbidden
            }
        } else {
            return ResponseEntity.notFound().build(); // Restituisce 404 Not Found
        }
    }

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