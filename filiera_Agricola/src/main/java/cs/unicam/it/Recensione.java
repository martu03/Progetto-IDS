package cs.unicam.it;

public class Recensione {

    private String title;
    private Prodotto product;
    private Utente user;
    private String description;
    private String rating;

    public Recensione(String title, Prodotto product, Utente user, String description, String rating) {
        this.title = title;
        this.product = product;
        this.user = user;
        this.description = description;
        this.rating = rating;
    }

//    @Override
//    public String toString() {
//        return "Review{" +
//                "user=" + user.getUsername() +
//                ", product=" + product.getName() +
//                ", content='" + content + ''' +
//        ", rating=" + rating +
//                '}';
//    }

}
