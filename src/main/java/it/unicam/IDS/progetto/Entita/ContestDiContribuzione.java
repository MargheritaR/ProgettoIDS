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
    //ultima data disponibile per aggiungere i contenuti al contest di contribuzione
    private LocalDate dpc;

    @NotNull
    private boolean suInvito;

    @NotNull
    //data inizio del contest di contribuzione
    private LocalDate dataInizio;

    @NotNull
    //data fine del contest di contribuzione
    private LocalDate dataFine;

    @NotNull
    private File vincitore;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Contenuti> contenuti;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Contenuti> contenutiApprovati;

    public ContestDiContribuzione(String nomeContest, String obiettivo, String tematica,
                                  String dpc, boolean suInvito, String dataInizio, String dataFine) {
        this.nomeContest = nomeContest;
        this.obiettivo = obiettivo;
        this.tematica = tematica;
        this.dpc = LocalDate.parse(dpc, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.suInvito= suInvito;
        this.dataInizio = LocalDate.parse(dataInizio,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.dataFine =LocalDate.parse(dataFine,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.vincitore = null;
        this.contenuti = null;
        this.contenutiApprovati = null;
    }

    public ContestDiContribuzione() {}

    public String getNomeContest() {
        return nomeContest;
    }

    public void setNomeContest(String nomeContest) {
        this.nomeContest = nomeContest;
    }

    public @NotNull String getObiettivo() {
        return obiettivo;
    }

    public void setObiettivo(@NotNull String obiettivo) {
        this.obiettivo = obiettivo;
    }

    public @NotNull String getTematica() {
        return tematica;
    }

    public void setTematica(@NotNull String tematica) {
        this.tematica = tematica;
    }

    public @NotNull LocalDate getDpc() {
        return dpc;
    }

    public void setDpc(@NotNull LocalDate dpc) {
        this.dpc = dpc;
    }

    @NotNull
    public boolean isSuInvito() {
        return suInvito;
    }

    public void setSuInvito(@NotNull boolean suInvito) {
        this.suInvito = suInvito;
    }

    public @NotNull LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(@NotNull LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public @NotNull LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(@NotNull LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public @NotNull File getVincitore() {
        return vincitore;
    }

    public void setVincitore(@NotNull File vincitore) {
        this.vincitore = vincitore;
    }

    public List<Contenuti> getContenuti() {
        return contenuti;
    }

    public void setContenuti(List<Contenuti> contenuti) {
        this.contenuti = contenuti;
    }

    public List<Contenuti> getContenutiApprovati() {
        return contenutiApprovati;
    }

    public void setContenutiApprovati(List<Contenuti> contenutiApprovati) {
        this.contenutiApprovati = contenutiApprovati;
    }

    @Override
    public String toString() {
        return "ContestDiContribuzione{" +
                "nomeContest='" + nomeContest + '\'' +
                ", obiettivo='" + obiettivo + '\'' +
                ", tematica='" + tematica + '\'' +
                ", dpc=" + dpc +
                ", suInvito=" + suInvito +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", vincitore=" + vincitore +
                ", contenuti=" + contenuti +
                ", contenutiApprovati=" + contenutiApprovati +
                '}';
    }
}
