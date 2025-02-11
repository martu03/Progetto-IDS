package cs.unicam.it.Controller;

import cs.unicam.it.Handler.HandlerProdottiCuratore;
import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curatore")
public class CuratoreController {

    @Autowired
    private HandlerProdottiCuratore handlerProdottiCuratore;

    // Visualizza i prodotti da validare
    @GetMapping("/prodotti-da-validare")
    public List<Prodotto> getProdottiDaValidare() {
        return handlerProdottiCuratore.getProdottiDaValidare();
    }

    // Valida tutti i prodotti
    @PostMapping("/valida-tutti")
    public String validaTutti() {
        handlerProdottiCuratore.svuotaLista(); // Simula la validazione
        return "Tutti i prodotti sono stati validati.";
    }

    // Valida parzialmente i prodotti
    @PostMapping("/valida-parziale")
    public String validaParziale(@RequestParam List<Integer> idProdotti) {
        idProdotti.forEach(id -> {
            Prodotto prodotto = handlerProdottiCuratore.getProdottiDaValidare()
                    .stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (prodotto != null) {
                handlerProdottiCuratore.getProdottiDaValidare().remove(prodotto);
            }
        });
        return "Prodotti validati parzialmente.";
    }
}