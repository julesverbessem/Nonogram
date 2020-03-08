package be.kdg.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Gebruiker implements Comparable<Gebruiker>{
    private String gebruikersnaam;
    private String passwoord;
    public Spel opgeslagenSpel;
    private int level;
    private LocalDateTime datum;

    public Gebruiker(String gebruikersnaam, String passwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.passwoord = passwoord;
        this.opgeslagenSpel = new Spel();
        this.level = 2;//aanpassen !!!!!!
        this.datum = LocalDateTime.now();
    }
    public Gebruiker(){};

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getPasswoord() {
        return passwoord;
    }

    public void setPasswoord(String passwoord) {
        this.passwoord = passwoord;
    }

    public Spel getOpgeslagenSpel() {
        return opgeslagenSpel;
    }

    public void setOpgeslagenSpel(Spel opgeslagenSpel) {
        this.opgeslagenSpel = opgeslagenSpel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public int compareTo(Gebruiker gbrkr1){
        if(this.getLevel()<gbrkr1.getLevel()){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
