package be.kdg;

import java.util.ArrayList;
import java.util.Collections;

public class GebruikersLijst extends Gebruiker {
    private ArrayList<Gebruiker> lijst;

    public GebruikersLijst() {
        this.lijst = new ArrayList<>();
    }

    public ArrayList<Gebruiker> getLijst() {
        return lijst;
    }

    public void setLijst(ArrayList<Gebruiker> lijst) {
        this.lijst = lijst;
    }

    public void setGebruiker(String gebruikersnaam, String passwoord) {
        Gebruiker gebruiker = new Gebruiker(gebruikersnaam, passwoord);
        this.lijst.add(gebruiker);
        gebruiker.opgeslagenSpel.startSpel(gebruiker);
    }

    public void login(String gebruikersnaam, String passwoord) {
        //later lijst overlopen om user te vinden
        for (Gebruiker huidigeGebruiker : lijst) {
            if (huidigeGebruiker.getGebruikersnaam().equals(gebruikersnaam) && huidigeGebruiker.getPasswoord().equals(passwoord)) {
                if (huidigeGebruiker.getOpgeslagenSpel() == null) {
                    huidigeGebruiker.setOpgeslagenSpel(new Spel());
                }
                huidigeGebruiker.opgeslagenSpel.startSpel(huidigeGebruiker);
            } else {
                System.out.println("Foute gebruikersnaam of wachtwoord");
            }
        }
    }

    public void overzichtSpelers() {
        /* PEERTUTOR VRAGEN      */
        Collections.sort(lijst);
        for (Gebruiker huidigeGebruiker : lijst) {
            System.out.println(String.format("%s -- %d", huidigeGebruiker.getGebruikersnaam(), huidigeGebruiker.getLevel()));
        }
    }

    public void startScherm() {
        System.out.println("-----NONOGRAM-----");
        System.out.println("");
        System.out.println("");
    }
}

