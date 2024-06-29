package it.unicam.IDS.progetto.Entita;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class ContestDiContribuzione{

    @Id
    private String nomeContest;

    @NotNull
    private String obiettivo;

    @NotNull
    private String tematica;

    @NotNull
    //limite massimo dei contenuti aggiungibili nel lasso di tempo del contest
    private LocalDate limiteMassimoC;

    @NotNull
    //termine massimo di spedizione degli inviti
    private String termineMassimoS;

    @NotNull
    //termine massimo di ricezione degli inviti
    private String termineMassimoR;

    @NotNull
    private int sogliaInviti = 0;

    @NotNull
    //tempo iniziale del lasso di tempo del contest
    private LocalDate tempoInizio;

    @NotNull
    //tempo finale del lasso di tempo del contest
    private LocalDate tempoFine;

    @NotNull
    private File vincitore;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Contenuti> contenuti;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Contenuti> contenutiApprovati;

    public ContestDiContribuzione(String nomeContest, String obiettivo, String tematica,
                                  String limiteMassimoC, int sogliaInviti, String termineMassimoS,
                                  String termineMassimoR, String tempoInizio, String tempoFine) {
        this.nomeContest = nomeContest;
        this.obiettivo = obiettivo;
        this.tematica = tematica;
        this.limiteMassimoC = LocalDate.parse(limiteMassimoC, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.sogliaInviti = sogliaInviti;
        this.termineMassimoR = termineMassimoR;
        this.termineMassimoS = termineMassimoS;
        this.tempoInizio = LocalDate.parse(tempoInizio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.tempoFine = LocalDate.parse(tempoFine, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.vincitore = null;
        this.contenuti = null;
        this.contenutiApprovati = null;
    }

    public ContestDiContribuzione() {}

    public String getNomeContest() {
        return nomeContest;
    }

    public String getObiettivo() {
        return obiettivo;
    }

    public String getTematica() {
        return tematica;
    }

    public LocalDate getLimiteMassimoC() {
        return limiteMassimoC;
    }

    public int getSogliaInviti() {
        return sogliaInviti;
    }

    public String getTermineMassimoS() {
        return termineMassimoS;
    }

    public String getTermineMassimoR() {
        return termineMassimoR;
    }

    public LocalDate getTempoInizio() {
        return tempoInizio;
    }

    public LocalDate getTempoFine() {
        return tempoFine;
    }

    public List<Contenuti> getListaContenuti() {
        return contenuti;
    }

    public List<Contenuti> getContenutiApprovati() {
        return contenutiApprovati;
    }

    public File getVincitore() {
        return vincitore;
    }

    public List<Contenuti> getContenuti() {
        return contenuti;
    }

    public void setVincitore(File vincitore) {
        this.vincitore = vincitore;
    }


    public void setContenuti(List<Contenuti> contenuti) {
        this.contenuti = contenuti;
    }

    public void setNomeContest(String nomeContest) {
        this.nomeContest = nomeContest;
    }

    public void setObiettivo(String obiettivo) {
        this.obiettivo = obiettivo;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setLimiteMassimoC(LocalDate limiteMassimoC) {
        this.limiteMassimoC = this.limiteMassimoC;
    }

    public void setSogliaInviti(int sogliaInviti) {
        this.sogliaInviti = sogliaInviti;
    }

    public void setTermineMassimoS(String termineMassimoS) {
        this.termineMassimoS = termineMassimoS;
    }

    public void setTermineMassimoR(String termineMassimoR) {
        this.termineMassimoR = termineMassimoR;
    }

    public void setTempoInizio(LocalDate tempoInizio) {
        this.tempoInizio = tempoInizio;
    }

    public void setTempoFine(LocalDate tempoFine) {
        this.tempoFine = tempoFine;
    }

    public void setListaContenuti(List<Contenuti> contenuti) {
        this.contenuti = contenuti;
    }

    public void setContenutiApprovati(List<Contenuti> contenutiApprovati) {
        this.contenutiApprovati = contenutiApprovati;
    }

    @Override
    public String toString() {
        return "ContestDiContribuzione" +
                "Nome del contest:" + nomeContest + '\n' +
                ", obiettivo:" + obiettivo + '\n' +
                ", tematica:" + tematica + '\n' +
                ", limite Massimo dei Contenuti:" + limiteMassimoC +
                ", termine massimo inviti spediti:" + termineMassimoS + '\n' +
                ", termine massimo inviti ricevuti:" + termineMassimoR + '\n' +
                ", soglia inviti:" + sogliaInviti + '\n' +
                ", lasso di tempo:" + "tra" + tempoInizio + "e" + tempoFine + '\n';
    }
}
