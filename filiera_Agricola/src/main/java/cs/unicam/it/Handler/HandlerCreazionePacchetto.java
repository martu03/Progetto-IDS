package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.*;
import cs.unicam.it.Utenti.Curatore;

import java.util.Date;
import java.util.List;

// Classe per la creazione di un pacchetto
public class HandlerCreazionePacchetto implements IHandlerCreazione{
    private ProdottoPacchetto pacchettoInCreazione;
    private PacchettoInputHandler pacchettoInputHandler;
    private static HandlerRichiestaPending handlerRichiestaPending;

    public HandlerCreazionePacchetto(Curatore curatore) {
        pacchettoInputHandler = new PacchettoInputHandler();
    }

    @Override
    public Prodotto avviaCreazione() {
        form();
        mostraResoconto();
        confermaCreazione();
        return pacchettoInCreazione;
    }

    @Override
    public void form() {
        System.out.println("=== Creazione Pacchetto ===");

        // Creazione di un pacchetto vuoto
        pacchettoInCreazione = new ProdottoPacchetto();

        // Inserimento nome del pacchetto
        String nomePacchetto = pacchettoInputHandler.chiediNome();
        pacchettoInCreazione.setName(nomePacchetto);

        // Inserimento descrizione del pacchetto
        String descrizionePacchetto = pacchettoInputHandler.chiediDescrizione();
        Descrizione descrizione = new Descrizione(descrizionePacchetto);
        pacchettoInCreazione.setDescription(descrizione);

        // Inserimento dei prodotti per il pacchetto
        List<Prodotto> prodotti = pacchettoInputHandler.chiediListaProdotti();
        pacchettoInCreazione.setProducts(prodotti);

        // Inserimento prezzo del pacchetto
        double prezzoPacchetto = pacchettoInputHandler.chiediPrezzo();
        pacchettoInCreazione.setPrice(prezzoPacchetto);

        // Calcolare la scadenza minima dei prodotti nel pacchetto
        Date dataScadenza = getMinScadenzaPacchetto();
        pacchettoInCreazione.setScadenza(dataScadenza);

        // Scelta della categoria
        Categoria categoriaProdotto = pacchettoInputHandler.chiediCategoria();
        pacchettoInCreazione.setCategory(categoriaProdotto);
    }

    // Calcolo della data minima di scadenza tra i prodotti nel pacchetto
    private Date getMinScadenzaPacchetto() {
        Date minDate = null;
        for (ProdottoSingolo product : pacchettoInCreazione.getChild()) {
            if (minDate == null || product.getScadenza().before(minDate)) {
                minDate = product.getScadenza();
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
    public void confermaCreazione() {
        System.out.println("Vuoi confermare la creazione del pacchetto? (S/N)");
        String conferma = pacchettoInputHandler.scanner.nextLine().trim().toUpperCase();
        if (conferma.equals("S")) {
            // Aggiungi il pacchetto ai prodotti del distributore
            System.out.println("Pacchetto creato e aggiunto ai prodotti del distributore.");
            handlerRichiestaPending.inviaRichiestaPerApprovazione(pacchettoInCreazione);
        } else {
            System.out.println("Creazione del pacchetto annullata.");
        }
    }
}

