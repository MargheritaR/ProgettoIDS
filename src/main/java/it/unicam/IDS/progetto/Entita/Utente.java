package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
public class Utente implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String password;

    @NotEmpty
    private String username;

    @Enumerated(value = EnumType.STRING)
    private Ruoli ruolo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messaggio> listaMessaggiNonLetti;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messaggio> listaMessaggiLetti;

    public Utente(String username,String password) {
        this.password = password;
        this.username = username;
        this.ruolo = Ruoli.ROLE_TURISTA;
        this.listaMessaggiNonLetti = null;
        this.listaMessaggiLetti = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ruolo.name()));
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
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

    public Ruoli getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruoli ruolo) {
        this.ruolo = ruolo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String nome) {
        this.username = nome;
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
                "password: " + password + '\n' +
                "nome: " + username + '\n' +
                "ruolo: " + ruolo + '\n' +
                "listaMessaggiNonLetti: " + listaMessaggiNonLetti + '\n' +
                "listaMessaggiLetti: " + listaMessaggiLetti + '\n';
    }
}
