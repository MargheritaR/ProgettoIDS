package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@Entity
public class ContestDiContribuzioneDtos {

    @Id
    private String nomeContest;

    @NotEmpty
    private String obiettivo;

    @NotEmpty
    private String tematica;

    @NotEmpty
    //limite massimo dei contenuti aggiungibili nel lasso di tempo del contest
    private String limiteMassimoC;

    @NotEmpty
    //termine massimo di spedizione degli inviti
    private String termineMassimoS;

    @NotEmpty
    //termine massimo di ricezione degli inviti
    private String termineMassimoR;

    @NotEmpty
    private int sogliaInviti = 0;

    @NotEmpty
    //tempo iniziale del lasso di tempo del contest
    private String tempoInizio;

    @NotEmpty
    //tempo finale del lasso di tempo del contest
    private String tempoFine;

    public ContestDiContribuzioneDtos() {
    }

    public String getNomeContest() {
        return nomeContest;
    }

    public  String getObiettivo() {
        return obiettivo;
    }

    public  String getTematica() {
        return tematica;
    }

    public  String getLimiteMassimoC() {
        return limiteMassimoC;
    }

    public  String getTermineMassimoS() {
        return termineMassimoS;
    }

    public  String getTermineMassimoR() {
        return termineMassimoR;
    }


    public int getSogliaInviti() {
        return sogliaInviti;
    }

    public  String getTempoInizio() {
        return tempoInizio;
    }

    public  String getTempoFine() {
        return tempoFine;
    }


}
