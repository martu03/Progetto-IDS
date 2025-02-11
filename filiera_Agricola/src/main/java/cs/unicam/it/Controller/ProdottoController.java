package cs.unicam.it.Controller;

import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.Recensione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private Marketplace marketplace;

    // Aggiungi un prodotto al marketplace
    @PostMapping("/aggiungi")
    public String aggiungiProdotto(@RequestBody Prodotto prodotto) {
        marketplace.aggiungiProdotto(prodotto);
        return "Prodotto aggiunto al marketplace.";
    }

    // Rimuovi un prodotto dal marketplace
    @PostMapping("/rimuovi")
    public String rimuoviProdotto(@RequestParam int id) {
        marketplace.rimuoviProdotto(id);
        return "Prodotto rimosso dal marketplace.";
    }

    // Ottieni tutti i prodotti nel marketplace
    @GetMapping
    public List<Prodotto> getProdotti() {
        return marketplace.getProdotti();
    }

    // Filtra i prodotti per categoria
    @GetMapping("/filtra-per-categoria")
    public List<Prodotto> filtraPerCategoria(@RequestParam Categoria categoria) {
        return marketplace.filtraPerCategoria(categoria);
    }

    // Aggiungi una recensione a un prodotto
    @PostMapping("/recensione")
    public String aggiungiRecensione(@RequestParam int idProdotto, @RequestBody Recensione recensione) {
        Prodotto prodotto = marketplace.getProdottoById(idProdotto);
        if (prodotto != null) {
            prodotto.aggiungiRecensione(recensione);
            return "Recensione aggiunta.";
        } else {
            return "Prodotto non trovato.";
        }
    }
}