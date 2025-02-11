package cs.unicam.it.Controller;

import cs.unicam.it.Prodotto.ProdottoPacchetto;
import cs.unicam.it.Repository.ProdottoPacchettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti/pacchetti")
public class ProdottoPacchettoController {

    @Autowired
    private ProdottoPacchettoRepository prodottoPacchettoRepository;

    // Ottieni tutti i prodotti pacchetto
    @GetMapping
    public List<ProdottoPacchetto> getAllProdottiPacchetti() {
        return prodottoPacchettoRepository.findAll();
    }

    // Crea un nuovo prodotto pacchetto
    @PostMapping
    public ProdottoPacchetto createProdottoPacchetto(@RequestBody ProdottoPacchetto prodotto) {
        return prodottoPacchettoRepository.save(prodotto);
    }
}
