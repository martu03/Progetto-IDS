package cs.unicam.it.Handler;

import cs.unicam.it.Utenti.UtenteLog;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class HandlerGestorePiattaforma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "handlerGestorePiattaforma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UtenteLog> utentiRegistrati; // Lista di utenti registrati
    @OneToMany(mappedBy = "handlerGestorePiattaforma", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UtenteLog> utentiInAttesa;// Lista di utenti in attesa di approvazione

    public HandlerGestorePiattaforma() {
        this.utentiRegistrati = new ArrayList<>();
        this.utentiInAttesa = new ArrayList<>();
    }

    // Ottiene la lista degli utenti registrati
    public List<UtenteLog> getUtentiRegistrati() {
        return utentiRegistrati;
    }

    // Ottiene la lista degli utenti in attesa di approvazione
    public List<UtenteLog> getUtentiInAttesa() {
        return utentiInAttesa;
    }

    public void visualizzaUtentiInAttesa() {
        System.out.println("Utenti in attesa di approvazione:");
        for (UtenteLog utente : utentiInAttesa) {
            System.out.println("Account:" + utente.getID() + " " + utente.getNome());
        }
    }

    public void aggiungiUtenteInAttesa(UtenteLog utente) {
        if (!utentiInAttesa.contains(utente)) {
            utentiInAttesa.add(utente);
            System.out.println("Utente " + utente.getNome() + " aggiunto in attesa di approvazione.");
        } else {
            System.out.println("L'utente è già in attesa di approvazione.");
        }
    }

    public void aggiungiUtenteRegistrato(UtenteLog utente) {
        if (!utentiRegistrati.contains(utente)) {
            utentiRegistrati.add(utente);
            System.out.println("Utente " + utente.getNome() + " registrato sulla piattaforma.");
        } else {
            System.out.println("L'utente è già registrato sulla piattaforma.");
        }
    }

    public void svuotaUtentiInAttesa() {
        utentiInAttesa.clear();
    }

    public void rimuoviUtente(UtenteLog utente) {
        if (utentiRegistrati.remove(utente)) {
            System.out.println("Utente " + utente.getNome() + " rimosso dalla piattaforma.");
        } else {
            System.out.println("L'utente non è registrato sulla piattaforma.");
        }
    }

    public boolean verificaCredenziali(String email, String password) {
        for (UtenteLog utente : utentiRegistrati) {
            if (utente.getEmail().equals(email) && utente.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
