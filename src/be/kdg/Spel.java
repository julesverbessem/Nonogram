package be.kdg;

public class Spel {
    Gebruiker mijnGebruiker = new Gebruiker();
    Nonogram mijnNonogram = new Nonogram(1);

    public void startSpel() {
        switch (mijnGebruiker.getLevel()){
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
    public void startVolgendSpel(){
        int lvl = mijnGebruiker.getLevel();
        int lvlUp = lvl++;
        mijnGebruiker.setLevel(lvlUp);
        startSpel();
    }
}
