package cs.unicam.it.Controller;

import cs.unicam.it.Facade.SistemaFacade;
import cs.unicam.it.Gestori.GestorePiattaforma;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private SistemaFacade sistemaFacade;

    @Autowired
    private GestorePiattaforma gestorePiattaforma;

    // Registra un nuovo utente
    @PostMapping("/registra")
    public UtenteLog registraUtente() {
        return sistemaFacade.registraUtente();
    }

    // Effettua il login di un utente
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        if (sistemaFacade.login(email, password)) {
            return "Login avvenuto con successo.";
        } else {
            return "Credenziali non valide.";
        }
    }

    // Ottieni tutti gli utenti registrati
    @GetMapping
    public List<UtenteLog> getAllUtenti() {
        return gestorePiattaforma.getHandlerGestorePiattaforma().getUtentiRegistrati();
    }

    // Elimina un utente
    @PostMapping("/elimina")
    public String eliminaUtente(@RequestParam String id) {
        UtenteLog utente = gestorePiattaforma.getHandlerGestorePiattaforma().getUtentiRegistrati()
                .stream()
                .filter(u -> u.getID() == Integer.parseInt(id))
                .findFirst()
                .orElse(null);

        if (utente != null) {
            gestorePiattaforma.rimuoviUtente(utente);
            return "Utente eliminato.";
        } else {
            return "Utente non trovato.";
        }
    }
}