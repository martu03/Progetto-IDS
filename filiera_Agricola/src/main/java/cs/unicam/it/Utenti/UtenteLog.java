package cs.unicam.it.Utenti;

import cs.unicam.it.Handler.HandlerGestorePiattaforma;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "log_type", discriminatorType = DiscriminatorType.STRING)
public abstract class UtenteLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private static int nextID = 0; //variabile statica per l'ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "handlerGestorePiattaforma")
    private HandlerGestorePiattaforma handlerGestorePiattaforma;
    private String nome;
    private String email;
    private String password;

    public UtenteLog(String nome, String email, String password) {
        this.ID = nextID++;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    public UtenteLog() {
    }

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static void setNextID(int nextID) {
        UtenteLog.nextID = nextID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HandlerGestorePiattaforma getHandlerGestorePiattaforma() {
        return handlerGestorePiattaforma;
    }

    public void setHandlerGestorePiattaforma(HandlerGestorePiattaforma handlerGestorePiattaforma) {
        this.handlerGestorePiattaforma = handlerGestorePiattaforma;
    }
}