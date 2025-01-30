package cs.unicam.it.Prodotto;

public class ProdottoSingolo extends Prodotto {

    private double prezzo;

    public ProdottoSingolo(double prezzo) {
        super();
        this.prezzo = prezzo;
    }

    public ProdottoSingolo(Prodotto prodottoClonazione) {
        super(prodottoClonazione);
        this.prezzo = ((ProdottoSingolo) prodottoClonazione).getPrezzo();
    }

    @Override
    public ProdottoSingolo clone(Prodotto prodottoClonazione) {
        return new ProdottoSingolo(this);
    }


    @Override
    public double getPrezzo() {
        return prezzo * getQuantita();
    }
}