package cs.unicam.it.Prodotto;

import java.util.Date;
import java.util.List;

public abstract class Prodotto {

    private int id;
    private static int nextID = 1;
    private String name;
    private double quantity;
    private double price;
    private Descrizione description;
    private Categoria category;
    private Certificazione certification;
    private List<Recensione> reviews;
    private Date scadenza;

    public Prodotto() {
        this.id = nextID++;
    }

    public int getId() {
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

    public Date getScadenza() {
        return scadenza;
    }

    public abstract ProdottoSingolo addProduct(ProdottoSingolo singleProduct);

    public abstract ProdottoSingolo removeProduct(ProdottoSingolo singleProduct);

    public abstract List<ProdottoSingolo> getChild();

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(Descrizione description) {
        this.description = description;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public void setCertification(Certificazione certification) {
        this.certification = certification;
    }

    public void setReviews(List<Recensione> reviews) {
        this.reviews = reviews;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", description=" + (description != null ? description.getDetails() : "null") +
                ", category=" + (category != null ? category.name() : "null") +
                ", certification=" + (certification != null ? certification.toString() : "null") +
                ", reviews=" + (reviews != null ? reviews.toString() : "null") +
                ", scadenza=" + (scadenza != null ? scadenza.toString() : "null") +
                '}';
    }

    //@Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        Prodotto product = (Prodotto) obj;
//        return id.equals(product.id);
//    }

//    @Override
//    public int hashCode() {
//        return id.hashCode();
//    }
}
