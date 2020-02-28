package be.kdg.model;

import be.kdg.model.Gebruiker;
import be.kdg.model.Nonogram;

public class Spel {
    Nonogram mijnNonogram;

    public int startSpel(Gebruiker speler) {
        switch (speler.getLevel()){
            case 1:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram1_lijn();
                break;
            case 2:
                mijnNonogram = new Nonogram(5);
                mijnNonogram.nonogram2_lijnVerticaal();
                break;
                //aanvullen tot 10
        }
        return  mijnNonogram.getGrootte();
    }
    public void startVolgendSpel(Gebruiker speler){
        int lvl = speler.getLevel();
        int lvlUp = lvl+1;
        speler.setLevel(lvlUp);
    }

}
