package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.*;

import java.util.Date;

// Classe per la creazione di un prodotto singolo
public class HandlerCreazioneProdottoSingolo implements IHandlerCreazione {
    private static HandlerRichiestaPending handlerRichiestaPending;
    private ProdottoSingolo prodottoInCreazione;
    private ProdottoInputHandler inputHandler;

    public HandlerCreazioneProdottoSingolo() {
        inputHandler = new ProdottoInputHandler();
    }

    @Override
    public Prodotto avviaCreazione() {
        form();
        mostraResoconto();
        confermaCreazione();
        return prodottoInCreazione;
    }

    @Override
    public void form() {
        System.out.println("=== Creazione Prodotto ===");

        // Creazione di un pacchetto vuoto
        prodottoInCreazione = new ProdottoSingolo();

        // Utilizzo dei metodi di input per raccogliere le informazioni
        //MANCA PER OGNI CAMPO IL CONTROLLO SULL INSERIMENTO VUOTO
        String nomeprodotto = inputHandler.chiediNome();
        prodottoInCreazione.setName(nomeprodotto);

        String descrizioneProdotto = inputHandler.chiediDescrizione();
        Descrizione descrizione = new Descrizione(descrizioneProdotto);
        prodottoInCreazione.setDescription(descrizione);

        double prezzoProdotto = inputHandler.chiediPrezzo();
        prodottoInCreazione.setPrice(prezzoProdotto);

        int quantitaProdotto = inputHandler.chiediQuantita();
        prodottoInCreazione.setQuantity(quantitaProdotto);

        Categoria categoriaProdotto = inputHandler.chiediCategoria();
        prodottoInCreazione.setCategory(categoriaProdotto);

        String scadenzaProdotto = inputHandler.chiediScadenza();
        Date scadenza = new Date(scadenzaProdotto);
        prodottoInCreazione.setScadenza(scadenza);

        Certificazione certificazioneProdotto = inputHandler.chiediCertificazione();
        prodottoInCreazione.setCertification(certificazioneProdotto);

    }

    @Override
    public void mostraResoconto() {
        System.out.println("Resoconto creazione prodotto: ");
        System.out.println(prodottoInCreazione.toString());
    }

    @Override
    public void confermaCreazione() {
        System.out.println("Vuoi confermare la creazione del prodotto? (S/N)");
        String conferma = inputHandler.scanner.nextLine().trim().toUpperCase();
        if (conferma.equals("S")) {
            handlerRichiestaPending.inviaRichiestaPerApprovazione(prodottoInCreazione);
        } else {
            System.out.println("Creazione del prodotto annullata.");
        }
    }
}
