package cs.unicam.it.Utenti;

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

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    //@Override non tutti gli utenti possono creare prodotti
    //public void crea(String nome, double prezzo, boolean certificato) {
    //    ComponenteProdotto prodotto = new ProdottoSingolo(nome, prezzo, certificato);
    //    aggiungiProdotto(prodotto);
    //}

    //private void aggiungiProdotto(ComponenteProdotto prodotto) {
    //    prodotti.add(prodotto);
    //    System.out.println("Prodotto aggiunto da " + nome + ": " + prodotto.getNome());
    //}

    //public List<ComponenteProdotto> getProdotti() {
    //    return prodotti;
    //}
}