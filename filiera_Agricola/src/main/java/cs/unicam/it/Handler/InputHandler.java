package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Categoria;

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
        scanner.nextLine(); // Pulire il buffer
        String input = scanner.nextLine().trim().toUpperCase();
        return Categoria.valueOf(input);
    }

    //Chiedi la data di scadenza
    public String chiediScadenza() {
        System.out.print("Inserisci la scadenza (YYYY-MM-DD): ");
        return scanner.nextLine().trim();
    }

    // Metodo astratto per il comportamento specifico (come quantità o lista di prodotti)
    public abstract void gestisciSpecifico();
}
