package cs.unicam.it.Controller;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Repository.CarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO RIVEDERE
@RestController
@RequestMapping("/api/carrelli")
public class CarrelloController {

    @Autowired
    private CarrelloRepository carrelloRepository;

    // Ottieni tutti i carrelli
    @GetMapping
    public List<Carrello> getAllCarrelli() {
        return carrelloRepository.findAll();
    }

    // Crea un nuovo carrello
    @PostMapping
    public Carrello createCarrello(@RequestBody Carrello carrello) {
        return carrelloRepository.save(carrello);
    }

    // Ottieni un carrello specifico
    @GetMapping("/{id}")
    public Carrello getCarrelloById(@PathVariable int id) {
        return carrelloRepository.findById(id).orElse(null);
    }

    // Elimina un carrello
    @DeleteMapping("/{id}")
    public void deleteCarrello(@PathVariable int id) {
        carrelloRepository.deleteById(id);
    }
}