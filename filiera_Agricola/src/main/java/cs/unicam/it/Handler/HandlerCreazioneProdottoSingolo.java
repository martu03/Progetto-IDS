package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Descrizione;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.ProdottoSingolo;

import java.util.Date;
import java.util.Scanner;

public class HandlerCreazioneProdottoSingolo {
    private static HandlerRichiestaPending handlerRichiestaPending = new HandlerRichiestaPending();
    private Prodotto prodottoInCreazione;

    public Prodotto avviaCreazioneProdotto() {
        form();
        mostraResoconto();
        confermaCreazione();
        return prodottoInCreazione;
    }

    private void form() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Creazione Prodotto ===");

        // Creazione di un pacchetto vuoto
        ProdottoSingolo prodotto = new ProdottoSingolo();
        prodottoInCreazione = prodotto;

        // Inserimento nome del pacchetto
        System.out.print("Inserisci il nome del prodotto: ");
        String nomeprodotto = scanner.nextLine().trim();
        while (nomeprodotto.isEmpty()) {
            System.out.println("Errore: Il nome del pacchetto è obbligatorio!");
            System.out.print("Inserisci il nome del pacchetto: ");
            nomeprodotto = scanner.nextLine().trim();
        }
        prodotto.setName(nomeprodotto);

        // Inserimento descrizione del pacchetto
        System.out.print("Inserisci la descrizione del prodotto: ");
        String descrizioneProdotto = scanner.nextLine().trim();
        while (descrizioneProdotto.isEmpty()) {
            System.out.println("Errore: La descrizione del pacchetto è obbligatoria!");
            System.out.print("Inserisci la descrizione del pacchetto: ");
            descrizioneProdotto = scanner.nextLine().trim();
        }
        Descrizione descrizione = new Descrizione(descrizioneProdotto);
        prodotto.setDescription(descrizione);

        //inserimento prezzo del prodotto
        System.out.print("Inserisci il Prezzo del prodotto: ");
        double prezzoProdotto = scanner.nextDouble();
        while (prezzoProdotto <= 0) {
            System.out.println("Errore: Il prezzo deve essere maggiore di 0!");
            System.out.print("Prezzo prodotto: ");
            prezzoProdotto = scanner.nextDouble();
        }
        prodotto.setPrice(prezzoProdotto);

        // Inserimento quantità prodotto
        System.out.print("Inserisci la Quantità del prodotto: ");
        int quantitaProdotto = scanner.nextInt();
        while (quantitaProdotto <= 0) {
            System.out.println("Errore: La quantità deve essere maggiore di 0!");
            System.out.print("Quantità prodotto: ");
            quantitaProdotto = scanner.nextInt();
        }
        prodotto.setQuantity(quantitaProdotto);

        // Inserimento scadenza prodotto
        System.out.print("Inserisci la scadenza del prodotto (YYYY-MM-DD): ");
        String scadenzaProdotto = scanner.nextLine().trim();
        while (scadenzaProdotto.isEmpty()) {
            System.out.println("Errore: La scadenza è obbligatoria!");
            System.out.print("Scadenza prodotto (YYYY-MM-DD): ");
            scadenzaProdotto = scanner.nextLine().trim();
        }
        Date dataScadenza = new Date(scadenzaProdotto);
        prodotto.setScadenza(dataScadenza);

        // Scelta della categoria
        System.out.println("Inserisci la categoria del prodotto, scegliendo tra queste disponibili:");
        for (Categoria categoria : Categoria.values()) {
            System.out.println("- " + categoria.name());
        }

        String categoriaInput = scanner.nextLine().trim().toUpperCase();
        Categoria categoria = null;
        boolean categoriaValida = false;

        while (!categoriaValida) {
            try {
                categoria = Categoria.valueOf(categoriaInput); // Controllo se l'input è valido
                categoriaValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: Categoria non valida. Riprova.");
                System.out.println("Categorie disponibili:");
                for (Categoria cat : Categoria.values()) {
                    System.out.println("- " + cat.name());
                }
                System.out.print("Inserisci una categoria valida: ");
                categoriaInput = scanner.nextLine().trim().toUpperCase();
            } finally {
                prodotto.setCategory(categoria);
            }

        }
    }

    private void mostraResoconto() {
        prodottoInCreazione.toString();
    }

    // Metodo per confermare la creazione del prodotto
    public void confermaCreazione() {
        System.out.println("Vuoi confermare la creazione del prodotto? (S/N)");
        Scanner scanner = new Scanner(System.in);
        String conferma = scanner.nextLine().trim().toUpperCase();
        if (conferma.equals("S")) {
            handlerRichiestaPending.aggiungiProdotto(prodottoInCreazione);
        } else {
            System.out.println("Creazione del prodotto annullata.");
        }
    }
}


