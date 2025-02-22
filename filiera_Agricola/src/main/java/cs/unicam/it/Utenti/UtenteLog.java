package cs.unicam.it.Utenti;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "log_type", discriminatorType = DiscriminatorType.STRING)
public abstract class UtenteLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_log_seq")
    @SequenceGenerator(name = "utente_log_seq", sequenceName = "utente_log_seq", allocationSize = 1)
    private int ID;
    private String nome;
    private String email;
    private String password;

    public UtenteLog(String nome, String email, String password) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}