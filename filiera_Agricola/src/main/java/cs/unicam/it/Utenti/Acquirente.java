package cs.unicam.it.Utenti;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Handler.HandlerAcquisti;
import cs.unicam.it.Handler.HandlerCarrello;
import cs.unicam.it.Observer.Observer;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;

// Acquirente che può acquistare prodotti
public class Acquirente extends UtenteLog implements Observer {

    HandlerAcquisti handlerAcquisti = new HandlerAcquisti();

    private Carrello carrello;
//    private Geolocalizzazione geolocalizzazione;
    private List<Prodotto> prodottiAcquistati; // Prodotti acquistati dall'acquirente

    public Acquirente(String nome, String email, String password) {
        super(nome, email, password);
        this.carrello = new Carrello();
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public void acquista(){
        handlerAcquisti.acquistaCarrello(carrello);
    }

    @Override
    public void update(Prodotto prodotto) {
        System.out.println("Acquirente " + getNome() + " notificato della scadenza del prodotto: " + prodotto.getNome());
        carrello.rimuoviProdotto(prodotto);
    }
}
