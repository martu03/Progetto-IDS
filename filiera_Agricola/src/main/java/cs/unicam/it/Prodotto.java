package cs.unicam.it;

import java.util.List;

public abstract class Prodotto {

    private String id;
    private String name;
    private double quantity;
    private double price;
    private Descrizione description;
    private Categoria category;
    private Certificazione certification;
    private List<Recensione> reviews;

    public Prodotto(String name, double quantity, double price, Descrizione description, Categoria category, Certificazione certification) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.category = category;
        this.certification = certification;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public abstract double getPrice();

    public Descrizione getDescription() {
        return description;
    }

    public Categoria getCategory() {
        return category;
    }

    public Certificazione getCertification() {
        return certification;
    }

    public List<Recensione> getReviews() {
        return reviews;
    }

    public abstract ProdottoSingolo addProduct(ProdottoSingolo singleProduct);

    public abstract ProdottoSingolo removeProduct(ProdottoSingolo singleProduct);

    public abstract List<ProdottoSingolo> getChild();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Prodotto product = (Prodotto) obj;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
