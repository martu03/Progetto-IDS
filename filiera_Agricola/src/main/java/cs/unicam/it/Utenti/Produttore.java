package cs.unicam.it.Utenti;

// Classe per il produttore
// Il produttore non può creare pacchetti di prodotti
public class Produttore extends Azienda {

    public Produttore(String nome, String email, String password) {
        super(nome, email, password);
    }
}