package be.kdg;

import java.util.ArrayList;

public class GebruikersLijst {
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

    public void login (String gebruikersnaam, String passwoord){
        //later lijst overlopen om user te vinden
        for(Gebruiker huidigeGebruiker: lijst){
            if(huidigeGebruiker.getGebruikersnaam().equals(gebruikersnaam) && huidigeGebruiker.getPasswoord().equals(passwoord) ){
                if(huidigeGebruiker.getOpgeslagenSpel() == null){
                    huidigeGebruiker.setOpgeslagenSpel(new Spel());
                }
                //start het spel
            }
        }
    }
}
