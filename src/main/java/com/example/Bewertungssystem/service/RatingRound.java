package com.example.Bewertungssystem.service;

import com.example.Bewertungssystem.model.Meme;
import com.example.Bewertungssystem.model.Rating;

import java.util.Timer;
import java.util.TimerTask;

public class RatingRound {

    private Meme currentMeme;
    private Timer timer;
    // Flag, um zu überprüfen, ob die Bewertungsrunde aktiv ist
    private boolean roundActive;

    // Dauer der Bewertungsrunde in Millisekunden (hier: 10 Sekunden)
    private static final long ROUND_DURATION_MS = 10000;

    public RatingRound(Meme meme) {
        this.currentMeme = meme;
        this.timer = new Timer();
        this.roundActive = false;
    }

    /**
     * Startet die Bewertungsrunde für das aktuelle Meme.
     * Initiiert den Timer, der nach Ablauf der festgelegten Dauer endRound() aufruft.
     */
    public void startRound() {
        roundActive = true;
        System.out.println("Bewertungsrunde gestartet für Meme: " + currentMeme.getImageUrl());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endRound();
            }
        }, ROUND_DURATION_MS);
    }

    /**
     * Aktualisiert die Bewertung eines Spielers während der aktiven Runde.
     * @param playerId Die ID des Spielers, der bewertet.
     * @param rating Das Rating-Objekt, das den Wert und ggf. den Meme-Brudi/Sis-Status enthält.
     */
    public void updateRating(String playerId, Rating rating) {
        if (!roundActive) {
            System.out.println("Runde ist nicht aktiv. Bewertung wird ignoriert.");
            return;
        }
        // Aktualisiert oder fügt die Bewertung zum aktuellen Meme hinzu.
        currentMeme.addOrUpdateRating(rating);
        System.out.println("Bewertung von Spieler " + playerId + " aktualisiert.");
    }

    /**
     * Beendet die Bewertungsrunde.
     * Berechnet die finale Punktzahl des Memes und setzt die Runde als inaktiv.
     */
    public void endRound() {
        if (!roundActive) {
            return;
        }
        roundActive = false;
        timer.cancel();
        // Berechnung der Gesamtpunktzahl, inklusive Bonus-Punkte
        currentMeme.calculateScore();
        System.out.println("Bewertungsrunde beendet. Finale Punktzahl: " + currentMeme.getTotalScore());
        // Hier könntest du zusätzlich den finalen Zustand an den Manager oder CommunicationManager übergeben.
    }

    // Getter für das aktuelle Meme
    public Meme getCurrentMeme() {
        return currentMeme;
    }

    // Prüft, ob die Runde noch aktiv ist.
    public boolean isRoundActive() {
        return roundActive;
    }
}
