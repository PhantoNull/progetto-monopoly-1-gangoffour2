package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.ModificaDenaro;
import com.gangoffour2.monopoly.model.casella.Via;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoVia implements StatoCasella {
    private Via via;

    @Override
    public AzioneCasella passaggio() {
        return ModificaDenaro.builder()
                .importo(via.getImporto())
                .build();
    }
}
