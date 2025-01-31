package cs.unicam.it.Utenti;

//definisce la struttura base di un utente loggato
public abstract class UtenteLog {

    private int ID;
    private static int nextID = 0; //variabile statica per l'ID
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
}