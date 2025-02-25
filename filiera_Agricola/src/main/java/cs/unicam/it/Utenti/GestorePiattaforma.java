package cs.unicam.it.Utenti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class GestorePiattaforma extends UtenteLog {



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UtenteLog> utentiRegistrati; // Lista di utenti registrati

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UtenteLog> utentiInAttesa; // Lista di utenti in attesa di approvazione



    public GestorePiattaforma(String nome, String email, String password, Ruolo ruolo) {
        super(nome, email, password, Ruolo.GESTORE);
        this.setApprovato(true);
        this.utentiRegistrati = new ArrayList<>();
        this.utentiInAttesa = new ArrayList<>();


    }

    public GestorePiattaforma() {

    }


    // Aggiunge un utente in attesa di approvazione
    public void aggiungiUtenteInAttesa(UtenteLog utente) {
        if (!utentiInAttesa.contains(utente)) {
            utentiInAttesa.add(utente);
            System.out.println("Utente " + utente.getNome() + " aggiunto in attesa di approvazione.");
        } else {
            System.out.println("L'utente è già in attesa di approvazione.");
        }
    }

    // Aggiunge un utente registrato
    public void aggiungiUtenteRegistrato(UtenteLog utente) {
        if (!utentiRegistrati.contains(utente)) {
            utentiRegistrati.add(utente);
            System.out.println("Utente " + utente.getNome() + " registrato sulla piattaforma.");
        } else {
            System.out.println("L'utente è già registrato sulla piattaforma.");
        }
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

    public void approvaUtenti() {
        if (utentiInAttesa.isEmpty()) {
            System.out.println("Non ci sono utenti in attesa di approvazione.");
            return;
        }

        visualizzaUtentiInAttesa();

        // Chiedi la scelta dell'utente
        int scelta = getSceltaUtente();
        switch (scelta) {
            case 1: // APPROVA TUTTI GLI UTENTI
                approvaUtenti(utentiInAttesa);
                System.out.println("Tutti gli utenti sono stati approvati e registrati sulla piattaforma.");
                break;
            case 2: // NON APPROVA NESSUN UTENTE
                System.out.println("Nessun utente è stato approvato.");
                break;
            case 3: // APPROVA PARZIALMENTE GLI UTENTI
                approvaParziale();
                System.out.println("Utenti approvati parzialmente.");
                break;
            default:
                System.out.println("Scelta non valida.");
                return;
        }

        // Pulisci la lista degli utenti in attesa dopo l'approvazione
        svuotaUtentiInAttesa();
    }

    private void approvaUtenti(List<UtenteLog> utentiApprovati) {
        for (UtenteLog utente : utentiApprovati) {
            aggiungiUtenteRegistrato(utente);
        }
    }

    private int getSceltaUtente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scegli cosa vuoi fare:");
        System.out.println("1. Approvare tutti gli utenti");
        System.out.println("2. Non approvare nessun utente");
        System.out.println("3. Approvare parzialmente gli utenti");
        System.out.print("Inserisci la tua scelta: ");
        int scelta = scanner.nextInt();
        scanner.nextLine(); // Consuma newline left-over
        return scelta;
    }

    private void approvaParziale() {
        List<UtenteLog> utentiDaApprovare = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleziona gli utenti da approvare (inserisci i numeri separati da spazi, 0 per terminare):");
        while (true) {
            int id = scanner.nextInt();
            if (id == 0) {
                break;
            }
            if (id > 0 && id <= utentiInAttesa.size()) {
                utentiDaApprovare.add(utentiInAttesa.get(id - 1));
            } else {
                System.out.println("ID utente non valido.");
            }
        }
        approvaUtenti(utentiDaApprovare);
    }

    // Svuota la lista degli utenti in attesa
    public void svuotaUtentiInAttesa() {
        utentiInAttesa.clear();
    }

    // Rimuove un utente dalla lista degli utenti registrati
    public void rimuoviUtente(UtenteLog utente) {
        if (utentiRegistrati.remove(utente)) {
            System.out.println("Utente " + utente.getNome() + " rimosso dalla piattaforma.");
        } else {
            System.out.println("L'utente non è registrato sulla piattaforma.");
        }
    }

    public boolean verificaCredenziali(String email, String password, PasswordEncoder passwordEncoder) {
        System.out.println("Verifica credenziali per: " + email);
        for (UtenteLog utente : utentiRegistrati) {
            if (utente.getEmail().equals(email) && passwordEncoder.matches(password, utente.getPassword())) {
                return true; // Credenziali corrette
            }
        }
        return false; // Credenziali sbagliate
    }




}