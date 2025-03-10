package com.example.bewertungssystem.model;

public class Player {

    // Eindeutige ID des Spielers
    private String playerId;
    // Name des Spielers
    private String playerName;

    // Standardkonstruktor
    public Player() {
    }

    // Konstruktor mit Parametern
    public Player(String playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
    }

    // Getter und Setter
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Optional: toString-Methode zur einfachen Darstellung der Spielerinformationen
    @Override
    public String toString() {
        return "Player{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                '}';
    }
}
