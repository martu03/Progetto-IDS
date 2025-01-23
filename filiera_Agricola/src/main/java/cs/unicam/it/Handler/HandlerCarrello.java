package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Prodotto.Prodotto;

public class HandlerCarrello {
    private Carrello carrello;
    private final HandlerMarketplace handlerMarketplace;
    private final HandlerScadenzaCarrello handlerScadenzaCarrello;


    public HandlerCarrello(Carrello carrello, HandlerMarketplace handlerMarketplace, HandlerScadenzaCarrello handlerScadenzaCarrello) {
        this.carrello = carrello;
        this.handlerMarketplace = handlerMarketplace;
        this.handlerScadenzaCarrello = handlerScadenzaCarrello;
    }

    // TODO: come salviamo la quantità del prodotto?
    public boolean aggiungiProdotto(Prodotto prodotto, int quantita) {
        Prodotto prodottoEsistente = carrello.getProdotti().stream()
                .filter(p -> p.equals(prodotto))
                .findFirst()
                .orElse(null);

        if (prodottoEsistente != null) {
            int nuovaQuantita = prodottoEsistente.getQuantity() + quantita;
            return modificaQuantitaProdotto(prodottoEsistente, nuovaQuantita);
        }

        if (handlerMarketplace.isDisponibile(prodotto, quantita)) {
            prodotto.setQuantity(quantita);
            carrello.getProdotti().add(prodotto);
            carrello.resetScadenza();
            return true;
        }

        System.out.println("Prodotto non disponibile");
        return false;
    }


    public boolean rimuoviProdotto(Prodotto prodotto) {
        if (carrello.getProdotti().contains(prodotto)) {
            carrello.getProdotti().remove(prodotto);
            carrello.resetScadenza();
            return true;
        } else {
            System.out.println("Prodotto non presente nel carrello");
            return false;
        }
    }

    // TODO: Il nuovo valore viene aggiunto o sottratto a quello attuale?
    public boolean modificaQuantitaProdotto(Prodotto prodotto, int nuovaQuantita) {
        if (!carrello.getProdotti().contains(prodotto)) {
            System.out.println("Prodotto non presente nel carrello");
            return false;
        }

        if (nuovaQuantita < 0) {
            System.out.println("Quantità non valida");
            return false;
        }

        int variazioneQuantita = nuovaQuantita - prodotto.getQuantity();

        if (variazioneQuantita > 0 && !handlerMarketplace.isDisponibile(prodotto, variazioneQuantita)) {
            System.out.println("Quantità richiesta non disponibile");
            return false;
        }

        prodotto.setQuantity(nuovaQuantita);
        carrello.resetScadenza();
        System.out.println("Quantità modificata");
        return true;
    }

    public void svuotaCarrello() {
        if (handlerScadenzaCarrello.isScaduto()) {
            carrello.svuotaCarrello();
            System.out.println("Carrello scaduto");
        }
    }

}
