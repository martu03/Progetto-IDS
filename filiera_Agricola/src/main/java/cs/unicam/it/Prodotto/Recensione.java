package cs.unicam.it.Prodotto;

import cs.unicam.it.Utenti.Acquirente;

import java.util.Date;

public class Recensione {

    private String title;
    private Prodotto product;
    private Acquirente user;
    private String description;
    private int rating; // Valutazione da 1 a 5
    private Date date;

    public Recensione(String title, Prodotto product, Acquirente user, String description, int rating, Date date) {
        this.title = title;
        this.product = product;
        this.user = user;
        this.description = description;
        this.rating = rating;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public Prodotto getProduct() {
        return product;
    }

    public Acquirente getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "Review {" +
                "user=" + user.getNome() +
                ", product=" + product.getName() +
                ", rating=" + rating +
                ", date=" + date +
                ", content='" + description + '\'' +
                '}';
    }

}
