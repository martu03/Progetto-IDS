package cs.unicam.it.Utenti;

import cs.unicam.it.Handler.HandlerListaProdotti;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;

// Classe per il curatore che è unico per tutto il marketplace
public class Curatore extends UtenteLog{
    private List<Prodotto> listaProdotti;
    private static final HandlerListaProdotti handlerListaProdotti = new HandlerListaProdotti();

    public Curatore(String nome, String email, String password) {
        super(nome, email, password);
        this.listaProdotti = new ArrayList<>();
    }

    public Curatore getCuratore() {
        return this;
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        listaProdotti.add(prodotto);
        System.out.println("Prodotto aggiunto: " + prodotto.getNome());
    }

    public void rimuoviProdotto(Prodotto prodotto) {
        if (listaProdotti.remove(prodotto)) {
            System.out.println("Prodotto rimosso: " + prodotto.getNome());
        } else {
            System.out.println("Prodotto non trovato: " + prodotto.getId());
        }
    }

    public void rimuoviProdotti(List<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            rimuoviProdotto(prodotto);
        }
    }

    public void validaProdotto(){
        if(!handlerListaProdotti.mostraProdottiDaValidare(listaProdotti))
            return; //non ci sono prodotti da validare

        List<Prodotto> prodottiValidati = handlerListaProdotti.validaProdotti(listaProdotti);
        //una volta che il curatore ha preso visione dei prodotti da validare, li rimuove dalla lista
        rimuoviProdotti(prodottiValidati);
    }
}