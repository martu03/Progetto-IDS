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
        if(confermaCreazione()){
            return prodottoInCreazione;
        }
        return null;
    }

    @Override
    public void form() {
        System.out.println("=== Creazione Prodotto ===");

        // Creazione di un prodotto vuoto
        prodottoInCreazione = new ProdottoSingolo(0);

        // Utilizzo dei metodi di input per raccogliere le informazioni
        //MANCA PER OGNI CAMPO IL CONTROLLO SULL INSERIMENTO VUOTO
        String nomeprodotto = inputHandler.chiediNome();
        prodottoInCreazione.setNome(nomeprodotto);

        String descrizioneProdotto = inputHandler.chiediDescrizione();
        Descrizione descrizione = new Descrizione(descrizioneProdotto);
        prodottoInCreazione.setDescrizione(descrizione);

        double prezzoProdotto = inputHandler.chiediPrezzo();
        prodottoInCreazione.setPrice(prezzoProdotto);

        int quantitaProdotto = inputHandler.chiediQuantita();
        prodottoInCreazione.setQuantita(quantitaProdotto);

        Categoria categoriaProdotto = inputHandler.chiediCategoria();
        prodottoInCreazione.setCategoria(categoriaProdotto);

        String scadenzaProdotto = inputHandler.chiediScadenza();
        Date scadenza = new Date(scadenzaProdotto);
        prodottoInCreazione.setScadenza(scadenza);

        Certificazione certificazioneProdotto = inputHandler.chiediCertificazione();
        prodottoInCreazione.setCertificazione(certificazioneProdotto);

    }

    @Override
    public void mostraResoconto() {
        System.out.println("Resoconto creazione prodotto: ");
        System.out.println(prodottoInCreazione.toString());
    }

    @Override
    public boolean confermaCreazione() {
        System.out.println("Vuoi confermare la creazione del prodotto? (S/N)");
        String conferma = inputHandler.scanner.nextLine().trim().toUpperCase();
        if (conferma.equals("S")) {
            handlerRichiestaPending.inviaRichiestaPerValidazione(prodottoInCreazione);
            return true;
        } else {
            System.out.println("Creazione del prodotto annullata.");
            return false;
        }
    }
}
