package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Esterni.PagoPA;

public class HandlerAcquisti {
    // TODO: Va messo il Pending da qualche parte?
    // TODO: Da capire bene come gestire le informazioni (costo e indirizzo) nel caso d'uso "Acquistare prodotti".
    private final Carrello carrello;
    private final HandlerMarketplace handlerMarketplace;
    private final HandlerAreaPersonale handlerAreaPersonale;

    public HandlerAcquisti(Carrello carrello, HandlerMarketplace handlerMarketplace, HandlerAreaPersonale handlerAreaPersonale) {
        this.carrello = carrello;
        this.handlerMarketplace = handlerMarketplace;
        this.handlerAreaPersonale = handlerAreaPersonale;
    }

    public void acquistaCarrello() {
        System.out.println("Contenuti del carrello:");
        carrello.mostraProdotti();

        if (!richiediConfermaAcquisto()) {
            System.out.println("Acquisto annullato.");
            return;
        }

        double totale = carrello.calcolaTotale();
        boolean pagamentoRiuscito = PagoPA.eseguiPagamento(totale);

        if (!pagamentoRiuscito) {
            System.out.println("Pagamento non riuscito.");
            return;
        }

        carrello.getProdotti().forEach(prodotto ->
                handlerMarketplace.aggiornaProdottiMarketplace(prodotto, -prodotto.getQuantity())
        );
        carrello.svuotaCarrello();

        String indirizzo = handlerAreaPersonale.getIndirizzo();
        inviaAlCorriere(indirizzo);

        System.out.println("Acquisto completato con successo!");
    }

    private boolean richiediConfermaAcquisto() {
        System.out.println("Confermi l'acquisto? (s/n)");
        return true;
    }

    private void inviaAlCorriere(String indirizzo) {
        System.out.println("Ordine inviato al corriere all'indirizzo: " + indirizzo);
    }
}
