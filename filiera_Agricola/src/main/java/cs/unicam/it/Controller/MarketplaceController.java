package cs.unicam.it.Controller;

import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marketplace")
public class MarketplaceController {

    @Autowired
    private Marketplace marketplace;

    // Aggiungi un prodotto al marketplace
    @PostMapping("/aggiungi-prodotto")
    public String aggiungiProdotto(@RequestBody Prodotto prodotto) {
        marketplace.aggiungiProdotto(prodotto);
        return "Prodotto aggiunto al marketplace.";
    }

    // Rimuovi un prodotto dal marketplace
    @PostMapping("/rimuovi-prodotto")
    public String rimuoviProdotto(@RequestParam int id) {
        marketplace.rimuoviProdotto(id);
        return "Prodotto rimosso dal marketplace.";
    }

    // Ottieni tutti i prodotti nel marketplace
    @GetMapping
    public List<Prodotto> getProdotti() {
        return marketplace.getProdotti();
    }
}