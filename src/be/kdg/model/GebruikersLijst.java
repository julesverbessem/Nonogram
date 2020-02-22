package be.kdg.model;

import java.util.ArrayList;
import java.util.Collections;

public class GebruikersLijst extends Gebruiker {
    private ArrayList<Gebruiker> lijst;

    public GebruikersLijst() {
        this.lijst = new ArrayList<>();
        setGebruiker("Jules","Test");
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

    public boolean login(String gebruikersnaam, String passwoord) {
        //later lijst overlopen om user te vinden
        for (Gebruiker huidigeGebruiker : lijst) {
            if (huidigeGebruiker.getGebruikersnaam().equals(gebruikersnaam) && huidigeGebruiker.getPasswoord().equals(passwoord)) {
                if (huidigeGebruiker.getOpgeslagenSpel() == null) {
                    huidigeGebruiker.setOpgeslagenSpel(new Spel());
                    huidigeGebruiker.setLevel(1);
                    System.out.println("Gebruiker heeft nog geen spel, hij start bij 1");
                    return true;
                }
                huidigeGebruiker.opgeslagenSpel.startSpel(huidigeGebruiker);
                System.out.println("Gebruiker is ingeloged");
                return true;
            } else {
                System.out.println("Foute gebruikersnaam of wachtwoord");
            }
            return false;
        }
        return false;
    }

    public void overzichtSpelers() {
        /* PEERTUTOR VRAGEN      */
        Collections.sort(lijst);
        for (Gebruiker huidigeGebruiker : lijst) {
            System.out.println(String.format("%s -- %d", huidigeGebruiker.getGebruikersnaam(), huidigeGebruiker.getLevel()));
        }
    }
}

