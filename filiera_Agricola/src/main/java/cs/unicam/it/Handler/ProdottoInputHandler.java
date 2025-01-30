package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Certificazione;

//InputHandler specifico per il prodotto
public class ProdottoInputHandler extends InputHandler{

    // Chiedi la quantità (specifico per il prodotto)
    public int chiediQuantita() {
        System.out.print("Inserisci la quantità: ");
        return scanner.nextInt();
    }

    @Override
    public void gestisciSpecifico() {
        // Chiedi la quantità
        chiediQuantita();
    }
}
