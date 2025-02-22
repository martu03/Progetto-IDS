package cs.unicam.it.Prodotto;

import jakarta.persistence.Entity;

@Entity
public class ProdottoSingolo extends Prodotto {

    private double prezzoUnitario;

    public ProdottoSingolo() {
        super();
    }

    @Override
    public double getPrezzo() {
        return prezzoUnitario;
    }

    @Override
    public void setPrezzo(double prezzo) {
        this.prezzoUnitario = prezzo; // Imposta il prezzo unitario
    }

    public double getPrezzoTotale() {
        return prezzoUnitario * getQuantita(); // Prezzo totale
    }

    public void setPrezzoTotale(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }
}