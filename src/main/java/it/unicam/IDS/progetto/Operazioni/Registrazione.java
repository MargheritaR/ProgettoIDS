package it.unicam.IDS.progetto.Operazioni;

import it.unicam.IDS.progetto.Entita.Comune;
import it.unicam.IDS.progetto.Entita.Utente;

import java.util.ArrayList;
import java.util.Scanner;

public class Registrazione {

    Scanner scanner = new Scanner(System.in);

    public void Registrati(ArrayList<Utente> listaUtente, ArrayList<Comune> listaComune, Utente utente) {
        System.out.println("Scegli il comune in cui ti vuoi registrare");
        System.out.println(listaComune);
        String comunescelto = scanner.nextLine();

        //TODO creare eccezione e lanciarla
        if (comunescelto.equals(""))
            System.out.println("Il comune scelto non può essere vuoto");
        if (!listaComune.contains(comunescelto))
            System.out.println("Il comune scelto non è presente nel sito");
        if (listaUtente.contains(utente))
            System.out.println("L'email è stata già utilizzata");
        if (utente.getPassword().equals(""))
            System.out.println("La password non può essere vuota");
        if (!ControlloPassword(utente.getPassword()))
            System.out.println("La password non rispetta i parametri di sicurezza" +
                    "La password deve essere lunga almeno 8 caratteri, avere una Maiuscola, una minuscola, un numero," +
                    "e un carattere speciale");

        listaUtente.add(utente);
        System.out.println("La registrazione è avvenuta con successo");
    }

    private boolean ControlloPassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        return password.matches(regex);
    }

}
