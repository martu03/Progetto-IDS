package cs.unicam.it.Prodotto;

public enum Categoria {

    LIQUIDI("Liquidi"),
    CARNE("Carne"),
    DOLCI("Dolci"),
    PRODOTTI_DA_FORNO("Prodotti da forno"), //?????
    LATTICINI("Latticini"),
    FRUTTA("Frutta"),
    VERDURA("Verdura"),
    PESCE("Pesce"),
    PACCHETTO("Pacchetto");

    private final String displayCategory;

    Categoria(String displayCategory) {
        this.displayCategory = displayCategory;
    }

    public String getCategoryName() {
        return displayCategory;
    }
}
