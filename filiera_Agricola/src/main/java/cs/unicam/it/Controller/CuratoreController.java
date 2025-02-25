package cs.unicam.it.Controller;

import cs.unicam.it.Handler.HandlerProdottiCuratore;
import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.ProdottoPacchetto;
import cs.unicam.it.Prodotto.Stato;
import cs.unicam.it.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curatore")
public class CuratoreController {

    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private HandlerProdottiCuratore handlerProdottiCuratore;

    @GetMapping("/da-validare")
    public ResponseEntity<List<Prodotto>> getProdottiDaValidare() {
        List<Prodotto> prodotti = prodottoRepository.findProdottoByStato(Stato.IN_ATTESA);

        // Filtra i prodotti per escludere quelli con categoria COMPONENTE_PACCHETTO
        List<Prodotto> prodottiDaValidare = prodotti.stream()
                .filter(prodotto -> !prodotto.getCategoria().equals(Categoria.COMPONENTE_PACCHETTO))
                .toList();

        return ResponseEntity.ok(prodottiDaValidare);
    }

    // POST /api/curatore/valida-tutti
    @PostMapping("/valida-tutti")
    public ResponseEntity<String> validaTutti() {
        List<Prodotto> prodottiDaValidare = handlerProdottiCuratore.getProdottiDaValidare();
        for (Prodotto prodotto : prodottiDaValidare) {
            prodotto.setStato(Stato.IN_VENDITA);
            if(prodotto instanceof ProdottoPacchetto) {
                ((ProdottoPacchetto) prodotto).getChild().forEach(p -> p.setStato(Stato.IN_VENDITA));
            }
            handlerProdottiCuratore.aggiungiProdotto(prodotto);// Imposta lo stato a IN_VENDITA
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Tutti i prodotti sono stati validati.");
    }

    // POST /api/curatore/valida-parziale
    @PostMapping("/valida-parziale/{idDaValidare}")
    public ResponseEntity<String> validaParziale(@PathVariable List<Integer> idDaValidare) {
        List<Prodotto> prodottiDaValidare = handlerProdottiCuratore.getProdottiDaValidare();
        // Rimuove i prodotti da validare

        prodottiDaValidare.removeIf(prodotto -> prodotto.getCategoria().equals(Categoria.COMPONENTE_PACCHETTO));

        for (Prodotto prodotto : prodottiDaValidare) {
            if (idDaValidare.contains(prodotto.getId())) {
                prodotto.setStato(Stato.IN_VENDITA);
                if(prodotto instanceof ProdottoPacchetto) {
                    ((ProdottoPacchetto) prodotto).getChild().forEach(p -> p.setStato(Stato.IN_VENDITA));
                }
                handlerProdottiCuratore.aggiungiProdotto(prodotto); // Salva il prodotto aggiornato
            }
        }
            handlerProdottiCuratore.svuotaLista(); // Svuota la lista
        return ResponseEntity.status(HttpStatus.CREATED).body("Prodotti validati parzialmente.");
    }


    @DeleteMapping("/non-valida")
    public ResponseEntity<String> nonValida() {
        handlerProdottiCuratore.svuotaLista(); // Svuota la lista senza validare nulla
        return ResponseEntity.ok("Nessun prodotto è stato validato.");
    }
}