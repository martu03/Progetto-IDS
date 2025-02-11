package cs.unicam.it;

import cs.unicam.it.Facade.SistemaFacade;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Utenti.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        SistemaFacade sistemaFacade = new SistemaFacade();
        UtenteGenerico utente = new UtenteGenerico(sistemaFacade);
        UtenteLog acquirente = utente.creaAccount();
    }

}