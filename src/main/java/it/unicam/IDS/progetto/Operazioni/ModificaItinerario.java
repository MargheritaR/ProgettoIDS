package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Itinerario;
import it.unicam.IDS.progetto.Entita.PuntoInteresse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModificaItinerario {

    public void EditItinerario(ArrayList<Itinerario> listaItinerari, Itinerario itinerario,
                                   ArrayList<PuntoInteresse> listaPDI){
        Scanner scanner = new Scanner(System.in);
        // String nomeItinerarioScelto = Mostra(listaItinerari);

        System.out.println("Cosa vuoi modificare?" +
                "nome" +
                "punti di interesse all'interno");
        String appoggio = scanner.next();

        if (appoggio.equals("nome")) {
            System.out.println("Inserire il nuovo nome dell'itinerario");
            String nome = scanner.next();
            itinerario.setNomeItinerario(nome);
        } else {
            System.out.println("Inserire che azione si vuole fare sulla lista dei Punti di Interesse:" +
                    "Aggiungere" +
                    "Rimuovere");
            String azione = scanner.next();

            if (azione.equals("Aggiungere")) {
                System.out.println("Inserire il nome del punto di interesse");
                String pdi = scanner.next();

                int index = FindIndex(listaPDI,pdi);
                if (index == -1)
                    System.out.println("eccezione");

                List<PuntoInteresse> newlistaItinerariPDI = new ArrayList<>(itinerario.getListaItinerarioPDI());
                newlistaItinerariPDI.add(listaPDI.get(index));
                itinerario.setListaItinerarioPDI(newlistaItinerariPDI);
            } else {
                System.out.println("Inserire il nome del punto di interesse");
                String pdi = scanner.next();

                int index = FindIndex(listaPDI,pdi);
                if (index == -1)
                    System.out.println("eccezione");

                List<PuntoInteresse> newlistaItinerariPDI = new ArrayList<>(itinerario.getListaItinerarioPDI());
                newlistaItinerariPDI.remove(listaPDI.get(index));
                itinerario.setListaItinerarioPDI(newlistaItinerariPDI);
            }
        }
    }

    private String Mostra(ArrayList<Itinerario> listaItinerari) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista degli itinerari");
        System.out.println(listaItinerari);

        System.out.println('\n' + "Quale itinerario vuoi modificare: (nome dell'itinerario)");
        String itinerarioScelto = scanner.nextLine();

        return itinerarioScelto;
    }

    private int FindIndex(ArrayList<PuntoInteresse> listaPDI, String pdi) {
        for (int i = 0; i < listaPDI.size(); i++)
            if (listaPDI.get(i).getNomePDI().equals(pdi))
                return i;
        return -1;
    }

}
