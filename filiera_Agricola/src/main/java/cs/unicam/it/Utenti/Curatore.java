package cs.unicam.it.Utenti;

import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.ProdottoSingolo;

import java.util.ArrayList;
import java.util.List;

public class Curatore {
    private String nome;
    private List<Prodotto> listaProdotti;

    public Curatore(String nome) {
        this.nome = nome;
        this.listaProdotti = new ArrayList<>();
    }

    // TODO DA CAPIRE
    public void aggiungiProdotto(Prodotto prodotto) {
        if (listaProdotti.stream().noneMatch(p -> p.getId() == prodotto.getId())) {
            listaProdotti.add(prodotto);
            System.out.println("Prodotto aggiunto: " + prodotto.getName());
        } else {
            System.out.println("Prodotto con ID " + prodotto.getId() + " già presente.");
        }
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

    //TODO DA CAPIRE
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


//    public void modificaProdotto (Prodotto prodotto){
//        //da implementare
//    }
//
//    public void visualizzaProdotti(){
//        for (Prodotto prodotto : listaProdotti){
//            System.out.println(prodotto);
//        }}
}