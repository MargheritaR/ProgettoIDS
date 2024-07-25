package it.unicam.IDS.progetto.Entita;

import it.unicam.IDS.progetto.Eccezioni.Itinerari.ItinerariNotFoundEccezione;
import it.unicam.IDS.progetto.Eccezioni.PDI.PuntoInteresseNotFoundEccezione;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ArrayList<PuntoInteresse> listaPDI;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ArrayList<PuntoInteresse> listaPendingPDI;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ArrayList<Itinerario> listaItinerari;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ArrayList<Itinerario> listaPendingItinerari;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private ArrayList<ContestDiContribuzione> listaContest;

    public Comune(String nomeComune, double asseX, double asseY, String cap) {
        this.nomeComune = nomeComune;
        this.coordinate = new Coordinate(nomeComune, asseX, asseY);
        this.cap = cap;
        this.listaPDI = new ArrayList<>();
        this.listaPendingPDI = new ArrayList<>();
        this.listaContest = new ArrayList<>();
        this.listaItinerari = new ArrayList<>();
        this.listaPendingItinerari = new ArrayList<>();
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

    public ArrayList<PuntoInteresse> getListaPendingPDI() {
        return listaPendingPDI;
    }

    public void setListaPendingPDI(ArrayList<PuntoInteresse> listaPendingPDI) {
        this.listaPendingPDI = listaPendingPDI;
    }

    public ArrayList<Itinerario> getListaItinerari() {
        return listaItinerari;
    }

    public void setListaItinerari(ArrayList<Itinerario> listaItinerari) {
        this.listaItinerari = listaItinerari;
    }

    public ArrayList<Itinerario> getListaPendingItinerari() {
        return listaPendingItinerari;
    }

    public void setListaPendingItinerari(ArrayList<Itinerario> listaPendingItinerari) {
        this.listaPendingItinerari = listaPendingItinerari;
    }

    public ArrayList<ContestDiContribuzione> getListaContest() {
        return listaContest;
    }

    public void setListaContest(ArrayList<ContestDiContribuzione> listaContest) {
        this.listaContest = listaContest;
    }

    public void addContenuti(String nomePDI, Contenuti contenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
//        if (puntoInteresse == null)
//            System.out.println("Il punto di interesse non esiste");
        if (puntoInteresse.getListaContenuti().contains(contenuto))
            System.out.println("Il nome del contenuto inserito è già presente nella piattaforma");

        puntoInteresse.getListaContenuti().add(contenuto);
        System.out.println("Il contenuto è stato aggiunto al punto di interesse");
    }

    public void addContenutiPending(String nomePDI, Contenuti contenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
//        if (puntoInteresse == null)
//            System.out.println("Il punto di interesse non esiste");
        if (puntoInteresse.getListaContenuti().contains(contenuto))
            System.out.println("Il nome del contenuto inserito è già presente nella piattaforma");

        puntoInteresse.getListaContenuti().add(contenuto);
        listaPDI.remove(puntoInteresse);
        listaPendingPDI.add(puntoInteresse);
        System.out.println("Il contenuto è stato aggiunto allo stato pending");
    }

    public void rimuoviContenuti(String nomePDI, Contenuti contenuto) {
        PuntoInteresse puntoInteresse = findPDI(nomePDI);
//        if (puntoInteresse == null)
//            System.out.println("Il punto di interesse non esiste");
        if (!puntoInteresse.getListaContenuti().contains(contenuto))
            System.out.println("Il nome del contenuto da eliminare non è presente nella piattaforma");

        puntoInteresse.getListaContenuti().remove(contenuto);
        System.out.println("Il contenuto è stato eliminato dal punto di interesse");
    }

    private PuntoInteresse findPDI(String nomePdi){
        for(PuntoInteresse pdi:listaPDI)
            if(pdi.getNomePDI().equalsIgnoreCase(nomePdi))
                return pdi;
        throw new PuntoInteresseNotFoundEccezione();
    }

    private Itinerario findItinerario(String nomeItinerario){
        for(Itinerario it:listaItinerari)
            if (it.getNomeItinerario().equalsIgnoreCase(nomeItinerario))
                return it;
        throw new ItinerariNotFoundEccezione();
    }

    public void inserimentoPDI(PuntoInteresse puntoPDI) {
        if ((listaPDI.size() == 1) && listaPDI.contains(puntoPDI))
            System.out.println("Il nome del punto di interesse è già presente nella piattaforma");

        listaPDI.add(puntoPDI);
        System.out.println("Il punto di interesse è stato aggiunto");
    }

    public void inserimentoPendingPDI(PuntoInteresse puntoPDI) {
        if (listaPendingPDI.contains(puntoPDI))
            System.out.println("Il nome del punto di interesse è già presente nella piattaforma");

        listaPendingPDI.add(puntoPDI);
        System.out.println("Il punto di interesse è stato aggiunto allo stato di pending");
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

    public void creaItinerario(Itinerario itinerario) {
        if(listaItinerari.contains(itinerario))
            System.out.println("L'itinerario è già presente nella piattaforma");

        listaItinerari.add(itinerario);
        System.out.println("L'itinerario è stato aggiunto");
    }

    public void creaItinerarioPending(Itinerario itinerario) {
        if(listaItinerari.contains(itinerario))
            System.out.println("L'itinerario è già presente nella piattaforma");

        listaPendingItinerari.add(itinerario);
        System.out.println("L'itinerario è stato aggiunto allo stato pending");
    }

    public void eliminaItinerario(Itinerario itinerario) {
        if(!listaItinerari.contains(itinerario))
            System.out.println("L'itinerario da eliminare non è presente nella piattaforma");

        listaItinerari.remove(itinerario);
        System.out.println("L'itinerario è stato eliminato dalla piattaforma");
    }

    public void aggiuntaPdiItinerario(String nomePuntoInteresse, String nomeItinerario) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        PuntoInteresse puntoInteresse = findPDI(nomePuntoInteresse);

        //TODO controllare se vanno i controlli di null
        itinerario.getListaItinerarioPDI().add(puntoInteresse);
        System.out.println("Il punto di interesse è stato aggiunto all'itinerario");
    }

    public void aggiuntaPendingPdiItinerario(String nomePuntoInteresse, String nomeItinerario) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        PuntoInteresse puntoInteresse = findPDI(nomePuntoInteresse);

        itinerario.getListaItinerarioPDI().add(puntoInteresse);
        listaItinerari.remove(itinerario);
        System.out.println("Il punto di interesse è stato aggiunto all'itinerario");
        listaPendingItinerari.add(itinerario);
        System.out.println("L'itinerario è stato aggiunto allo stato di pending");
    }

    public void rimuoviPdiItinerario(String nomePuntoInteresse, String nomeItinerario) {
        Itinerario itinerario = findItinerario(nomeItinerario);
        PuntoInteresse puntoInteresse = findPDI(nomePuntoInteresse);

        itinerario.getListaItinerarioPDI().remove(puntoInteresse);
        System.out.println("Il punto di interesse è stato rimosso dall'itinerario");
    }

    public void creaContestDiContribuzione(ContestDiContribuzione contestDiContribuzione){
        if(listaContest.contains(contestDiContribuzione))
            System.out.println("Il contest di contribuzione è già presente nella piattaforma");
        if(!(contestDiContribuzione.getDpc().isBefore(contestDiContribuzione.getDataFine()) &&
                contestDiContribuzione.getDpc().isAfter(contestDiContribuzione.getDataInizio())))
            System.out.println("L'ultima data disponibile per la possibilità di aggiungere contenuti " +
                    "per il contest di contribuzione non è compresa tra la data di inizio e la data di fine");
        if (!contestDiContribuzione.getDataInizio().isBefore(contestDiContribuzione.getDataFine()))
            System.out.println("La data di inizio deve essere  prima della data di fine");

        listaContest.add(contestDiContribuzione);
        System.out.println("Il contest di contribuzione è stato aggiunto");
    }

    public void eliminaContestDiContribuzione(ContestDiContribuzione contestDiContribuzione){
        if(!listaContest.contains(contestDiContribuzione))
            System.out.println("Il contest di contribuzione da eliminare non è presente nella piattaforma");
        listaContest.remove(contestDiContribuzione);
        System.out.println("Il contest di contribuzione è stato eliminato dalla piattaforma");

    }

    @Override
    public String toString() {
        return "Comune{" +
                "nomeComune='" + nomeComune + '\'' +
                ", coordinate=" + coordinate +
                ", cap='" + cap + '\'' +
                ", listaPDI=" + listaPDI +
                ", listaPendingPDI=" + listaPendingPDI +
                ", listaItinerari=" + listaItinerari +
                ", listaPendingItinerari=" + listaPendingItinerari +
                ", listaContest=" + listaContest +
                '}';
    }
}
