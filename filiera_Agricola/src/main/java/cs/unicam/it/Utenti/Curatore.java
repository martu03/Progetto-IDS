package cs.unicam.it.Utenti;

import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class Curatore {
    private String nome;
    private List<Prodotto> listaProdotti;

    public Curatore(String nome) {
        this.nome = nome;
        this.listaProdotti = new ArrayList<>();
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }


    public void rimuoviProdotto(Prodotto prodotto) {
        if (listaProdotti.remove(prodotto)) {
            System.out.println("Prodotto rimosso: " + prodotto.getName());
        } else {
            System.out.println("Prodotto non trovato: " + prodotto.getId());
        }
    }

    public void rimuoviProdotti(List<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            rimuoviProdotto(prodotto);
        }
    }

    public void visualizzaProdotti() {
        if (listaProdotti.isEmpty()) {
            System.out.println("Nessun prodotto disponibile.");
        } else {
            System.out.println("Lista prodotti del curatore " + nome + ":");
            listaProdotti.forEach(System.out::println);
        }
    }
}