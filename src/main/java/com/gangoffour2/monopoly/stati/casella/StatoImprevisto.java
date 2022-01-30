package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.PescaImprevisto;
import com.gangoffour2.monopoly.model.casella.Imprevisto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoImprevisto implements StatoCasella {

    private Imprevisto imprevisto;

    @Override
    public AzioneCasella arrivo(){
        return PescaImprevisto.builder().build();
    }
}