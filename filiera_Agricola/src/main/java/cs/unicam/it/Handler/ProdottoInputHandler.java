package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Certificazione;

//InputHandler specifico per il prodotto
public class ProdottoInputHandler extends InputHandler{

    // Chiedi la quantità (specifico per il prodotto)
    public int chiediQuantita() {
        System.out.print("Inserisci la quantità: ");
        return scanner.nextInt();
    }

    //chiedi certificazione (specifico per il prodotto)
    public Certificazione chiediCertificazione() {
        System.out.println("Inserisci certificazione, scegliendo tra queste disponibili:");
        for (Certificazione cert : Certificazione.values()) {
            System.out.println("- " + cert.getCertificationName());
        }
        System.out.print("Certificazione: ");
        scanner.nextLine(); // Consuma la newline rimasta
        String input = scanner.nextLine().trim().toUpperCase();
        for (Certificazione cert : Certificazione.values()) {
            if (cert.getCertificationName().equalsIgnoreCase(input)) {
                return cert;
            }
        }
        return null; // O gestisci l'errore in modo appropriato
    }

    @Override
    public void gestisciSpecifico() {
        // Chiedi la quantità
        chiediQuantita();
        // Chiedi la certificazione
        chiediCertificazione();
    }
}
