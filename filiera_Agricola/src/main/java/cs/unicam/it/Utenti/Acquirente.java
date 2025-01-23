package cs.unicam.it.Utenti;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;

public class Acquirente extends UtenteLog {
    private Carrello carrello;
    private List<Prodotto> prodottiAcquistati; // Prodotti acquistati dall'acquirente
    private Curatore HandlerCarrello;


    public Acquirente(String nome, String email, String password) {
        super(nome, email, password);
        this.carrello = new Carrello(null);

    }



}
