package cs.unicam.it;

import java.util.HashSet;
import java.util.Set;

public class Utente {

    private String username;
    private Set<Prodotto> purchasedProducts;

    public Utente(String username) {
        this.username = username;
        this.purchasedProducts = new HashSet<>();
    }

    public void purchaseProduct(Prodotto product) {
        purchasedProducts.add(product);
    }

    public boolean hasPurchased(Prodotto product) {
        return purchasedProducts.contains(product);
    }

    public String getUsername() {
        return username;
    }
}