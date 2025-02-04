package cs.unicam.it.Gestori;

public enum Ruolo {

    ACQUIRENTE("Acquirente"),
    PRODUTTORE("Produttore"),
    TRASFORMATORE("Trasformatore"),
    DISTRIBUTORE("Distributore");

    private final String displayRuolo;

    Ruolo(String displayRuolo) {
        this.displayRuolo = displayRuolo;
    }

    public String getNomeRuolo() {
        return displayRuolo;
    }
}