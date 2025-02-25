package cs.unicam.it.Utenti;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "log_type", discriminatorType = DiscriminatorType.STRING)
public abstract class UtenteLog implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utente_log_seq")
    @SequenceGenerator(name = "utente_log_seq", sequenceName = "utente_log_seq", allocationSize = 1)
    private int ID;
    private String nome;
    private String email;
    private String password;
    private boolean approvato;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public UtenteLog(String nome, String email, String password, Ruolo ruolo) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.approvato = false;
        this.ruolo = ruolo;
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


    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + ruolo.name()));
    }

    //DA CAPIRE SE QUELLI DI SOTTO SERVONO
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isApprovato() {
        return approvato;
    }

    public void setApprovato(boolean approvato) {
        this.approvato = approvato;
    }
}