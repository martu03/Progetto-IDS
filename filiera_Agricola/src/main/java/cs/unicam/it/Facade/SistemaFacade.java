package cs.unicam.it.Facade;

import cs.unicam.it.Esterni.PagoPa;
import cs.unicam.it.Eventi.EventoBuilder;
import cs.unicam.it.Gestori.GestorePiattaforma;
import cs.unicam.it.Gestori.HandlerGestorePiattoforma;
import cs.unicam.it.Gestori.Ruolo;
import cs.unicam.it.Handler.*;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Mappa.Mappa;
import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.PacchettoBuilder;
import cs.unicam.it.Prodotto.ProdottoSingoloBuilder;
import cs.unicam.it.Utenti.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SistemaFacade {

    //GESTORE
    @Autowired
    private GestorePiattaforma gestorePiattaforma;
    @Autowired
    private HandlerGestorePiattoforma handlerGestorePiattoforma;
    //ANIMATORE
    @Autowired
    private Animatore animatore;
    @Autowired
    private EventoBuilder eventoBuilder;
    @Autowired
    private EventoInputHandler eventoInputHandler;
    @Autowired
    private HandlerEventi handlerEventi;
    //CURATORE
    @Autowired
    private Curatore curatore;
    //AZIENDE
    @Autowired
    private PacchettoInputHandler pacchettoInputHandler;
    @Autowired
    private ProdottoSingoloInputHandler prodottoSingoloInputHandler;
    @Autowired
    private PacchettoBuilder pacchettoBuilder;
    @Autowired
    private ProdottoSingoloBuilder prodottoSingoloBuilder;
    //ACQUIRENTE
    @Autowired
    private HandlerAcquisti handlerAcquisti;
    @Autowired
    private HandlerCalcolaTotale handlerCalcolaTotale;
    @Autowired
    private PagoPa pagoPa;
    //CARRELLI
    @Autowired
    private HandlerCarrelli handlerCarrelli;
    @Autowired
    private HandlerScadenzaCarrello handlerScadenzaCarrello;
    //PRODOTTI
    @Autowired
    private HandlerScadenzaProdotto handlerScadenzaProdotto;
    @Autowired
    private Marketplace marketplace;
    //MAPPA
    @Autowired
    private Mappa mappa;

    // Costruttore
    public SistemaFacade() {

        this.gestorePiattaforma = GestorePiattaforma.getInstance();
        this.handlerGestorePiattoforma = new HandlerGestorePiattoforma();

        this.animatore = new Animatore("Animatore", "animatore@example.com", "password123");
        this.eventoBuilder = EventoBuilder.getInstance();
        this.eventoInputHandler = EventoInputHandler.getInstance();
        this.handlerEventi = new HandlerEventi();

        this.curatore = new Curatore("Curatore", "curatore@example.com", "password123");

        this.pacchettoInputHandler = PacchettoInputHandler.getInstance();
        this.prodottoSingoloInputHandler = ProdottoSingoloInputHandler.getInstance();
        this.pacchettoBuilder = PacchettoBuilder.getInstance();
        this.prodottoSingoloBuilder = ProdottoSingoloBuilder.getInstance();

        this.handlerAcquisti = HandlerAcquisti.getInstance();
        this.handlerCalcolaTotale = HandlerCalcolaTotale.getInstance();
        this.pagoPa = PagoPa.getInstance();

        this.handlerCarrelli = HandlerCarrelli.getInstance();
        this.handlerScadenzaCarrello = HandlerScadenzaCarrello.getInstance();

        this.handlerScadenzaProdotto = new HandlerScadenzaProdotto();
        this.marketplace = Marketplace.getInstance();

        this.mappa = Mappa.getInstance();
    }

    public GestorePiattaforma getGestorePiattaforma(){
        return gestorePiattaforma;
    }

    public Curatore getCuratore(){
        return curatore;
    }

    public Animatore getAnimatore(){
        return animatore;
    }

    public Marketplace getMarketplace(){
        return marketplace;
    }

    public boolean login(String email, String password) {
        // Delega la verifica delle credenziali al GestorePiattaforma
        if (gestorePiattaforma.verificaCredenziali(email, password)) {
            System.out.println("Login avvenuto con successo!");
            return true;
        } else {
            System.out.println("Accesso non valido.");
            return false;
        }
    }

    public UtenteLog registraUtente() {
        Scanner scanner = new Scanner(System.in);

        String nome = chiediNome(scanner);
        String email = chiediEmail(scanner);
        String password = chiediPassword(scanner);
        Ruolo ruoloSelezionato = chiediRuolo(scanner);
        Geolocalizzazione geolocalizzazione = chiediGeolocalizzazione(scanner);

        UtenteLog nuovoUtente = creaUtente(nome, email, password, ruoloSelezionato, geolocalizzazione);

        if (nuovoUtente != null) {
            gestorePiattaforma.aggiungiUtenteInAttesa(nuovoUtente);
            System.out.println("Registrazione completata! L'account è in attesa di approvazione.");
        } else {
            System.out.println("Errore durante la registrazione.");

        }

        scanner.close();
        return nuovoUtente;
    }

    private String chiediNome(Scanner scanner) {
        System.out.print("Inserisci il nome: ");
        return scanner.nextLine();
    }

    private String chiediEmail(Scanner scanner) {
        System.out.print("Inserisci l'email: ");
        return scanner.nextLine();
    }

    private String chiediPassword(Scanner scanner) {
        System.out.print("Inserisci la password: ");
        return scanner.nextLine();
    }

    private Ruolo chiediRuolo(Scanner scanner) {
        System.out.println("Seleziona il tuo ruolo:");
        Ruolo[] ruoli = Ruolo.values();
        for (int i = 0; i < ruoli.length; i++) {
            System.out.println((i + 1) + ". " + ruoli[i].getNomeRuolo());
        }

        int sceltaRuolo;
        while (true) {
            System.out.print("Inserisci il numero corrispondente al tuo ruolo: ");
            try {
                sceltaRuolo = Integer.parseInt(scanner.nextLine());
                if (sceltaRuolo > 0 && sceltaRuolo <= ruoli.length) {
                    return ruoli[sceltaRuolo - 1];
                } else {
                    System.out.println("Scelta non valida. Riprova.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
            }
        }
    }

    private Geolocalizzazione chiediGeolocalizzazione(Scanner scanner) {
        System.out.print("Inserisci la latitudine: ");
        double latitudine = Double.parseDouble(scanner.nextLine());

        System.out.print("Inserisci la longitudine: ");
        double longitudine = Double.parseDouble(scanner.nextLine());

        return new Geolocalizzazione(latitudine, longitudine, "Indirizzo di prova");
    }

    // Metodo ausiliario per creare l'utente in base al ruolo
    private UtenteLog creaUtente(String nome, String email, String password, Ruolo ruolo, Geolocalizzazione geolocalizzazione) {
        switch (ruolo) {
            case ACQUIRENTE:
                return new Acquirente(nome, email, password,geolocalizzazione); // Classe Acquirente deve essere implementata
            case PRODUTTORE:
                return new Produttore(nome, email, password,geolocalizzazione); // Classe Produttore deve essere implementata
            case TRASFORMATORE:
                return new Trasformatore(nome, email, password,geolocalizzazione); // Classe Trasformatore deve essere implementata
            case DISTRIBUTORE:
                return new Distributore(nome, email, password,geolocalizzazione); // Classe Distributore deve essere implementata
            default:
                return null;
        }
    }

}
