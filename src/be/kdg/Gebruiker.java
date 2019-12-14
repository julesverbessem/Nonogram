package be.kdg;

public class Gebruiker {
    private String gebruikersnaam;
    private String passwoord;
    private Spel opgeslagenSpel;

    public Gebruiker(String gebruikersnaam, String passwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.passwoord = passwoord;
    }

    public Gebruiker(String gebruikersnaam, String passwoord, Spel opgeslagenSpel) {
        this.gebruikersnaam = gebruikersnaam;
        this.passwoord = passwoord;
        this.opgeslagenSpel = opgeslagenSpel;
    }

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

    public void login (){//String gebruikersnaam, String passwoord => parameters
        //later lijst overlopen om user te vinden
        if(opgeslagenSpel == null){
            opgeslagenSpel = new Spel();
        }
            opgeslagenSpel.startSpel();
    }
}
