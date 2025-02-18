package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.ProdottoSingolo;
import cs.unicam.it.Prodotto.ProdottoSingoloBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class PacchettoInputHandler extends ProdottoInputHandler {

    private static PacchettoInputHandler instance = null;

    private PacchettoInputHandler() {
        super();
    }

    public static PacchettoInputHandler getInstance() {
        if (instance == null) {
            instance = new PacchettoInputHandler();
        }
        return instance;
    }

    // Chiedi la lista di prodotti (specifico per il pacchetto)
    public List<Prodotto> chiediListaProdotti() {
        List<Prodotto> listaProdotti = new ArrayList<>();
        boolean continua = true;
        while (continua) {
            System.out.println("Aggiungi un prodotto al pacchetto:");
            Prodotto prodotto = ProdottoSingoloBuilder.getInstance().build();
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