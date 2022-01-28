package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.casella.Casella;

public interface RandomTabelloneStrategy {
    void randomizzaCasella(Casella c);
}
