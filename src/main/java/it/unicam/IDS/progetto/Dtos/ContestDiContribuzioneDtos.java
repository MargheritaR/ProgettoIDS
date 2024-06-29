package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.Id;

public class ContestDiContribuzioneDtos {

    @Id
    private String nomeContest;

    private String obiettivo;

    private String tematica;

    //limite massimo dei contenuti aggiungibili nel lasso di tempo del contest
    private String limiteMassimoC;

    //termine massimo di spedizione degli inviti
    private String termineMassimoS;

    //termine massimo di ricezione degli inviti
    private String termineMassimoR;

    private int sogliaInviti = 0;

    //tempo iniziale del lasso di tempo del contest
    private String tempoInizio;

    //tempo finale del lasso di tempo del contest
    private String tempoFine;

    public ContestDiContribuzioneDtos(String nomeContest, String obiettivo, String tematica,
                                      String limiteMassimoC, String termineMassimoS, String termineMassimoR,
                                      int sogliaInviti, String tempoInizio, String tempoFine) {
        this.nomeContest = nomeContest;
        this.obiettivo = obiettivo;
        this.tematica = tematica;
        this.limiteMassimoC = limiteMassimoC;
        this.termineMassimoS = termineMassimoS;
        this.termineMassimoR = termineMassimoR;
        this.sogliaInviti = sogliaInviti;
        this.tempoInizio = tempoInizio;
        this.tempoFine = tempoFine;
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
