package cs.unicam.it.Utenti;

import cs.unicam.it.Facade.SistemaFacade;
import cs.unicam.it.Mappa.Mappa;

public class UtenteGenerico {

    private SistemaFacade sistemaFacade;

    // Costruttore che riceve il riferimento al gestore piattaforma
    public UtenteGenerico(SistemaFacade sistemaFacade) {
        this.sistemaFacade = sistemaFacade;
    }

    // Effettua il login
//    public void effettuaLogin(String email, String password) {
//        sistemaFacade.login(email, password);
//    }

    // Visualizza la mappa
    public void visualizzaMappa() {
        Mappa.getInstance().visualizzaMappa();
        System.out.println("Hai consultato la mappa. Puoi visualizzare eventi e prodotti locali.");
    }

    // Crea un nuovo account
    public UtenteLog creaAccount() {
        System.out.println("Richiesta di registrazione inviata. Attendere l'approvazione del gestore.");
        return sistemaFacade.registraUtente();
    }
}
