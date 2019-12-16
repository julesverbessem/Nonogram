package be.kdg;

import java.lang.reflect.Array;

public class Nonogram {
    private int moeilijkheidsgraad;
    private String naam;
    private String[] patroon = {"X",};

    public Nonogram(int moeilijkheidsgraad, String naam) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.naam = naam;
        }

    public int getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    public void setMoeilijkheidsgraad(int moeilijkheidsgraad) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
    }

    public String getNaam() {
        return naam;
    }

    public String[] getPatroon() {
        return patroon;
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
        for(int i = 1; i < 11;i++) {
            patroon[i] = new Vakje().getWaarde();
        }
        for(int i = 1; i < 11;i++) {
            if(i%10== 0){
                grid.append(patroon[i-1]).append(" ").append("\n");
            }else{
                grid.append(patroon[i-1]).append(" ");
        }
            System.out.println(grid.append("\n").toString());

        }



    }

}
