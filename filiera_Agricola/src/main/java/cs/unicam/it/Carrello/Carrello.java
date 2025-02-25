package cs.unicam.it.Carrello;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Utenti.Acquirente;
import jakarta.persistence.*;

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
    @JoinColumn(name = "carrello_id")
    private final List<ItemCarrello> prodottiCarrello = new ArrayList<>();

    @OneToOne(mappedBy = "carrello", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Acquirente acquirente;



    public Carrello() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }

    public void setAcquirente(Acquirente acquirente) {
        this.acquirente = acquirente;
        if (acquirente != null && acquirente.getCarrello() != this) {
            acquirente.setCarrello(this); // Imposta la relazione inversa
        }
    }

//    public void visualizzaCarrello() {
//        for (ItemCarrello item : prodottiCarrello) {
//            Prodotto prodotto = item.getProdotto();
//            System.out.println(
//                    prodotto.getNome() + " - Quantità: " + item.getQuantita() +
//                            " - Prezzo Unitario: " + prodotto.getPrezzoUnitario()
//            );
//        }
//    }
//
//    // Aggiunge un prodotto al carrello
//    public void aggiungiProdotto(Prodotto prodotto, int quantita) {
//        if (prodotto.verificaDisponibilita(quantita)) {
//            if(prodottiCarrello.isEmpty())
//                HandlerCarrelli.getInstance().aggiungiCarrello(this);
//            else
//                resetTimestamp();
//            if(getItemCarrello(prodotto) != null) {
//                modificaQuantita(prodotto.getId(), getItemCarrello(prodotto).getQuantita() + quantita);
//            }else{
//                ItemCarrello item = new ItemCarrello(prodotto, quantita);
//                prodottiCarrello.add(item);
//                prodotto.modificaQuantita(-quantita);
//            }
//        } else {
//            System.out.println("Quantità richiesta non disponibile.");
//        }
//    }
//
//    public void rimuoviProdotto(int IDProdotto) {
//        Prodotto prodotto = getProdottoByID(IDProdotto);
//        ItemCarrello item = getItemCarrello(prodotto);
//        if(item != null) {
//            prodotto.modificaQuantita(item.getQuantita());
//            prodottiCarrello.remove(item);
//            resetTimestamp();
//        }else{
//            System.out.println("Il prodotto non è presente nel carrello.");
//        }
//    }
//
//    public void modificaQuantita(int IDProdotto, int nuovaQuantita) {
//        Prodotto prodotto = getProdottoByID(IDProdotto);
//        ItemCarrello item = getItemCarrello(prodotto);
//        if (item == null) {
//            System.out.println("Il prodotto non è presente nel carrello.");
//            return;
//        }
//
//        if (nuovaQuantita == 0 || nuovaQuantita < 0) {
//            rimuoviProdotto(IDProdotto);
//            return;
//        }
//
//        int vecchiaQuantita = item.getQuantita();
//        int delta = nuovaQuantita - vecchiaQuantita;
//
//        if (delta < 0) {
//            prodotto.modificaQuantita(Math.abs(delta));
//            item.setQuantita(nuovaQuantita);
//        } else if (delta > 0) {
//            if (!prodotto.verificaDisponibilita(delta)) {
//                System.out.println("Errore: La nuova quantità richiesta non è disponibile.");
//                return;
//            }
//            prodotto.modificaQuantita(-delta);
//            item.setQuantita(nuovaQuantita);
//        }
//
//        resetTimestamp();
//    }
//
//    public void svuota() {
//        for (ItemCarrello item : prodottiCarrello) {
//            Prodotto prodotto = item.getProdotto();
//            rimuoviProdotto(prodotto.getId());
//        }
//        this.timestamp = null;
//        HandlerCarrelli.getInstance().rimuoviCarrello(this);
//    }
//
//    public ItemCarrello getItemCarrello(Prodotto prodotto) {
//        for (ItemCarrello item : prodottiCarrello) {
//            if (item.getProdotto().equals(prodotto)) {
//                return item;
//            }
//        }
//        return null;
//    }
//
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void resetTimestamp() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public List<ItemCarrello> getProdottiCarrello() {
        return prodottiCarrello;
    }

    public Prodotto getProdottoByID(int IDProdotto) {
        for (ItemCarrello item : prodottiCarrello) {
            if (item.getProdotto().getId() == IDProdotto) {
                return item.getProdotto();
            }
        }
        return null;
    }
}
