package cs.unicam.it.Handler;


import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Marketplace.GestoreMarketplace;
import cs.unicam.it.Utenti.Curatore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//LA CLASSE HA COME RESPONSABILITà LA GESTIONE E LA VALIDAZIONE DEI PRODOTTI NEL CONTESTO DEL MARKETPLACE

public class HandlerListaProdotti {
    private Curatore curatore;
    private GestoreMarketplace gestoreMarketplace;
    private final Marketplace marketplace = new Marketplace();

    //quando un oggetto di tipo HandlerListaProdotti viene creato,
    // viene creato anche un oggetto di tipo GestoreMarketplace
    public HandlerListaProdotti(Curatore curatore) {
        this.curatore = curatore;
        this.gestoreMarketplace = new GestoreMarketplace(marketplace);
    }


    //mostra i prodotti che il curatore ha da validare
    public void mostraProdottiDaValidare() {
        List<Prodotto> prodottiDaValidare = curatore.getListaProdotti();
        if (prodottiDaValidare.isEmpty()) {
            System.out.println("Non ci sono prodotti da validare.");
            return;
        }

        System.out.println("Prodotti da validare:");
        prodottiDaValidare.forEach(prodotto -> System.out.println(prodotto));
    }



    public void validaProdotti() {
        List<Prodotto> prodottiDaValidare = curatore.getListaProdotti();
        if (prodottiDaValidare.isEmpty()) {
            System.out.println("Non ci sono prodotti da validare.");
            return;
        }

        List<Prodotto> prodottiSelezionati = selezionaProdotti(prodottiDaValidare);
        if (prodottiSelezionati.isEmpty()) {
            System.out.println("Nessun prodotto è stato selezionato per la validazione.");
            return;
        }

        if (confermaValidazione(prodottiSelezionati)) {
            for (Prodotto prodotto : prodottiSelezionati) {
                prodotto.setValidato(true);
                gestoreMarketplace.aggiungiProdotto(prodotto); // Aggiungi un prodotto alla volta
            }
            curatore.rimuoviProdotti(prodottiSelezionati); // Rimuovi i prodotti validati dalla lista del curatore
            System.out.println("I prodotti sono stati validati e aggiunti al marketplace.");
        } else {
            System.out.println("Operazione annullata. Nessun prodotto è stato validato.");
        }
    }


    private List<Prodotto> selezionaProdotti(List<Prodotto> prodottiDaValidare) {
        System.out.println("Seleziona i prodotti da validare (inserisci gli ID separati da virgola):");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] ids = input.split(",");

        List<Prodotto> prodottiSelezionati = new ArrayList<>();
        for (String id : ids) {
            try {
                int prodottoId = Integer.parseInt(id.trim());
                Prodotto prodotto = prodottiDaValidare.stream()
                        .filter(p -> p.getId() == prodottoId)
                        .findFirst()
                        .orElse(null);

                if (prodotto != null) {
                    if (!prodottiSelezionati.contains(prodotto)) {
                        prodottiSelezionati.add(prodotto);
                    } else {
                        System.out.println("Prodotto già selezionato: " + prodottoId);
                    }
                } else {
                    System.out.println("Nessun prodotto trovato con ID: " + prodottoId);
                }
            } catch (NumberFormatException e) {
                System.out.println("ID non valido: " + id);
            }
        }
        return prodottiSelezionati;
    }

    private boolean confermaValidazione(List<Prodotto> prodottiSelezionati) {
        System.out.println("Confermi la validazione dei seguenti prodotti? (s/n)");
        prodottiSelezionati.forEach(prodotto -> System.out.println(prodotto));

        Scanner scanner = new Scanner(System.in);
        String conferma = scanner.nextLine();
        return "s".equalsIgnoreCase(conferma);
    }
}
