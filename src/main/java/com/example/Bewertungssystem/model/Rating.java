package com.example.Bewertungssystem.model;

public class Rating {

    // Die ID des Spielers, der die Bewertung abgegeben hat.
    private String playerId;
    // Der Wert der Bewertung: +1 (Upvote), -1 (Downvote) oder 0 (neutral).
    private int value;
    // Flag, das anzeigt, ob der Spieler den Meme-Brudi/Sis Bonus vergeben hat.
    private boolean memeBrudi;

    // Standardkonstruktor
    public Rating() {
    }

    // Konstruktor mit allen Attributen
    public Rating(String playerId, int value, boolean memeBrudi) {
        this.playerId = playerId;
        this.value = value;
        this.memeBrudi = memeBrudi;
    }

    // Getter und Setter
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isMemeBrudi() {
        return memeBrudi;
    }

    public void setMemeBrudi(boolean memeBrudi) {
        this.memeBrudi = memeBrudi;
    }
}
