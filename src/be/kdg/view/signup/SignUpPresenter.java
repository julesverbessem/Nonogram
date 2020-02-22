package be.kdg.view.signup;

import be.kdg.model.Gebruiker;
import be.kdg.model.GebruikersLijst;

public class SignUpPresenter {
    private GebruikersLijst model;
    private SignUpView view;

    public SignUpPresenter(GebruikersLijst model, SignUpView view){
        this.model = model;
        this.view = view;
    }
}
