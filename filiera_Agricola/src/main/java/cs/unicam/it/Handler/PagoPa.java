package cs.unicam.it.Handler;

// Classe per la gestione del sistema di pagamento
public class PagoPa {

    // Metodo per eseguire il pagamento tramite PagoPA
    public boolean executePayment(double totale) {
        // Simulazione di invio del pagamento a PagoPA
        System.out.println("Invio pagamento di " + totale + "€ a PagoPA...");

        //ricevo la risposta da PagoPA
        boolean pagamentoRiuscito = inviaPagamentoAPagoPA(totale);

        if (pagamentoRiuscito) {
            System.out.println("Pagamento eseguito con successo tramite PagoPA.");
            return true;
        } else {
            System.out.println("Errore nel pagamento tramite PagoPA.");
            return false;
        }
    }

    // Metodo di simulazione per l'invio del pagamento a PagoPA
    private boolean inviaPagamentoAPagoPA(double totale) {
        return Math.random() > 0.5;
    }
}
