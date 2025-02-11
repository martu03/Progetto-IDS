package cs.unicam.it.Controller;

import cs.unicam.it.Prodotto.ProdottoSingolo;
import cs.unicam.it.Repository.ProdottoSingoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti/singoli")
public class ProdottoSingoloController {

    @Autowired
    private ProdottoSingoloRepository prodottoSingoloRepository;

    // Ottieni tutti i prodotti singoli
    @GetMapping
    public List<ProdottoSingolo> getAllProdottiSingoli() {
        return prodottoSingoloRepository.findAll();
    }

    // Crea un nuovo prodotto singolo
    @PostMapping
    public ProdottoSingolo createProdottoSingolo(@RequestBody ProdottoSingolo prodotto) {
        return prodottoSingoloRepository.save(prodotto);
    }
}
