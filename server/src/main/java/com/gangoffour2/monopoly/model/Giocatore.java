package com.gangoffour2.monopoly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.carta.CartaEsciGratisPrigione;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Giocatore implements Serializable {
    @JsonIgnore
    private IPartita partita;
    @JsonIgnore
    private String idSessione;
    private String nick;
    private int conto;
    private LinkedList<CartaEsciGratisPrigione> esciGratis;
    private Casella casellaCorrente;

    @Builder.Default
    private ArrayList<Proprieta> proprietaPossedute = new ArrayList<>();

    public void aggiungiDenaro(int importo) throws ModificaDenaroException {
        if (this.getConto() + importo < 0)
            throw new ModificaDenaroException();
        this.setConto(this.getConto() + importo);
    }


    public void paga(Giocatore destinatario, int importo) {
        conto -= importo;
        destinatario.conto += importo;
    }

    public void acquistaProprieta(Proprieta proprieta) {
        aggiudica(proprieta, proprieta.getCostoBase());
    }


    public void abbandona() {
        lasciaProprieta();
    }

    public void lasciaProprieta() {
        for (Proprieta p : proprietaPossedute) {
            p.setProprietario(null);
        }
        proprietaPossedute.clear();
    }

    public void aggiungiEsciGratis(CartaEsciGratisPrigione c) {
        this.getEsciGratis().add(c);
    }

    public CartaEsciGratisPrigione rimuoviEsciGratis() {
        return this.getEsciGratis().remove();
    }

    public boolean haCartaEsciGratis() {
        return this.getEsciGratis().size() > 0;
    }

    public void aggiudica(Proprieta proprieta, int importo) {
        conto -= importo;
        proprietaPossedute.add(proprieta);
        proprieta.setProprietario(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Giocatore giocatore = (Giocatore) o;
        return nick.equals(giocatore.nick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick);
    }
}
