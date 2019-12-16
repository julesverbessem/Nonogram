package be.kdg;

import java.lang.reflect.Array;

public class Nonogram {
    private int moeilijkheidsgraad;
    private int grootte;
    private String naam;
    private Vakje[] patroon;

    public Nonogram(int moeilijkheidsgraad, int grootte, String naam) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.grootte = grootte;
        this.naam = naam;
        this.patroon = new Vakje[grootte];
    }

    public int getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    public void setMoeilijkheidsgraad(int moeilijkheidsgraad) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
    }

    public int getGrootte() {
        return grootte;
    }

    public void setGrootte(int grootte) {
        this.grootte = grootte;
    }

    public String getNaam() {
        return naam;
    }

    public Vakje[] getPatroon() {
        return patroon;
    }

    public void setPatroon(Vakje[] patroon) {
        this.patroon = patroon;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void controlleren() {

    }

    public String feliciteren() {
        return "Gefeliciteerd, heeft het nonogram " + this.naam + " afgewerkt";
    }

    public void toonGrid() {
        StringBuilder grid = new StringBuilder();
        for(int i = 0; i < grootte*grootte;i++){
            if(i%grootte == 0){
                grid.append(patroon[i-1].getWaarde()).append(" ").append("\n");
            }else{
                grid.append(patroon[i-1].getWaarde()).append(" ");                }
        }



    }

}
