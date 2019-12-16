package be.kdg;

import java.lang.reflect.Array;

public class Nonogram {
    private int moeilijkheidsgraad;
    private String naam;
    private int grootte;
    private Vakje[][] patroon;

    public Nonogram(int moeilijkheidsgraad, String naam, int grootte) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.naam = naam;
        this.grootte = grootte;
        this.patroon = new Vakje[grootte][grootte];
        for(int rij = 1; rij <= grootte; rij++){
            for(int kolom = 1; kolom <= grootte; kolom++){
                int r = rij;
                int k = kolom;
                this.patroon[r-1][k-1] = new Vakje();
            }
        }
        }

    public int getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    public String getNaam() {
        return naam;
    }


    public void controlleren() {

    }

    public String feliciteren() {
        return "Gefeliciteerd, heeft het nonogram " + this.naam + " afgewerkt";
    }

    public void toonGrid(){
        StringBuilder builder = new StringBuilder();
        for(int rij = 1; rij < grootte; rij++){
            for(int kolom = 1; kolom < grootte; kolom++){
                int r = rij;
                int k = kolom;
                builder.append(patroon[r-1][k-1].getWaarde()).append(" ");
                if(kolom%grootte==0){

                    builder.append(patroon[r-1][k-1].getWaarde()).append(" ").append("\n");
                }
            }
        }
        System.out.println(builder.append("\n").toString());
    }


}
