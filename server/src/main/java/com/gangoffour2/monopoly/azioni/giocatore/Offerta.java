package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;

@Builder
public class Offerta extends AzioneGiocatore {
    @Override
    public void accept(EventoCasella eventoCasella) {
        eventoCasella.onAzioneGiocatore(this);
    }

    @Override
    public void accept(StatoPartita statoPartita) throws InterruptedException {
        statoPartita.onAzioneCasella(this);
    }
}