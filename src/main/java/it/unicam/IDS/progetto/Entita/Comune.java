package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContentAlreadyExistEccezione;
import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContenutiNotFoundEccezione;
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
    private ArrayList<PuntoInteresse> listaPDI = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<StatoPendingPuntoInteresse> listaPendingPDI = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Itinerario> listaItinerari = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<StatoPendingItinerario> listaPendingItinerari = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<ContestDiContribuzione> listaContest = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Itinerario> listaPreferitiItinerario = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<PuntoInteresse> listaPreferitiPDI = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArrayList<Utente> listaUtenti;

    public Comune(String nomeComune, double asseX, double asseY, String cap) {
        this.nomeComune = nomeComune;
        this.coordinate = new Coordinate(nomeComune, asseX, asseY);
        this.cap = cap;
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

    public ArrayList<PuntoInteresse> getListaPDI() {
        return listaPDI;
    }

    public void setListaPDI(ArrayList<PuntoInteresse> listaPDI) {
        this.listaPDI = listaPDI;
    }

    public ArrayList<Itinerario> getListaItinerari() {
        return listaItinerari;
    }

    public void setListaItinerari(ArrayList<Itinerario> listaItinerari) {
        this.listaItinerari = listaItinerari;
    }

    public ArrayList<ContestDiContribuzione> getListaContest() {
        return listaContest;
    }

    public void setListaContest(ArrayList<ContestDiContribuzione> listaContest) {
        this.listaContest = listaContest;
    }

    public ArrayList<StatoPendingPuntoInteresse> getListaPendingPDI() {
        return listaPendingPDI;
    }

    public void setListaPendingPDI(ArrayList<StatoPendingPuntoInteresse> listaPendingPDI) {
        this.listaPendingPDI = listaPendingPDI;
    }

    public ArrayList<StatoPendingItinerario> getListaPendingItinerari() {
        return listaPendingItinerari;
    }

    public void setListaPendingItinerari(ArrayList<StatoPendingItinerario> listaPendingItinerari) {
        this.listaPendingItinerari = listaPendingItinerari;
    }

    public ArrayList<Itinerario> getListaPreferitiItinerario() {
        return listaPreferitiItinerario;
    }

    public void setListaPreferitiItinerario(ArrayList<Itinerario> listaPreferitiItinerario) {
        this.listaPreferitiItinerario = listaPreferitiItinerario;
    }

    public ArrayList<PuntoInteresse> getListaPreferitiPDI() {
        return listaPreferitiPDI;
    }

    public void setListaPreferitiPDI(ArrayList<PuntoInteresse> listaPreferitiPDI) {
        this.listaPreferitiPDI = listaPreferitiPDI;
    }

    public ArrayList<Utente> getListaUtenti() {
        return listaUtenti;
    }

    public void setListaUtenti(ArrayList<Utente> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }

    public void addContenuti(String nomePDI, Contenuti contenuto, String ruolo) {
        //uso la string ruolo, che verrà tolta in spring boot, per controllare che ruolo ha l'utente
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
        if (puntoInteresse.getListaContenuti().contains(contenuto))
            System.out.println("Il nome del contenuto inserito è già presente nella piattaforma");
        puntoInteresse.getListaContenuti().add(contenuto);

        IStatoPendingFactory factory = new StatoPendingPDIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingPuntoInteresse) {
            StatoPendingPuntoInteresse pdi = new StatoPendingPuntoInteresse(puntoInteresse.getNomePDI(),
                    puntoInteresse.getCoordinate().getLatitudine(),
                    puntoInteresse.getCoordinate().getLongitudine(), puntoInteresse.getListaContenuti());
            listaPDI.remove(puntoInteresse);
            listaPendingPDI.add(pdi);
        }

        System.out.println("Il contenuto è stato aggiunto al punto di interesse");
    }

    public void rimuoviContenuti(String nomePDI, String nomeContenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
        Contenuti contenuti = findContenutoPDI(puntoInteresse, nomeContenuto);

        puntoInteresse.getListaContenuti().remove(contenuti);
        System.out.println("Il contenuto è stato eliminato dal punto di interesse");
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

    private PuntoInteresse findPreferitiPdi(String nomePdi) {
        for (PuntoInteresse pdi : listaPreferitiPDI)
            if (pdi.getNomePDI().equalsIgnoreCase(nomePdi))
                return pdi;
        throw new PreferitiNotFoundEccezione();
    }

    private Itinerario findPreferitiItinerario(String nomeItinerario) {
        for (Itinerario it : listaPreferitiItinerario)
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

    public void inserimentoPDI(PuntoInteresse puntoPDI, String ruolo) {
        //uso la string ruolo, che verrà tolta in spring boot, per controllare che ruolo ha l'utente
        if (listaPDI.contains(puntoPDI))
            throw new PuntoInteresseAlreadyExitsEccezione();
        IStatoPendingFactory factory = new StatoPendingPDIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingPuntoInteresse) {
            StatoPendingPuntoInteresse puntoInteresse = new StatoPendingPuntoInteresse(puntoPDI.getNomePDI(),
                    puntoPDI.getCoordinate().getLatitudine(), puntoPDI.getCoordinate().getLongitudine());
            listaPendingPDI.add(puntoInteresse);
        } else listaPDI.add(puntoPDI);

        System.out.println("Il punto di interesse è stato aggiunto");
    }

    public void eliminaPDI(String nomePDI) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
        listaPDI.remove(puntoInteresse);
        System.out.println("il punto di interesse è stato eliminato");
    }

    public void approvazioneStatoPendingPDI(String pdiScelto, String scelta) {
        StatoPendingPuntoInteresse pdi = findPDIPending(pdiScelto);

        if (scelta.equalsIgnoreCase("Y")) {
            listaPendingPDI.remove(pdi);
            PuntoInteresse puntoInteresse = new PuntoInteresse(pdi.getNomePDI(), pdi.getAsseX(), pdi.getAsseY(), pdi.getListaContenuti());
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

    public void creaItinerario(Itinerario itinerario, String ruolo) {
        //uso la string ruolo, che verrà tolta in spring boot, per controllare che ruolo ha l'utente
        if (listaItinerari.contains(itinerario))
            throw new ItinerariAlreadyExistEccezione();

        IStatoPendingFactory factory = new StatoPendingPIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingItinerario) {
            StatoPendingItinerario it = new StatoPendingItinerario(itinerario.getNomeItinerario());
            listaPendingItinerari.add(it);
        } else listaItinerari.add(itinerario);
        System.out.println("L'itinerario è stato aggiunto");
    }

    public void eliminaItinerario(String nomeItinerario) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        listaItinerari.remove(itinerario);
        System.out.println("L'itinerario è stato eliminato dalla piattaforma");
    }

    public void aggiuntaPdiItinerario(String nomePuntoInteresse, String nomeItinerario, String ruolo) {
        //uso la string ruolo, che verrà tolta in spring boot, per controllare che ruolo ha l'utente
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
        System.out.println("Il punto di interesse è stato aggiunto all'itinerario");
    }

    public void rimuoviPdiItinerario(String nomePuntoInteresse, String nomeItinerario) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        PuntoInteresse puntoInteresse = findPDI(nomePuntoInteresse);

        itinerario.getListaItinerarioPDI().remove(puntoInteresse);
        System.out.println("Il punto di interesse è stato rimosso dall'itinerario");
    }

    public void creaContestDiContribuzione(ContestDiContribuzione contestDiContribuzione) {
        if (listaContest.contains(contestDiContribuzione))
            throw new ContentAlreadyExistEccezione();
        if (!(contestDiContribuzione.getDpc().isBefore(contestDiContribuzione.getDataFine()) &&
                contestDiContribuzione.getDpc().isAfter(contestDiContribuzione.getDataInizio())))
            throw new ContestOverTimeLimitEccezione();
        if (!contestDiContribuzione.getDataInizio().isBefore(contestDiContribuzione.getDataFine()))
            throw new ContestInvalidDataEccezione();

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

    public void aggiungiPreferitiItinerario(String nomeItinerario) {
        Itinerario it = findItinerario(nomeItinerario);
        if (listaPreferitiItinerario.contains(it))
            throw new PreferitiAlreadyExist();

        listaPreferitiItinerario.add(it);
        System.out.println("L'itinerario è stato aggiunto ai preferiti");
    }

    public void aggiungiPreferitiPDI(String nomePdi) {
        PuntoInteresse pdi = findPDI(nomePdi);
        if (listaPreferitiPDI.contains(pdi))
            throw new PreferitiAlreadyExist();

        listaPreferitiPDI.add(pdi);
        System.out.println("IL punto di interesse è stato aggiunto ai preferiti");
    }

    public void rimuoviPreferitiPDI(String nomePdi) {
        PuntoInteresse pdi = findPreferitiPdi(nomePdi);

        listaPreferitiPDI.remove(pdi);
        System.out.println("Il punto di interesse è stato rimosso dai preferiti");
    }

    public void rimuoviPreferitiItinerari(String nomeItinerari) {
        Itinerario it = findPreferitiItinerario(nomeItinerari);

        listaPreferitiItinerario.remove(it);
        System.out.println("L'itinerario è stato rimosso dai preferiti");
    }

    public void aggiungiFotoItinerario(Foto foto, String nomeItinerario, String ruolo) {
        //uso la string ruolo, che verrà tolta in spring boot, per controllare che ruolo ha l'utente
        Itinerario it = findItinerario(nomeItinerario);

        it.getListaFoto().add(foto);
        IStatoPendingFactory factory = new StatoPendingPIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingItinerario) {
            StatoPendingItinerario itinerario = new StatoPendingItinerario(it.getNomeItinerario(), it.getListaItinerarioPDI(), it.getListaFoto());
            listaItinerari.remove(it);
            listaPendingItinerari.add(itinerario);
        }
        System.out.println("La foto è stata aggiunta all'itinerario");
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
        System.out.println("La foto è stata rimossa dall'itinerario");
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
                "listaPreferitiItinerario=" + listaPreferitiItinerario + '\n' +
                "listaPreferitiPDI=" + listaPreferitiPDI + '\n' +
                "listaUtenti=" + listaUtenti +
                '}';
    }

}
