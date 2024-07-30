package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Eccezioni.Contenuti.ContenutiNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.ContestDiContribuzione.ContestDiContribuzioneNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.Itinerari.ItinerariNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseNotFoundEccezione;
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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
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

    public void rimuoviContenuti(String nomePDI, Contenuti contenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
        if (!puntoInteresse.getListaContenuti().contains(contenuto))
            System.out.println("Il nome del contenuto da eliminare non è presente nella piattaforma");

        puntoInteresse.getListaContenuti().remove(contenuto);
        System.out.println("Il contenuto è stato eliminato dal punto di interesse");
    }

    private PuntoInteresse findPDI(String nomePdi) {
        for (PuntoInteresse pdi : listaPDI)
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

    private ContestDiContribuzione findContest(String nomeContest) {
        for (ContestDiContribuzione cont : listaContest)
            if (cont.getNomeContest().equalsIgnoreCase(nomeContest))
                return cont;
        throw new ContestDiContribuzioneNotFoundEccezione();
    }

    private Contenuti findContenuto(ContestDiContribuzione contest, String nomeContenuti) {
        for (Contenuti c : contest.getContenuti())
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
        for(Utente utente:listaUtenti)
            if(utente.getNome().equalsIgnoreCase(nomeUtente))
                return utente;
        throw new UtenteNotFoundEccezione();
    }

    public void inserimentoPDI(PuntoInteresse puntoPDI, String ruolo) {
        //uso la string ruolo, che verrà tolta in spring boot, per controllare che ruolo ha l'utente
        if (listaPDI.contains(puntoPDI))
            System.out.println("Il nome del punto di interesse è già presente nella piattaforma");
        IStatoPendingFactory factory = new StatoPendingPDIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingPuntoInteresse) {
            StatoPendingPuntoInteresse puntoInteresse = new StatoPendingPuntoInteresse(puntoPDI.getNomePDI(),
                    puntoPDI.getCoordinate().getLatitudine(), puntoPDI.getCoordinate().getLongitudine());
            listaPendingPDI.add(puntoInteresse);
        } else
            listaPDI.add(puntoPDI);

        System.out.println("Il punto di interesse è stato aggiunto");
    }

    public void eliminaPDI(PuntoInteresse puntoPDI) {
        if (!listaPDI.contains(puntoPDI))
            System.out.println("Il nome del punto di interesse da eliminare non è presente nella piattaforma");

        listaPDI.remove(puntoPDI);
        System.out.println("il punto di interesse è stato eliminato");
    }

    public void approvazioneStatoPendingPDI(String pdiScelto, String scelta) {
        PuntoInteresse pdi = findPDI(pdiScelto);
        if (!listaPendingPDI.contains(pdi))
            throw new PuntoInteresseNotFoundEccezione();

        if (scelta.equalsIgnoreCase("Y")) {
            listaPendingPDI.remove(pdi);
            listaPDI.add(pdi);
            System.out.println("Il punto di interesse è stato approvato");
        } else {
            listaPendingPDI.remove(pdi);
            System.out.println("Il punto di interesse non è stato approvato quindi è stato eliminato");
        }
    }

    public void approvazioneStatoPendingItinerario(String itinerarioScelto, String scelta) {
        Itinerario it = findItinerario(itinerarioScelto);
        if (!listaPendingItinerari.contains(it))
            throw new ItinerariNotFoundEccezione();

        if (scelta.equalsIgnoreCase("Y")) {
            listaPendingItinerari.remove(it);
            listaItinerari.add(it);
            System.out.println("L'itinerario è stato approvato");
        } else {
            listaPendingItinerari.remove(it);
            System.out.println("L'itinerario non è stato approvato quindi è stato eliminato");
        }
    }

    public void creaItinerario(Itinerario itinerario, String ruolo) {
        //uso la string ruolo, che verrà tolta in spring boot, per controllare che ruolo ha l'utente
        if (listaItinerari.contains(itinerario))
            System.out.println("L'itinerario è già presente nella piattaforma");

        IStatoPendingFactory factory = new StatoPendingPIFactory();
        IStatoPending appoggio = factory.newStatoPending(ruolo);
        if (appoggio instanceof StatoPendingItinerario) {
            StatoPendingItinerario it = new StatoPendingItinerario(itinerario.getNomeItinerario());
            listaPendingItinerari.add(it);
        } else
            listaItinerari.add(itinerario);
        System.out.println("L'itinerario è stato aggiunto");
    }

    public void eliminaItinerario(Itinerario itinerario) {
        if (!listaItinerari.contains(itinerario))
            System.out.println("L'itinerario da eliminare non è presente nella piattaforma");

        listaItinerari.remove(itinerario);
        System.out.println("L'itinerario è stato eliminato dalla piattaforma");
    }

    public void aggiuntaPdiItinerario(String nomePuntoInteresse, String nomeItinerario, String ruolo) {
        //uso la string ruolo, che verrà tolta in spring boot, per controllare che ruolo ha l'utente
        Itinerario itinerario = findItinerario(nomeItinerario);
        PuntoInteresse puntoInteresse = findPDI(nomePuntoInteresse);

        //TODO controllare se vanno i controlli di null
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
            System.out.println("Il contest di contribuzione è già presente nella piattaforma");
        if (!(contestDiContribuzione.getDpc().isBefore(contestDiContribuzione.getDataFine()) &&
                contestDiContribuzione.getDpc().isAfter(contestDiContribuzione.getDataInizio())))
            System.out.println("L'ultima data disponibile per la possibilità di aggiungere contenuti " +
                    "per il contest di contribuzione non è compresa tra la data di inizio e la data di fine");
        if (!contestDiContribuzione.getDataInizio().isBefore(contestDiContribuzione.getDataFine()))
            System.out.println("La data di inizio deve essere  prima della data di fine");

        listaContest.add(contestDiContribuzione);
        System.out.println("Il contest di contribuzione è stato aggiunto");
    }

    public void eliminaContestDiContribuzione(ContestDiContribuzione contestDiContribuzione) {
        if (!listaContest.contains(contestDiContribuzione))
            System.out.println("Il contest di contribuzione da eliminare non è presente nella piattaforma");

        listaContest.remove(contestDiContribuzione);
        System.out.println("Il contest di contribuzione è stato eliminato dalla piattaforma");
    }

    public void modificaContestDiContribuzione(String nomeContest, String param, String elemNuovo) {
        ContestDiContribuzione contest = findContest(nomeContest);
        if (("obiettivo").equalsIgnoreCase(param)) {
            contest.setObiettivo(elemNuovo);
            System.out.println("La modifica dell'obiettivo del contest di contribuzione è avvenuta");
        } else {
            contest.setTematica(elemNuovo);
            System.out.println("La modifica della tematica del contest di contribuzione è avvenuta");
        }
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
        Contenuti contenuto = findContenuto(contest, nomeContenuto);
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
        } else {
            setCap(elemNuovo);
            System.out.println("La modifica del CAP del comune è avvenuta");
        }
    }

    public void aggiungiPreferitiItinerario(String nomeItinerario) {
        Itinerario it = findItinerario(nomeItinerario);
        //TODO cambiare eccezzione
        if (listaPreferitiItinerario.contains(it))
            throw new ItinerariNotFoundEccezione();

        listaPreferitiItinerario.add(it);
        System.out.println("L'itinerario è stato aggiunto ai preferiti");
    }

    public void aggiungiPreferitiPDI(String nomePdi) {
        PuntoInteresse pdi = findPDI(nomePdi);
        //TODO cambiare eccezzione
        if (listaPreferitiPDI.contains(pdi))
            throw new PuntoInteresseNotFoundEccezione();

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

    public void InviaMessaggi(Messaggio messaggio){
        Utente destinatario = findUtente(messaggio.getDestinatario());

        destinatario.getListaMessaggiNonLetti().add(messaggio);
        System.out.println("Messaggio inviato a: "+ destinatario.getNome());
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
                "listaContest=" + listaContest +
                '}';
    }
}
