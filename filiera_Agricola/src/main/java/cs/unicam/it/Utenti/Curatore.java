package cs.unicam.it.Utenti;

import cs.unicam.it.Handler.HandlerProdottiCuratore;
import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Certificazione;
import cs.unicam.it.Prodotto.Prodotto;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class Curatore extends UtenteLog {

    @ElementCollection
    private List<Integer> idDaRimuovere;

    public Curatore(String nome, String email, String password) {
        super(nome, email, password, Ruolo.CURATORE);
    }

    public Curatore() {
        super();
    }

    public void valida() {
        HandlerProdottiCuratore.getInstance().visualizzaProdottiDaValidare();
        int scelta = getSceltaUtente();

        switch (scelta) {
            case 1: //VALIDA TUTTI I PRODOTTI
                List<Prodotto> prodottiDaValidare = HandlerProdottiCuratore.getInstance().getProdottiDaValidare();
                validaProdotti(prodottiDaValidare);
                System.out.println("Tutti i prodotti sono stati validati e aggiunti al marketplace.");
                break;
            case 2://NON VALIDARE NESSUN PRODOTTO
                notificaAziendaNonValidazione(HandlerProdottiCuratore.getInstance().getProdottiDaValidare());
                System.out.println("Nessun prodotto è stato validato.");
                break;
            case 3://VALIDA PARZIALMENTE I PRODOTTI
                validaParziale();
                System.out.println("Prodotti validati parzialmente.");
                break;
            default:
                System.out.println("Scelta non valida.");
                return;
        }

        HandlerProdottiCuratore.getInstance().svuotaLista();
    }

    private int getSceltaUtente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scegli cosa vuoi fare:");
        System.out.println("1. Validare tutti i prodotti");
        System.out.println("2. Non validare nessun prodotto");
        System.out.println("3. Validare parzialmente i prodotti");
        System.out.print("Inserisci la tua scelta: ");
        int scelta = scanner.nextInt();
        scanner.nextLine(); // Consuma newline left-over
        return scelta;
    }

    public void validaParziale() {
        List<Integer> idDaRimuovereList = getIdDaRimuovere();
        int[] idDaRimuovere = idDaRimuovereList.stream().mapToInt(Integer::intValue).toArray();

        List<Prodotto> prodottiDaValidare = HandlerProdottiCuratore.getInstance().getProdottiDaValidare();

        for (int id : idDaRimuovere) {
            for (Prodotto prodotto : prodottiDaValidare) {
                if (prodotto.getId() == id) {
                    prodottiDaValidare.remove(prodotto);
                    break;
                }
            }
        }
        validaProdotti(prodottiDaValidare);
    }

    private List<Integer> getIdDaRimuovere() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserisci gli ID dei prodotti da rimuovere (separati da spazi): ");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        List<Integer> idDaRimuovere = new ArrayList<>();
        for (String s : inputArray) {
            idDaRimuovere.add(Integer.parseInt(s));
        }
        return idDaRimuovere;
    }

    //Simula la verifica della certificazione
    private boolean validaCertificazione(Certificazione certificazione) {
        return true;
    }

    private void validaProdotti(List<Prodotto> prodottiDaValidare) {
        for (Prodotto prodotto : prodottiDaValidare) {
            if (validaCertificazione(prodotto.getCertificazione())) {
                Marketplace.getInstance().aggiungiProdotto(prodotto);
                System.out.println("Prodotto " + prodotto.getNome() + " validato e aggiunto al marketplace.");
            } else {
                System.out.println("Certificazione non valida. Prodotto non aggiunto al marketplace.");
            }
        }

    }

    private void notificaAziendaNonValidazione(List<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            System.out.println("Il prodotto " + prodotto.getNome() + " non è stato validato.");
        }
    }

}