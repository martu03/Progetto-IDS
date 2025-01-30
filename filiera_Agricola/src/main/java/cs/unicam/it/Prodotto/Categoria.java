package cs.unicam.it.Prodotto;

// Enumerazione delle categorie di prodotti
public enum Categoria {

    LIQUIDI("Liquidi"),
    CARNE("Carne"),
    DOLCI("Dolci"),
    PRODOTTI_DA_FORNO("Prodotti da forno"),
    LATTICINI("Latticini"),
    FRUTTA("Frutta"),
    VERDURA("Verdura"),
    PESCE("Pesce"),
    PACCHETTO("Pacchetto");

    private final String displayCategoria;

    Categoria(String displayCategoria) {
        this.displayCategoria = displayCategoria;
    }

    public String getNomeCategoria() {
        return displayCategoria;
    }
}
