package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class Preferiti{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPreferiti;

    @NotEmpty
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Itinerario> preferiti;

    public Preferiti(List<Itinerario> preferiti) {
        this.preferiti = preferiti;
    }

    public List<Itinerario> getPreferiti() {
        return preferiti;
    }

    public void setPreferiti(List<Itinerario> preferiti) {
        this.preferiti = preferiti;
    }

    public int getIdPreferiti() {
        return idPreferiti;
    }

    public void setIdPreferiti(int idPreferiti) {
        this.idPreferiti = idPreferiti;
    }
}
