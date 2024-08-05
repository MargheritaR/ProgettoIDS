package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Dtos.ContenutiDtos;
import it.unicam.IDS.progetto.Dtos.ItinerarioDtos;
import it.unicam.IDS.progetto.Dtos.PuntoInteresseDtos;
import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContentAlreadyExistEccezione;
import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContenutiNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione.ContestAlreadyExistEccezione;
import it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione.ContestDiContribuzioneNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione.ContestInvalidDataEccezione;
import it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione.ContestOverTimeLimitEccezione;
import it.unicam.IDS.progetto.Eccezioni.Itinerari.ItinerariAlreadyExistEccezione;
import it.unicam.IDS.progetto.Eccezioni.Itinerari.ItinerariNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseAlreadyExitsEccezione;
import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.Preferiti.PreferitiAlreadyExist;
import it.unicam.IDS.progetto.Eccezioni.Preferiti.PreferitiNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.Utente.UtenteNotFoundEccezione;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class Comune {

    @Id
    private String nomeComune;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeComune", referencedColumnName = "coordinate_id")
    private Coordinate coordinate;

    @NotNull
    private String cap;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PuntoInteresse> listaPDI;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StatoPendingPuntoInteresse> listaPendingPDI;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Itinerario> listaItinerari;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StatoPendingItinerario> listaPendingItinerari;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContestDiContribuzione> listaContest;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Utente> listaUtenti;

    public Comune(String nomeComune, double latitudine, double longitudine, String cap) {
        this.nomeComune = nomeComune;
        this.coordinate = new Coordinate(nomeComune, latitudine, longitudine);
        this.cap = cap;
        listaPDI = null;
        listaPendingPDI = null;
        listaItinerari = null;
        listaPendingItinerari = null;
        listaContest = null;
        listaUtenti = null;
    }

    public Comune(ArrayList<Utente> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public @NotNull String getCap() {
        return cap;
    }

    public void setCap(@NotNull String cap) {
        this.cap = cap;
    }

    public List<PuntoInteresse> getListaPDI() {
        return listaPDI;
    }

    public void setListaPDI(List<PuntoInteresse> listaPDI) {
        this.listaPDI = listaPDI;
    }

    public List<StatoPendingPuntoInteresse> getListaPendingPDI() {
        return listaPendingPDI;
    }

    public void setListaPendingPDI(List<StatoPendingPuntoInteresse> listaPendingPDI) {
        this.listaPendingPDI = listaPendingPDI;
    }

    public List<Itinerario> getListaItinerari() {
        return listaItinerari;
    }

    public void setListaItinerari(List<Itinerario> listaItinerari) {
        this.listaItinerari = listaItinerari;
    }

    public List<StatoPendingItinerario> getListaPendingItinerari() {
        return listaPendingItinerari;
    }

    public void setListaPendingItinerari(List<StatoPendingItinerario> listaPendingItinerari) {
        this.listaPendingItinerari = listaPendingItinerari;
    }

    public List<ContestDiContribuzione> getListaContest() {
        return listaContest;
    }

    public void setListaContest(List<ContestDiContribuzione> listaContest) {
        this.listaContest = listaContest;
    }

    public List<Utente> getListaUtenti() {
        return listaUtenti;
    }

    public void setListaUtenti(List<Utente> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    public void aggiungiContenuti(String nomePDI, ContenutiDtos contenutiDtos, String ruolo) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
        if (puntoInteresse.getListaContenuti().contains(contenutiDtos.getNomeContenuto()))
            throw new ContentAlreadyExistEccezione();
        Contenuti contenuti = new Contenuti(contenutiDtos.getNomeContenuto(),contenutiDtos.getContenuto());
        puntoInteresse.getListaContenuti().add(contenuti);

        statoPendingContenuti(ruolo, puntoInteresse);
    }

    private void statoPendingContenuti(String ruolo, PuntoInteresse puntoInteresse){
        IStatoPendingFactory factory = new StatoPendingPDIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingPuntoInteresse) {
            StatoPendingPuntoInteresse pdi = new StatoPendingPuntoInteresse(puntoInteresse.getNomePDI(),
                    puntoInteresse.getCoordinate().getLatitudine(),
                    puntoInteresse.getCoordinate().getLongitudine(), puntoInteresse.getListaContenuti());
            listaPDI.remove(puntoInteresse);
            listaPendingPDI.add(pdi);
        }
    }

    public void rimuoviContenuti(String nomePDI, String nomeContenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
        Contenuti contenuti = findContenutoPDI(puntoInteresse, nomeContenuto);

        puntoInteresse.getListaContenuti().remove(contenuti);
    }

    private PuntoInteresse findPDI(String nomePdi) {
        for (PuntoInteresse pdi : listaPDI)
            if (pdi.getNomePDI().equalsIgnoreCase(nomePdi))
                return pdi;
        throw new PuntoInteresseNotFoundEccezione();
    }

    private StatoPendingPuntoInteresse findPDIPending(String nomePdi) {
        for (StatoPendingPuntoInteresse pdi : listaPendingPDI)
            if (pdi.getNomePDI().equalsIgnoreCase(nomePdi))
                return pdi;
        throw new PuntoInteresseNotFoundEccezione();
    }

    private Itinerario findItinerario(String nomeItinerario) {
        for (Itinerario it : listaItinerari)
            if (it.getNomeItinerario().equalsIgnoreCase(nomeItinerario))
                return it;
        throw new ItinerariNotFoundEccezione();
    }

    private StatoPendingItinerario findItinerariPending(String nomeItinerario) {
        for (StatoPendingItinerario it : listaPendingItinerari)
            if (it.getNomeItinerario().equalsIgnoreCase(nomeItinerario))
                return it;
        throw new ItinerariNotFoundEccezione();
    }

    private ContestDiContribuzione findContest(String nomeContest) {
        for (ContestDiContribuzione cont : listaContest)
            if (cont.getNomeContest().equalsIgnoreCase(nomeContest))
                return cont;
        throw new ContestDiContribuzioneNotFoundEccezione();
    }

    private Contenuti findContenutoContestNonApprovato(ContestDiContribuzione contest, String nomeContenuti) {
        for (Contenuti c : contest.getContenuti())
            if (c.getNomeContenuto().equalsIgnoreCase(nomeContenuti))
                return c;
        throw new ContenutiNotFoundEccezione();
    }

    private Contenuti findContenutoContestApprovato(ContestDiContribuzione contest, String nomeContenuti) {
        for (Contenuti c : contest.getContenutiApprovati())
            if (c.getNomeContenuto().equalsIgnoreCase(nomeContenuti))
                return c;
        throw new ContenutiNotFoundEccezione();
    }

    private Contenuti findContenutoPDI(PuntoInteresse puntoInteresse, String nomeContenuti) {
        for (Contenuti c : puntoInteresse.getListaContenuti())
            if (c.getNomeContenuto().equalsIgnoreCase(nomeContenuti))
                return c;
        throw new ContenutiNotFoundEccezione();
    }

    private PuntoInteresse findPreferitiPdi(String nomePdi, Utente utente) {
        for (PuntoInteresse pdi : utente.getListaPreferitiPDI())
            if (pdi.getNomePDI().equalsIgnoreCase(nomePdi))
                return pdi;
        throw new PreferitiNotFoundEccezione();
    }

    private Itinerario findPreferitiItinerario(String nomeItinerario,Utente utente) {
        for (Itinerario it : utente.getListaPreferitiItinerario())
            if (it.getNomeItinerario().equalsIgnoreCase(nomeItinerario))
                return it;
        throw new PreferitiNotFoundEccezione();
    }

    private Utente findUtente(String nomeUtente) {
        for (Utente utente : listaUtenti)
            if (utente.getNome().equalsIgnoreCase(nomeUtente))
                return utente;
        throw new UtenteNotFoundEccezione();
    }

    public void inserimentoPDI(PuntoInteresseDtos pdi, String ruolo) {
        if (listaPDI.contains(pdi))
            throw new PuntoInteresseAlreadyExitsEccezione();
        PuntoInteresse puntoInteresse = new PuntoInteresse(pdi.getNomePDI(), pdi.getLatitudine(), pdi.getLongitudine());
        statoPendingPuntoInteresse(ruolo, puntoInteresse);
    }

    private void statoPendingPuntoInteresse(String ruolo,PuntoInteresse pdi){
        IStatoPendingFactory factory = new StatoPendingPDIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingPuntoInteresse) {
            StatoPendingPuntoInteresse puntoInteresse = new StatoPendingPuntoInteresse(pdi.getNomePDI(),
                    pdi.getCoordinate().getLatitudine(), pdi.getCoordinate().getLongitudine());
            listaPendingPDI.add(puntoInteresse);
        } else listaPDI.add(pdi);
    }

    public void eliminaPDI(String nomePDI) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
        listaPDI.remove(puntoInteresse);
    }

    public void approvazioneStatoPendingPDI(String pdiScelto, String scelta) {
        StatoPendingPuntoInteresse pdi = findPDIPending(pdiScelto);

        if (scelta.equalsIgnoreCase("Y")) {
            listaPendingPDI.remove(pdi);
            PuntoInteresse puntoInteresse = new PuntoInteresse(pdi.getNomePDI(), pdi.getLatitudine(), pdi.getLongitudine(), pdi.getListaContenuti());
            listaPDI.add(puntoInteresse);
            System.out.println("Il punto di interesse è stato approvato");
        } else {
            listaPendingPDI.remove(pdi);
            System.out.println("Il punto di interesse non è stato approvato quindi è stato eliminato");
        }
    }

    public void approvazioneStatoPendingItinerario(String itinerarioScelto, String scelta) {
        StatoPendingItinerario it = findItinerariPending(itinerarioScelto);

        if (scelta.equalsIgnoreCase("Y")) {
            listaPendingItinerari.remove(it);
            Itinerario itinerario = new Itinerario(it.getNomeItinerario(), it.getListaItinerarioPDI(), it.getListaFoto());
            listaItinerari.add(itinerario);
            System.out.println("L'itinerario è stato approvato");
        } else {
            listaPendingItinerari.remove(it);
            System.out.println("L'itinerario non è stato approvato quindi è stato eliminato");
        }
    }

    public void creaItinerario(ItinerarioDtos it1, String ruolo) {
        if (listaItinerari.contains(it1.getNomeItinerario()))
            throw new ItinerariAlreadyExistEccezione();
        Itinerario itinerario = new Itinerario(it1.getNomeItinerario());

        IStatoPendingFactory factory = new StatoPendingPIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingItinerario) {
            StatoPendingItinerario it = new StatoPendingItinerario(itinerario.getNomeItinerario());
            listaPendingItinerari.add(it);
        } else listaItinerari.add(itinerario);
    }

    public void eliminaItinerario(String nomeItinerario) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        listaItinerari.remove(itinerario);
    }

    public void aggiuntaPdiItinerario(String nomePuntoInteresse, String nomeItinerario, String ruolo) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        PuntoInteresse puntoInteresse = findPDI(nomePuntoInteresse);

        itinerario.getListaItinerarioPDI().add(puntoInteresse);
        IStatoPendingFactory factory = new StatoPendingPIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingItinerario) {
            StatoPendingItinerario it = new StatoPendingItinerario(itinerario.getNomeItinerario(),
                    itinerario.getListaItinerarioPDI(), itinerario.getListaFoto());
            listaItinerari.remove(itinerario);
            listaPendingItinerari.add(it);
        }
    }

    public void rimuoviPdiItinerario(String nomePuntoInteresse, String nomeItinerario) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        PuntoInteresse puntoInteresse = findPDI(nomePuntoInteresse);

        itinerario.getListaItinerarioPDI().remove(puntoInteresse);
    }

    public void creaContestDiContribuzione(ContestDiContribuzione contestDiContribuzione) {
        if (listaContest.contains(contestDiContribuzione))
            throw new ContestAlreadyExistEccezione();
        if (!(contestDiContribuzione.getDpc().isBefore(contestDiContribuzione.getDataFine()) &&
                contestDiContribuzione.getDpc().isAfter(contestDiContribuzione.getDataInizio())))
            throw new ContestOverTimeLimitEccezione();
        if (!contestDiContribuzione.getDataInizio().isBefore(contestDiContribuzione.getDataFine()))
            throw new ContestInvalidDataEccezione();
        if(contestDiContribuzione.isSuInvito() == true)
            System.out.println("Spedisci gli inviti");

        listaContest.add(contestDiContribuzione);
        System.out.println("Il contest di contribuzione è stato aggiunto");
    }

    public void eliminaContestDiContribuzione(String nomeContest) {
        ContestDiContribuzione contestDiContribuzione = findContest(nomeContest);
        listaContest.remove(contestDiContribuzione);
        System.out.println("Il contest di contribuzione è stato eliminato dalla piattaforma");
    }

    public void modificaContestDiContribuzione(String nomeContest, String param, String elemNuovo) {
        ContestDiContribuzione contest = findContest(nomeContest);
        if (("obiettivo").equalsIgnoreCase(param)) {
            contest.setObiettivo(elemNuovo);
            System.out.println("La modifica dell'obiettivo del contest di contribuzione è avvenuta");
        } else if(("tematica".equalsIgnoreCase(param))){
            contest.setTematica(elemNuovo);
            System.out.println("La modifica della tematica del contest di contribuzione è avvenuta");
        } else System.out.println("Parametro non valido, inserire l'obiettivo o la " +
                "tematica del contest che si vuole modificare");
    }

    public void proponiContenuti(String nomeContest, File file) {
        ContestDiContribuzione contest = findContest(nomeContest);
        if (contest.getContenuti().contains(file))
            throw new ContenutiNotFoundEccezione();
        Contenuti contenuto = new Contenuti(file.getName(), file);
        contest.getContenuti().add(contenuto);
        System.out.println("Il contenuto è stato proposto al contest di contribuzione");
    }

    public void validaContenuti(String nomeContest, String nomeContenuto, String approv) {
        ContestDiContribuzione contest = findContest(nomeContest);
        Contenuti contenuto = findContenutoContestNonApprovato(contest, nomeContenuto);
        if (approv.equalsIgnoreCase("Y")) {
            contest.getContenuti().remove(contenuto);
            contest.getContenutiApprovati().add(contenuto);
            System.out.println("Il contenuto è stato approvato");
        } else {
            contest.getContenuti().remove(contenuto);
            System.out.println("Il contenuto è stato eliminato perchè non è stato approvato");
        }
    }

    public void modificaComune(String param, String elemNuovo) {
        if (("nome").equalsIgnoreCase(param)) {
            setNomeComune(elemNuovo);
            System.out.println("La modifica del nome del comune è avvenuta");
        } else if(("cap").equalsIgnoreCase(param)){
            setCap(elemNuovo);
            System.out.println("La modifica del CAP del comune è avvenuta");
        } else System.out.println("Parametro invalido, inserire il nome o il cap del comune che si vuole modificare");
    }

    public void aggiungiPreferitiItinerario(String nomeItinerario,String nomeUtente) {
        Utente utente = findUtente(nomeUtente);
        Itinerario it = findItinerario(nomeItinerario);
        if (utente.getListaPreferitiItinerario().contains(it))
            throw new PreferitiAlreadyExist();

        utente.getListaPreferitiItinerario().add(it);
        System.out.println("L'itinerario è stato aggiunto ai preferiti");
    }

    public void aggiungiPreferitiPDI(String nomePdi,String nomeUtente) {
        Utente utente = findUtente(nomeUtente);
        PuntoInteresse pdi = findPDI(nomePdi);
        if (utente.getListaPreferitiPDI().contains(pdi))
            throw new PreferitiAlreadyExist();

        utente.getListaPreferitiPDI().add(pdi);
        System.out.println("IL punto di interesse è stato aggiunto ai preferiti");
    }

    public void rimuoviPreferitiPDI(String nomePdi,String nomeUtente) {
        Utente utente = findUtente(nomeUtente);
        PuntoInteresse pdi = findPreferitiPdi(nomePdi,utente);

        utente.getListaPreferitiPDI().remove(pdi);
        System.out.println("Il punto di interesse è stato rimosso dai preferiti");
    }

    public void rimuoviPreferitiItinerari(String nomeItinerari,String nomeUtente) {
        Utente utente = findUtente(nomeUtente);
        Itinerario it = findPreferitiItinerario(nomeItinerari,utente);

        utente.getListaPreferitiItinerario().remove(it);
        System.out.println("L'itinerario è stato rimosso dai preferiti");
    }

    public void aggiungiFotoItinerario(Foto foto, String nomeItinerario, String ruolo) {
        Itinerario it = findItinerario(nomeItinerario);

        it.getListaFoto().add(foto);
        IStatoPendingFactory factory = new StatoPendingPIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingItinerario) {
            StatoPendingItinerario itinerario = new StatoPendingItinerario(it.getNomeItinerario(),
                    it.getListaItinerarioPDI(), it.getListaFoto());
            listaItinerari.remove(it);
            listaPendingItinerari.add(itinerario);
        }
    }

    public void inviaMessaggi(Messaggio messaggio) {
        Utente destinatario = findUtente(messaggio.getDestinatario());

        destinatario.getListaMessaggiNonLetti().add(messaggio);
        System.out.println("Messaggio inviato a: " + destinatario.getNome());
    }

    public void decidiContenutoVincitore(String nomeContest, String nomeContenuto, Messaggio messaggio) {
        ContestDiContribuzione cont = findContest(nomeContest);
        Contenuti contenuto = findContenutoContestApprovato(cont, nomeContenuto);
        inviaMessaggi(messaggio);

        cont.setVincitore(contenuto.getContenuto());
        System.out.println("Il vincitore è stato scelto");
    }

    public void rimuoviFotoItinerario(int idFoto, String nomeItinerario) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        Foto foto = itinerario.getListaFoto().get(idFoto);
        itinerario.getListaFoto().remove(foto);
    }

    @Override
    public String toString() {
        return "Comune{" + '\n' +
                "nomeComune=" + nomeComune + '\n' +
                "coordinate=" + coordinate + '\n' +
                "cap=" + cap + '\n' +
                "listaPDI=" + listaPDI + '\n' +
                "listaPendingPDI=" + listaPendingPDI + '\n' +
                "listaItinerari=" + listaItinerari + '\n' +
                "listaPendingItinerari=" + listaPendingItinerari + '\n' +
                "listaContest=" + listaContest + '\n' +
                "listaUtenti=" + listaUtenti +
                '}';
    }

}
