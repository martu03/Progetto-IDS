package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Utenti.Acquirente;

import java.util.HashMap;
import java.util.Map;

//handler che gestisce tutti i carrelli degli acquirenti
public class HandlerCarrello {

    private static final Map<Acquirente, Carrello> carrelli = new HashMap<>();
    private static final HandlerScadenzaCarrello handlerScadenzaCarrello = new HandlerScadenzaCarrello();
    private static final HandlerMarketplace handlerMarketplace = new HandlerMarketplace();
    private static final HandlerCalcolaTotale handlerCalcoloTotale = new HandlerCalcolaTotale();
    private static final HandlerAcquisti handlerAcquisto = new HandlerAcquisti();
    private static final HandlerScadenzaProdotto handlerScadenzaProdotto = new HandlerScadenzaProdotto();

    public void creaCarrello(Acquirente acquirente) {
        carrelli.put(acquirente, acquirente.getCarrello());
    }

    public Carrello getCarrello(Acquirente acquirente) {
        return carrelli.get(acquirente);
    }

    public boolean isScaduto(Carrello carrello) {
        if(handlerScadenzaCarrello.isScaduto(carrello)) {
            svuotaCarrello(carrello);
            return true;
        }
        handlerScadenzaCarrello.eliminaTimer(carrello);
        return false;
    }

    public void aggiungiProdotto(Acquirente acquirente, Prodotto prodotto, int quantita) {
        Carrello carrelloAcquirente = this.getCarrello(acquirente);
        carrelloAcquirente.aggiungiProdotto(prodotto, quantita);

        //aggiornare i prodotti presenti nel carrello nel marketplace
        handlerMarketplace.modificaQuantitaMarketplace(prodotto.getId(), -quantita);
        handlerScadenzaCarrello.resetScadenza(carrelloAcquirente);

        handlerScadenzaProdotto.aggiungiOsservatore(prodotto, acquirente);
    }

    public void rimuoviProdotto(Acquirente acquirente, Prodotto prodotto) {
        Carrello carrelloAcquirente = this.getCarrello(acquirente);
        carrelloAcquirente.rimuoviProdotto(prodotto);

        //aggiornare i prodotti presenti nel carrello nel marketplace
        handlerMarketplace.modificaQuantitaMarketplace(prodotto.getId(), +prodotto.getQuantita());
        handlerScadenzaCarrello.resetScadenza(carrelloAcquirente);

        handlerScadenzaProdotto.rimuoviOsservatore(prodotto, acquirente);
    }

    //nuova quantità corrisponde alla quantità desiderata dall'utente finale
    public void modificaQuantitaProdotto(Acquirente acquirente, Prodotto prodotto, int nuovaQuantita) {
        Carrello carrelloAcquirente = this.getCarrello(acquirente);
        Prodotto prodottoNelCarrello = carrelloAcquirente.getProdottoByID(prodotto.getId());

        if (prodottoNelCarrello == null) {
            System.out.println("Prodotto non presente nel carrello.");
            return;
        }

        int quantitaAttuale = prodottoNelCarrello.getQuantita();

        if (nuovaQuantita <= 0) {
            rimuoviProdotto(acquirente, prodotto);
            return;
        }

        if (nuovaQuantita < quantitaAttuale) {
            carrelloAcquirente.modificaQuantitaProdotto(prodotto, nuovaQuantita);
            handlerMarketplace.modificaQuantitaMarketplace(prodotto.getId(), quantitaAttuale - nuovaQuantita);
        } else if (handlerMarketplace.isDisponibile(prodotto.getId(), nuovaQuantita - quantitaAttuale)) {
            carrelloAcquirente.modificaQuantitaProdotto(prodotto, nuovaQuantita);
            handlerMarketplace.modificaQuantitaMarketplace(prodotto.getId(), quantitaAttuale - nuovaQuantita);
        } else {
            System.out.println("Quantità richiesta non disponibile nel marketplace.");
        }
        handlerScadenzaCarrello.resetScadenza(carrelloAcquirente);
    }

    public void svuotaCarrello(Carrello carrello) {
        carrello.svuotaCarrello();

        //aggiornare i prodotti presenti nel carrello nel marketplace
        handlerMarketplace.aggiungiProdottiAlMarketplace(carrello.getProdotti());
        handlerScadenzaCarrello.eliminaTimer(carrello);
    }

    public double calcolaTotale(Carrello carrello) {
        //chiamo il metodo per calcolare il totale
        return handlerCalcoloTotale.calcolaTotale(carrello);
    }

}
