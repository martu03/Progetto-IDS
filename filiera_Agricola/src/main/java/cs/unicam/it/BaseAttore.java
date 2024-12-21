package cs.unicam.it;

import java.util.ArrayList;
import java.util.List;

abstract class BaseAttore implements Attore {
    protected String nome;
    protected String email;
    protected List<ComponenteProdotto> prodotti;

    public BaseAttore(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.prodotti = new ArrayList<>();
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void crea(String nome, double prezzo, boolean certificato) {
        ComponenteProdotto prodotto = new ProdottoSingolo(nome, prezzo, certificato);
        aggiungiProdotto(prodotto);
    }

    private void aggiungiProdotto(ComponenteProdotto prodotto) {
        prodotti.add(prodotto);
        System.out.println("Prodotto aggiunto da " + nome + ": " + prodotto.getNome());
    }

    public List<ComponenteProdotto> getProdotti() {
        return prodotti;
    }
}