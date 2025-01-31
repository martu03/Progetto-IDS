package cs.unicam.it.Eventi;

public enum TipologiaEvento {

    TOUR("Tour"),
    SAGRA("Sagra"),
    FIERA("Fiera"),
    MERCATINI("Mercatini");

    private final String displayTipologia;

    TipologiaEvento(String displayTipologia) {
        this.displayTipologia = displayTipologia;
    }

    public String getNomeTipologia() {
        return displayTipologia;
    }
}