package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class AzioneGiocatore {
    private Giocatore giocatore;

    protected AzioneGiocatore(){

    }

    public abstract void accept(EventoCasella eventoCasella);
    public abstract void accept(StatoPartita statoPartita) throws InterruptedException;
}
