package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gangoffour2.monopoly.azioni.casella.AttesaLancioDadi;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AzioneGiocatore;
import com.gangoffour2.monopoly.model.PartitaObserver;
import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;


@Data
@SuperBuilder
@JsonIgnoreProperties(value = {"evento", "subscribers"})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Terreno.class, name = "Terreno"),
        @JsonSubTypes.Type(value = Societa.class, name = "Societa"),
        @JsonSubTypes.Type(value = Stazione.class, name = "Stazione"),
        @JsonSubTypes.Type(value = Via.class, name = "Via"),
        @JsonSubTypes.Type(value = Imprevisto.class, name = "Imprevisto"),
        @JsonSubTypes.Type(value = Probabilita.class, name = "Probabilita"),
        @JsonSubTypes.Type(value = Prigione.class, name = "Prigione"),
        @JsonSubTypes.Type(value = Parcheggio.class, name = "Parcheggio"),
        @JsonSubTypes.Type(value = Tassa.class, name = "Tassa"),
        @JsonSubTypes.Type(value = VaiInPrigione.class, name = "VaiInPrigione"),
})
public abstract class Casella implements SubjectStatoPartita, Serializable {

    protected String nome;
    @Builder.Default
    protected ArrayList<PartitaObserver> subscribers = new ArrayList<>();
    protected EventoCasella evento;


    @JsonProperty("type")
    public abstract String getTipo();

    public void arrivo(){
        evento.arrivo();
    }
    public void passaggio(){
        notificaTutti(evento.passaggio());
    }

    public void fineGiro(){
        evento.fineGiro();
    }

    protected Casella(){
        subscribers = new ArrayList<>();
    }

    /**
     * Da overrideare per i comportamenti diversi
     */
    public void inizioTurno() {
        notificaTutti(AttesaLancioDadi.builder().build());
    }

    @Override
    public void notificaTutti(AzioneCasella azione){
        subscribers.forEach(subscriber -> {
            try {
                subscriber.onAzioneCasella(azione);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Override
    public void aggiungi(PartitaObserver observer){
        subscribers.add(observer);
    }

    @Override
    public void rimuovi(PartitaObserver observer){
        subscribers.remove(observer);
    }


    public void onAzioneGiocatore(AzioneGiocatore azioneGiocatore){
        azioneGiocatore.accept(evento);
    }
}