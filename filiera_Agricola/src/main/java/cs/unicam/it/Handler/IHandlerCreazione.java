package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Prodotto;

//Interfaccia che definisce i metodi per la creazione di un prodotto
public interface IHandlerCreazione {
    // Metodo per avviare la creazione di un prodotto
    Prodotto avviaCreazione();

    // Metodo per raccogliere le informazioni del prodotto
    void form();

    // Metodo per mostrare un resoconto della creazione del prodotto
    void mostraResoconto();

    // Metodo per confermare la creazione del prodotto
    void confermaCreazione();
}
