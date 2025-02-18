package cs.unicam.it.Utenti;

import cs.unicam.it.Handler.HandlerGestorePiattaforma;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
@DiscriminatorValue("GestorePiattaforma")
public class GestorePiattaforma extends UtenteLog {

    private static GestorePiattaforma instance;

    public GestorePiattaforma() {
        super();
    }

    private GestorePiattaforma(String nome, String email, String password) {
        super(nome, email, password);
        setHandlerGestorePiattaforma(new HandlerGestorePiattaforma());
    }

    // Ottiene l'istanza del gestore piattaforma
    public static GestorePiattaforma getInstance() {
        if (instance == null) {
            instance = new GestorePiattaforma("Gestore", "gestore.pattaforma@gmail.com", "Filiera");
        }
        return instance;
    }

    // Aggiunge un utente in attesa di approvazione
    public void aggiungiUtenteInAttesa(UtenteLog utente) {
        getHandlerGestorePiattaforma().aggiungiUtenteInAttesa(utente);
    }

    public void approvaUtenti() {
        if (getHandlerGestorePiattaforma().getUtentiInAttesa().isEmpty()) {
            System.out.println("Non ci sono utenti in attesa di approvazione.");
            return;
        }

        getHandlerGestorePiattaforma().visualizzaUtentiInAttesa();

        // Chiedi la scelta dell'utente
        int scelta = getSceltaUtente();
        switch (scelta) {
            case 1: // APPROVA TUTTI GLI UTENTI
                approvaUtenti(getHandlerGestorePiattaforma().getUtentiInAttesa());
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
        getHandlerGestorePiattaforma().svuotaUtentiInAttesa();
    }

    private void approvaUtenti(List<UtenteLog> utentiApprovati) {
        for (UtenteLog utente : utentiApprovati) {
            getHandlerGestorePiattaforma().aggiungiUtenteRegistrato(utente);
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
            if (id > 0 && id <= getHandlerGestorePiattaforma().getUtentiInAttesa().size()) {
                utentiDaApprovare.add(getHandlerGestorePiattaforma().getUtentiInAttesa().get(id - 1));
            } else {
                System.out.println("ID utente non valido.");
            }
        }
        approvaUtenti(utentiDaApprovare);
    }

    // Rimuove un utente dalla lista degli utenti registrati
    public void rimuoviUtente(UtenteLog utente) {
        getHandlerGestorePiattaforma().rimuoviUtente(utente);
    }

    // Verifica le credenziali di un utente tra gli utenti registrati
    public boolean verificaCredenziali(String email, String password) {
        return getHandlerGestorePiattaforma().verificaCredenziali(email, password);
    }

}
