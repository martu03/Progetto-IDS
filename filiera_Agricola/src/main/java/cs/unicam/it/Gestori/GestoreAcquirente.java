package cs.unicam.it.Gestori;

import cs.unicam.it.Utenti.Acquirente;
import cs.unicam.it.Utenti.UtenteLog;

public class GestoreAcquirente extends GestoreRuolo {

    @Override
    public UtenteLog creaUtente(String nome, String email, String password) {
        return new Acquirente(nome, email, password);
    }
}