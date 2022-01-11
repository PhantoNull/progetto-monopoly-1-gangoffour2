package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;

@Builder
public class Ipoteca extends AzioneGiocatore{

    protected Ipoteca(){

    }

    @Override
    public void accept(Casella casella) {
        casella.onAzioneGiocatore(this);
    }

    @Override
    public void accept(StatoPartita statoPartita) throws InterruptedException {
        statoPartita.onAzioneGiocatore(this);
    }
}