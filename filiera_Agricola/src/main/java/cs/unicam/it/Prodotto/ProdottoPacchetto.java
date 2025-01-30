package cs.unicam.it.Prodotto;

import java.util.List;

public class ProdottoPacchetto extends Prodotto {

    private double prezzo;
    private List<Prodotto> prodotti;

    public ProdottoPacchetto(double prezzo) {
        super();
        this.prezzo = prezzo;
    }

    public ProdottoPacchetto(Prodotto prodottoClonazione) {
        super(prodottoClonazione);
        this.prezzo = ((ProdottoSingolo) prodottoClonazione).getPrezzo();
        this.prodotti = ((ProdottoPacchetto) prodottoClonazione).prodotti;
    }

    //metodo per calcolare il prezzo totale del pacchetto
    @Override
    public double getPrezzo() {
        double totale = 0;
        for (Prodotto prodotto : prodotti) {
            totale += prodotto.getPrezzo(); // Ricorsione sul Composite
        }
        return totale;
    }

    @Override
    public Prodotto clone(Prodotto prodottoClonazione) {
        return new ProdottoPacchetto(this);
    }

    //metodo per aggiungere prodotti al pacchetto
    public void addProdotti(List<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            prodotti.add(prodotto);
        }
    }

    public List<Prodotto> getChild() {
        return List.of();
    }
}