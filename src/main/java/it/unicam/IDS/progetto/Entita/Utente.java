package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cognome;

    @NotEmpty
    private int ruolo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messaggio> listaMessaggiNonLetti;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messaggio> listaMessaggiLetti;

    public Utente(String email, String password, String nome, String cognome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = 6;
        this.listaMessaggiNonLetti = null;
        this.listaMessaggiLetti = null;
    }

    public Utente() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    public List<Messaggio> getListaMessaggiNonLetti() {
        return listaMessaggiNonLetti;
    }

    public void setListaMessaggiNonLetti(List<Messaggio> listaMessaggiNonLetti) {
        this.listaMessaggiNonLetti = listaMessaggiNonLetti;
    }

    public List<Messaggio> getListaMessaggiLetti() {
        return listaMessaggiLetti;
    }

    public void setListaMessaggiLetti(List<Messaggio> listaMessaggiLetti) {
        this.listaMessaggiLetti = listaMessaggiLetti;
    }

    @Override
    public String toString() {
        return "Utente: " + '\n' +
                "id: " + id + '\n' +
                "email: " + email + '\n' +
                "password: " + password + '\n' +
                "nome: " + nome + '\n' +
                "cognome: " + cognome + '\n' +
                "ruolo: " + ruolo + '\n' +
                "listaMessaggiNonLetti: " + listaMessaggiNonLetti + '\n' +
                "listaMessaggiLetti: " + listaMessaggiLetti + '\n';
    }
}
