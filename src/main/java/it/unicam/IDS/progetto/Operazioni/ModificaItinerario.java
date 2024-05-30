package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Itinerario;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModificaItinerario {

    Scanner scanner = new Scanner(System.in);

    public void EditItinerario(Itinerario itinerario,ArrayList<PuntoInteresse> listaPDI){
        System.out.println("Cosa vuoi modificare?" + '\n' + "nome" + '\n' + "pdi");
        String appoggio = scanner.next();

        if (appoggio.equals("nome")) {
            System.out.println("Inserire il nuovo nome dell'itinerario");
            itinerario.setNomeItinerario(scanner.next());
        } else {
            System.out.println("Inserire che azione si vuole fare sulla lista dei Punti di Interesse:" + '\n' +
                    "Aggiungere" + '\n' + "Rimuovere");
            String azione = scanner.next();
            System.out.println("Inserire il nome del punto di interesse");
            String pdi = scanner.next();

            if (azione.equals("Aggiungere"))
                AggiungerePDI(listaPDI,itinerario,pdi);
            else
                EliminaPDI(listaPDI,itinerario,pdi);
        }
    }

    private int FindIndex(ArrayList<PuntoInteresse> listaPDI, String pdi) {
        for (int i = 0; i < listaPDI.size(); i++)
            if (listaPDI.get(i).getNomePDI().equals(pdi))
                return i;
        return -1;
    }

    private void AggiungerePDI(ArrayList<PuntoInteresse> listaPDI,Itinerario itinerario,String pdi) {
        int index = FindIndex(listaPDI,pdi);
        if (index == -1)
            System.out.println("eccezione");

        List<PuntoInteresse> newlistaItinerariPDI = new ArrayList<>(itinerario.getListaItinerarioPDI());
        newlistaItinerariPDI.add(listaPDI.get(index));
        itinerario.setListaItinerarioPDI(newlistaItinerariPDI);
    }

    private void EliminaPDI(ArrayList<PuntoInteresse> listaPDI,Itinerario itinerario,String pdi) {
        int index = FindIndex(listaPDI,pdi);
        if (index == -1)
            System.out.println("eccezione");

        List<PuntoInteresse> newlistaItinerariPDI = new ArrayList<>(itinerario.getListaItinerarioPDI());
        newlistaItinerariPDI.remove(listaPDI.get(index));
        itinerario.setListaItinerarioPDI(newlistaItinerariPDI);
    }

}
