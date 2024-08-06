package it.unicam.IDS.progetto.Dtos;

import jakarta.persistence.Id;

import java.time.LocalDate;

public class ContestDiContribuzioneDtos {

    @Id
    private String nomeContest;

    private String obiettivo;

    private String tematica;

    //limite massimo dei contenuti aggiungibili nel lasso di tempo del contest
    private String dpc;

    private boolean suInvito;

    //tempo iniziale del lasso di tempo del contest
    private String dataInizio;

    //tempo finale del lasso di tempo del contest
    private String dataFine;

    public ContestDiContribuzioneDtos(String nomeContest, String obiettivo, String tematica, String dpc,
                                      boolean suInvito, String dataInizio, String dataFine) {
        this.nomeContest = nomeContest;
        this.obiettivo = obiettivo;
        this.tematica = tematica;
        this.dpc = dpc;
        this.suInvito = suInvito;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public String getNomeContest() {
        return nomeContest;
    }

    public void setNomeContest(String nomeContest) {
        this.nomeContest = nomeContest;
    }

    public String getObiettivo() {
        return obiettivo;
    }

    public void setObiettivo(String obiettivo) {
        this.obiettivo = obiettivo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDpc() {
        return dpc;
    }

    public void setDpc(String dpc) {
        this.dpc = dpc;
    }

    public boolean isSuInvito() {
        return suInvito;
    }

    public void setSuInvito(boolean suInvito) {
        this.suInvito = suInvito;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }
}
