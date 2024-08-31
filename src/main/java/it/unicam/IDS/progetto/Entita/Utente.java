package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Dtos.MessaggioDtos;
import it.unicam.IDS.progetto.Eccezioni.Messaggio.MessaggiEmptyEccezione;
import it.unicam.IDS.progetto.Eccezioni.Messaggio.MessaggioNotFoundEccezione;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Ruoli ruolo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messaggio> listaMessaggiNonLetti;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Messaggio> listaMessaggiLetti;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Itinerario> listaPreferitiItinerario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PuntoInteresse> listaPreferitiPDI;

    public Utente(String email, String password, String nome, String cognome) {
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = Ruoli.ROLE_TURISTA;
        this.listaMessaggiNonLetti = null;
        this.listaMessaggiLetti = null;
        this.listaPreferitiItinerario = null;
        this.listaPreferitiPDI = null;
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
        this.email = nome;
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

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull String getCognome() {
        return cognome;
    }

    public void setCognome(@NotNull String cognome) {
        this.cognome = cognome;
    }

    public List<Itinerario> getListaPreferitiItinerario() {
        return listaPreferitiItinerario;
    }

    public void setListaPreferitiItinerario(List<Itinerario> listaPreferitiItinerario) {
        this.listaPreferitiItinerario = listaPreferitiItinerario;
    }

    public List<PuntoInteresse> getListaPreferitiPDI() {
        return listaPreferitiPDI;
    }

    public void setListaPreferitiPDI(List<PuntoInteresse> listaPreferitiPDI) {
        this.listaPreferitiPDI = listaPreferitiPDI;
    }

    public MessaggioDtos leggiMessaggi(String titoloMessaggio, Utente utente) {
        if (listaMessaggiNonLetti == null)
            throw new MessaggiEmptyEccezione();

        Messaggio messaggio = findMessaggi(titoloMessaggio, utente);
        listaMessaggiNonLetti.remove(messaggio);
        listaMessaggiLetti.add(messaggio);
        return new MessaggioDtos(messaggio.getMittente(), messaggio.getDestinatario(),
                messaggio.getTitolo(), messaggio.getIntestazione());
    }

    private Messaggio findMessaggi(String titoloMessaggi, Utente utente) {
        for (Messaggio m : utente.listaMessaggiNonLetti)
            if (m.getTitolo().equals(titoloMessaggi))
                return m;
        throw new MessaggioNotFoundEccezione();
    }

    public void assegnamentoRuoli(String ruolo, Utente utente) {
        utente.setRuolo(Ruoli.valueOf(ruolo));
    }

    @Override
    public String toString() {
        return "Utente: " + '\n' +
                "id: " + id + '\n' +
                "password: " + password + '\n' +
                "nome: " + email + '\n' +
                "ruolo: " + ruolo + '\n' +
                "listaMessaggiNonLetti: " + listaMessaggiNonLetti + '\n' +
                "listaMessaggiLetti: " + listaMessaggiLetti + '\n' +
                "listaPreferitiItinerario=" + listaPreferitiItinerario + '\n' +
                "listaPreferitiPDI=" + listaPreferitiPDI + '\n' +
                '}';
    }
}
