package be.kdg.model;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class GebruikersLijst extends Gebruiker {
    private ArrayList<Gebruiker> lijst;

    public GebruikersLijst() {
        this.lijst = new ArrayList<>();
        setGebruiker(new Gebruiker("Jules","Test"));
    }

    public ArrayList<Gebruiker> getLijst() {
        return lijst;
    }

    public Gebruiker getGebruiker(String gebruikersnaam){
        for (Gebruiker huidigeGebruiker: lijst) {
            if(gebruikersnaam.equals(huidigeGebruiker.getGebruikersnaam())){
                return huidigeGebruiker;
            }
        }
        return null;
    }

    public void setLijst(ArrayList<Gebruiker> lijst) {
        this.lijst = lijst;
    }

    public void setGebruiker(Gebruiker nieweSpeler) {
        this.lijst.add(nieweSpeler);
        //nieweSpeler.opgeslagenSpel.startSpel(nieweSpeler);
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
                //huidigeGebruiker.opgeslagenSpel.startSpel(huidigeGebruiker);
                System.out.println("Gebruiker is ingeloged");
                return true;
            } else {
                System.out.println("Foute gebruikersnaam of wachtwoord");
            }
            return false;
        }
        return false;
    }

    public String overzichtSpelers() {
        Collections.sort(lijst);
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        for (Gebruiker huidigeGebruiker : lijst) {
            counter++;
            DateTimeFormatter format =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = format.format(huidigeGebruiker.getDatum());
            System.out.println(String.format("%d. %s ( %s) --------------------- %d",counter, huidigeGebruiker.getGebruikersnaam(),date, huidigeGebruiker.getLevel()));
            stringBuilder.append(String.format("%d. %s ( %s) --------------------- %d",counter, huidigeGebruiker.getGebruikersnaam(),date, huidigeGebruiker.getLevel()));
        }
        return stringBuilder.toString();
    }
}

