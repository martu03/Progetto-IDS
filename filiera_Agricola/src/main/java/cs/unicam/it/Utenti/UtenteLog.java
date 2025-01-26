package cs.unicam.it.Utenti;

// Classe astratta che rapp
abstract class UtenteLog {

    private int ID;
    private static int nextID = 1; //variabile statica per l'ID
    private String nome;
    private String email;
    private String password;

    public UtenteLog(String nome, String email, String password) {
        this.ID = nextID++;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    //Metodi get per ottenere i valori delle variabili
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