package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;

// Classe per la gestione dell'input di un pacchetto
public class PacchettoInputHandler extends ProdottoInputHandler {

    private static HandlerCreazioneProdottoSingolo handlerCreazioneProdottoSingolo = new HandlerCreazioneProdottoSingolo();

    // Chiedi la lista di prodotti (specifico per il pacchetto)
    public List<Prodotto> chiediListaProdotti() {
        List<Prodotto> listaProdotti = new ArrayList<>();
        boolean continua = true;
        while (continua) {
            System.out.println("Aggiungi un prodotto al pacchetto:");
            Prodotto prodotto = handlerCreazioneProdottoSingolo.avviaCreazione();
            listaProdotti.add(prodotto);

            // Chiedi se continuare ad aggiungere altri prodotti
            System.out.print("Vuoi aggiungere un altro prodotto? (S/N): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("N")) {
                continua = false;
            }
        }
        return listaProdotti;
    }

    @Override
    public void gestisciInput() {
        System.out.println("Gestione input per pacchetto");
    }
}