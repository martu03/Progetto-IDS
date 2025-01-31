package cs.unicam.it.Gestori;

import cs.unicam.it.Utenti.UtenteLog;

public abstract class GestoreRuolo {

    public abstract UtenteLog creaUtente(String nome, String email, String password);

    public void visualizzaForm() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("Inserisci i dati per creare un utente:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.println("Ruolo:");
        Ruolo[] ruoli = Ruolo.values();
        for (int i = 0; i < ruoli.length; i++) {
            System.out.println((i + 1) + ". " + ruoli[i].getNomeRuolo());
        }
        int scelta = scanner.nextInt();
        scanner.nextLine();

        GestoreRuolo gestore;

        switch (scelta) {
            case 1:
                gestore = new GestoreAcquirente();
                break;
            case 2:
                gestore = new GestoreProduttore();
                break;
            case 3:
                gestore = new GestoreTrasformatore();
                break;
            case 4:
                gestore = new GestoreDistributore();
                break;
            default:
                System.out.println("Ruolo non valido. Creazione utente annullata.");
                return;
        }

        UtenteLog utente = gestore.creaUtente(nome, email, password);
        System.out.println("Utente creato con successo: " + utente);
    }
}