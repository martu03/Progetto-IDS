package cs.unicam.it.Utenti;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class UtenteLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private static int nextID = 0;
    private String nome;
    private String email;
    private String password;

    public UtenteLog(String nome, String email, String password) {
        this.ID = nextID++;
        this.nome = nome;
        this.email = email;
        this.password = password;
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
}