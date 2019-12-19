package be.kdg;

public class Spel {
    Nonogram mijnNonogram = new Nonogram(1);

    public void startSpel(Gebruiker speler) {
        switch (speler.getLevel()){
            case 1:
                mijnNonogram.setGrootte(5);
                mijnNonogram.nonogram1_lijn();
                break;
            case 2:
                mijnNonogram.setGrootte(5);
                mijnNonogram.nonogram2_lijnVerticaal();
                break;
                //aanvullen tot 10
        }
    }
    public void startVolgendSpel(Gebruiker speler){
        int lvl = speler.getLevel();
        int lvlUp = lvl++;
        speler.setLevel(lvlUp);
        startSpel(speler);
    }
}
