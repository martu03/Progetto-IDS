package cs.unicam.it.Handler;


import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//LA CLASSE HA COME RESPONSABILITà LA GESTIONE E LA VALIDAZIONE DEI PRODOTTI NEL CONTESTO DEL MARKETPLACE
public class HandlerListaProdotti {
    private static final HandlerMarketplace handlerMarketplace = new HandlerMarketplace();

    public HandlerListaProdotti() {
    }

    //mostra i prodotti che il curatore ha da validare
    public boolean mostraProdottiDaValidare(List<Prodotto> listaProdotti) {
        if (listaProdotti.isEmpty()) {
            System.out.println("Non ci sono prodotti da validare.");
            return false;
        }

        System.out.println("Prodotti da validare:");
        listaProdotti.forEach(prodotto -> System.out.println(prodotto));
        return true;
    }

    public List<Prodotto> validaProdotti(List<Prodotto> listaProdotti) {
        List<Prodotto> prodottiSelezionati = selezionaProdotti(listaProdotti);
        if (prodottiSelezionati.isEmpty()) {
            System.out.println("Nessun prodotto è stato selezionato per la validazione.");
            return null;
        }

        if (confermaValidazione(prodottiSelezionati)) {
            // Aggiungi i prodotti validati al marketplace
            handlerMarketplace.aggiungiProdottiAlMarketplace(prodottiSelezionati);
            System.out.println("I prodotti sono stati validati e aggiunti al marketplace.");
            return prodottiSelezionati;
        } else {
            System.out.println("Operazione annullata. Nessun prodotto è stato validato.");
            return null;
        }
    }


    //metodo per selezionare i prodotti da validare
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
