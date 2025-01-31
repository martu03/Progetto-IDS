package cs.unicam.it.Handler;

// Classe per la gestione dell'input di un prodotto singolo
public class ProdottoSingoloInputHandler extends ProdottoInputHandler {

    public int chiediQuantita() {
        System.out.print("Inserisci la quantità: ");
        return scanner.nextInt();
    }

    @Override
    public void gestisciInput() {
        System.out.println("Gestione input per prodotto singolo");
    }
}