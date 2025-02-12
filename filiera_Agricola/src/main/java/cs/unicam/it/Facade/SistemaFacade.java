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
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SistemaFacade {

    // GESTORE
    private final GestorePiattaforma gestorePiattaforma;
    private final HandlerGestorePiattoforma handlerGestorePiattoforma;

    // ANIMATORE
    private final Animatore animatore;
    private final EventoBuilder eventoBuilder;
    private final EventoInputHandler eventoInputHandler;
    private final HandlerEventi handlerEventi;

    // CURATORE
    private final Curatore curatore;

    // AZIENDE
    private final PacchettoInputHandler pacchettoInputHandler;
    private final ProdottoSingoloInputHandler prodottoSingoloInputHandler;
    private final PacchettoBuilder pacchettoBuilder;
    private final ProdottoSingoloBuilder prodottoSingoloBuilder;

    // ACQUIRENTE
    private final HandlerAcquisti handlerAcquisti;
    private final HandlerCalcolaTotale handlerCalcolaTotale;
    private final PagoPa pagoPa;

    // CARRELLI
    private final HandlerCarrelli handlerCarrelli;
    private final HandlerScadenzaCarrello handlerScadenzaCarrello;

    // PRODOTTI
    private final HandlerScadenzaProdotto handlerScadenzaProdotto;
    private final Marketplace marketplace;

    // MAPPA
    private final Mappa mappa;

    // Costruttore con Autowired per l'iniezione delle dipendenze
    public SistemaFacade(
            GestorePiattaforma gestorePiattaforma,
            HandlerGestorePiattoforma handlerGestorePiattoforma,
            EventoBuilder eventoBuilder,
            EventoInputHandler eventoInputHandler,
            HandlerEventi handlerEventi,
            PacchettoInputHandler pacchettoInputHandler,
            ProdottoSingoloInputHandler prodottoSingoloInputHandler,
            PacchettoBuilder pacchettoBuilder,
            ProdottoSingoloBuilder prodottoSingoloBuilder,
            HandlerAcquisti handlerAcquisti,
            HandlerCalcolaTotale handlerCalcolaTotale,
            PagoPa pagoPa,
            HandlerCarrelli handlerCarrelli,
            HandlerScadenzaCarrello handlerScadenzaCarrello,
            HandlerScadenzaProdotto handlerScadenzaProdotto,
            Marketplace marketplace,
            Mappa mappa
    ) {
        this.gestorePiattaforma = gestorePiattaforma;
        this.handlerGestorePiattoforma = handlerGestorePiattoforma;

        this.animatore = new Animatore("Animatore", "animatore@example.com", "password123");
        this.eventoBuilder = eventoBuilder;
        this.eventoInputHandler = eventoInputHandler;
        this.handlerEventi = handlerEventi;

        this.curatore = new Curatore("Curatore", "curatore@example.com", "password123");

        this.pacchettoInputHandler = pacchettoInputHandler;
        this.prodottoSingoloInputHandler = prodottoSingoloInputHandler;
        this.pacchettoBuilder = pacchettoBuilder;
        this.prodottoSingoloBuilder = prodottoSingoloBuilder;

        this.handlerAcquisti = handlerAcquisti;
        this.handlerCalcolaTotale = handlerCalcolaTotale;
        this.pagoPa = pagoPa;

        this.handlerCarrelli = handlerCarrelli;
        this.handlerScadenzaCarrello = handlerScadenzaCarrello;

        this.handlerScadenzaProdotto = handlerScadenzaProdotto;
        this.marketplace = marketplace;

        this.mappa = mappa;
    }

    public GestorePiattaforma getGestorePiattaforma() {
        return gestorePiattaforma;
    }

    public Curatore getCuratore() {
        return curatore;
    }

    public Animatore getAnimatore() {
        return animatore;
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public boolean login(String email, String password) {
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

    private UtenteLog creaUtente(String nome, String email, String password, Ruolo ruolo, Geolocalizzazione geolocalizzazione) {
        switch (ruolo) {
            case ACQUIRENTE:
                return new Acquirente(nome, email, password, geolocalizzazione);
            case PRODUTTORE:
                return new Produttore(nome, email, password, geolocalizzazione);
            case TRASFORMATORE:
                return new Trasformatore(nome, email, password, geolocalizzazione);
            case DISTRIBUTORE:
                return new Distributore(nome, email, password, geolocalizzazione);
            default:
                return null;
        }
    }
}
