package cs.unicam.it.Utenti;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;

// Acquirente che può acquistare prodotti
public class Acquirente extends UtenteLog {
    private Carrello carrello;
    private List<Prodotto> prodottiAcquistati; // Prodotti acquistati dall'acquirente

    public Acquirente(String nome, String email, String password) {
        super(nome, email, password);
        this.carrello = new Carrello();
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void acquista(){
        // TODO implementare
    }
}
