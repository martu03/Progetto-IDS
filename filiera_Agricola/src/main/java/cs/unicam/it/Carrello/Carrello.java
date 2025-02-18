package cs.unicam.it.Carrello;

import cs.unicam.it.Handler.HandlerCarrelli;
import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Prodotto;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Timestamp timestamp;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "carrello_id") // Chiave esterna per la relazione con ItemCarrello
    private final List<ItemCarrello> prodottiCarrello = new ArrayList<>();

    public Carrello() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public void visualizzaCarrello() {
        for (ItemCarrello item : prodottiCarrello) {
            Prodotto prodotto = item.getProdotto();
            System.out.println(
                    prodotto.getNome() + " - Quantità: " + item.getQuantita() +
                            " - Prezzo Unitario: " + prodotto.getPrezzo()
            );
        }
    }

    // Aggiunge un prodotto al carrello
    public void aggiungiProdotto(int IDProdotto, int quantita) {
        Prodotto prodotto = getProdottoByID(IDProdotto);
        if (prodotto.verificaDisponibilita(quantita)) {
            if(prodottiCarrello.isEmpty())
                HandlerCarrelli.getInstance().aggiungiCarrello(this);
            else
                resetTimestamp();
            if(getItemCarrello(prodotto) != null) {
                modificaQuantita(IDProdotto, getItemCarrello(prodotto).getQuantita() + quantita);
            }else{
                ItemCarrello item = new ItemCarrello(prodotto, quantita);
                prodottiCarrello.add(item);
                prodotto.modificaQuantita(-quantita);
            }
        } else {
            System.out.println("Quantità richiesta non disponibile.");
        }
    }

    public void rimuoviProdotto(int IDProdotto) {
        Prodotto prodotto = getProdottoByID(IDProdotto);
        ItemCarrello item = getItemCarrello(prodotto);
        if(item != null) {
            prodotto.modificaQuantita(item.getQuantita());
            prodottiCarrello.remove(item);
            resetTimestamp();
        }else{
            System.out.println("Il prodotto non è presente nel carrello.");
        }
    }

    public void modificaQuantita(int IDProdotto, int nuovaQuantita) {
        Prodotto prodotto = getProdottoByID(IDProdotto);
        ItemCarrello item = getItemCarrello(prodotto);
        if (item == null) {
            System.out.println("Il prodotto non è presente nel carrello.");
            return;
        }

        if (nuovaQuantita == 0 || nuovaQuantita < 0) {
            rimuoviProdotto(IDProdotto);
            return;
        }

        int vecchiaQuantita = item.getQuantita();
        int delta = nuovaQuantita - vecchiaQuantita;

        if (delta < 0) {
            prodotto.modificaQuantita(Math.abs(delta));
            item.setQuantita(nuovaQuantita);
        } else if (delta > 0) {
            if (!prodotto.verificaDisponibilita(delta)) {
                System.out.println("Errore: La nuova quantità richiesta non è disponibile.");
                return;
            }
            prodotto.modificaQuantita(-delta);
            item.setQuantita(nuovaQuantita);
        }

        resetTimestamp();
    }

    public void svuota() {
        for (ItemCarrello item : prodottiCarrello) {
            Prodotto prodotto = item.getProdotto();
            rimuoviProdotto(prodotto.getId());
        }
        this.timestamp = null;
        HandlerCarrelli.getInstance().rimuoviCarrello(this);
    }

    public ItemCarrello getItemCarrello(Prodotto prodotto) {
        for (ItemCarrello item : prodottiCarrello) {
            if (item.getProdotto().equals(prodotto)) {
                return item;
            }
        }
        return null;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void resetTimestamp() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public List<ItemCarrello> getProdottiCarrello() {
        return prodottiCarrello;
    }

    private Prodotto getProdottoByID(int id) {
        return Marketplace.getInstance().getProdottoById(id);
    }

}
