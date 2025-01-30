package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Certificazione;

import java.util.Scanner;

// Classe astratta per la gestione dell'input per la creazione di prodotti
public abstract class InputHandler {
    protected final Scanner scanner = new Scanner(System.in);

    // Chiedi il nome
    public String chiediNome() {
        System.out.print("Inserisci il nome: ");
        return scanner.nextLine().trim();
    }

    // Chiedi la descrizione
    public String chiediDescrizione() {
        System.out.print("Inserisci la descrizione: ");
        return scanner.nextLine().trim();
    }

    // Chiedi il prezzo
    public double chiediPrezzo() {
        System.out.print("Inserisci il prezzo: ");
        return scanner.nextDouble();
    }

    // Chiedi la categoria
    public Categoria chiediCategoria() {
        System.out.println("Inserisci la categoria:");
        for (Categoria categoria : Categoria.values()) {
            System.out.println("- " + categoria.name());
        }
        scanner.nextLine();
        String input = scanner.nextLine().trim().toUpperCase();
        return Categoria.valueOf(input);
    }

    //Chiedi la data di scadenza
    public String chiediScadenza() {
        System.out.print("Inserisci la scadenza (YYYY-MM-DD): ");
        return scanner.nextLine().trim();
    }

    //chiedi certificazione
    public Certificazione chiediCertificazione() {
        System.out.println("Inserisci certificazione, scegliendo tra queste disponibili:");
        for (Certificazione cert : Certificazione.values()) {
            System.out.println("- " + cert.getCertificationName());
        }
        System.out.print("Certificazione: ");
        scanner.nextLine();
        String input = scanner.nextLine().trim().toUpperCase();
        for (Certificazione cert : Certificazione.values()) {
            if (cert.getCertificationName().equalsIgnoreCase(input)) {
                return cert;
            }
        }
        return null;
    }

    // Metodo astratto per il comportamento specifico (come quantità o lista di prodotti)
    public abstract void gestisciSpecifico();
}
