package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.*;

import java.util.Date;
import java.util.List;

// Classe per la creazione di un pacchetto
public class HandlerCreazionePacchetto implements IHandlerCreazione{
    private ProdottoPacchetto pacchettoInCreazione;
    private PacchettoInputHandler pacchettoInputHandler;
    private static HandlerRichiestaPending handlerRichiestaPending;

    public HandlerCreazionePacchetto() {
        pacchettoInputHandler = new PacchettoInputHandler();
    }

    @Override
    public Prodotto avviaCreazione() {
        form();
        mostraResoconto();
        //controllo se l'utente conferma la creazione del pacchetto
        if(confermaCreazione()){
            return pacchettoInCreazione;
        }
        return null;
    }

    @Override
    public void form() {
        System.out.println("=== Creazione Pacchetto ===");

        // Creazione di un pacchetto vuoto
        pacchettoInCreazione = new ProdottoPacchetto(0);

        // Inserimento nome del pacchetto
        String nomePacchetto = pacchettoInputHandler.chiediNome();
        pacchettoInCreazione.setNome(nomePacchetto);

        // Inserimento descrizione del pacchetto
        String descrizionePacchetto = pacchettoInputHandler.chiediDescrizione();
        Descrizione descrizione = new Descrizione(descrizionePacchetto);
        pacchettoInCreazione.setDescrizione(descrizione);

        // Inserimento dei prodotti per il pacchetto
        List<Prodotto> prodotti = pacchettoInputHandler.chiediListaProdotti();
        pacchettoInCreazione.addProdotti(prodotti);

        // Inserimento prezzo del pacchetto
        double prezzoPacchetto = pacchettoInputHandler.chiediPrezzo();
        pacchettoInCreazione.setPrice(prezzoPacchetto);

        // Calcolare la scadenza minima dei prodotti nel pacchetto
        Date dataScadenza = getMinScadenzaPacchetto();
        pacchettoInCreazione.setScadenza(dataScadenza);

        // Scelta della categoria
        Categoria categoriaProdotto = pacchettoInputHandler.chiediCategoria();
        pacchettoInCreazione.setCategoria(categoriaProdotto);
    }

    // Calcolo della data minima di scadenza tra i prodotti nel pacchetto
    private Date getMinScadenzaPacchetto() {
        Date minDate = null;
        for (Prodotto prodotto : pacchettoInCreazione.getChild()) {
            if (minDate == null || prodotto.getScadenza().before(minDate)) {
                minDate = prodotto.getScadenza();
            }
        }
        return minDate;
    }

    @Override
    public void mostraResoconto() {
        System.out.println("=== Resoconto Pacchetto Creato ===");
        System.out.println(pacchettoInCreazione.toString());
    }

    @Override
    public boolean confermaCreazione() {
        System.out.println("Vuoi confermare la creazione del pacchetto? (S/N)");
        String conferma = pacchettoInputHandler.scanner.nextLine().trim().toUpperCase();
        if (conferma.equals("S")) {
            // Aggiungi il pacchetto ai prodotti del distributore
            System.out.println("Pacchetto creato e aggiunto ai prodotti del distributore.");
            handlerRichiestaPending.inviaRichiestaPerValidazione(pacchettoInCreazione);
            return true;
        } else {
            System.out.println("Creazione del pacchetto annullata.");
            return false;
        }
    }
}

