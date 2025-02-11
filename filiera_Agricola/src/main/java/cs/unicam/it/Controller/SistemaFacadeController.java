package cs.unicam.it.Controller;

import cs.unicam.it.Facade.SistemaFacade;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facade")
public class SistemaFacadeController {

    @Autowired
    private SistemaFacade sistemaFacade;

    // Effettua il login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        if (sistemaFacade.login(email, password)) {
            return "Login avvenuto con successo.";
        } else {
            return "Accesso non valido.";
        }
    }

    // Registra un nuovo utente
    @PostMapping("/registra")
    public UtenteLog registraUtente() {
        return sistemaFacade.registraUtente();
    }

    // Ottieni tutti gli utenti registrati
    @GetMapping("/utenti")
    public List<UtenteLog> getUtentiRegistrati() {
        return sistemaFacade.getGestorePiattaforma().getHandlerGestorePiattaforma().getUtentiRegistrati();
    }
}