package com.example.Bewertungssystem.model;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Meme {

    // Das Memebild wird hier als URL dargestellt. Alternativ könnte es auch als Byte-Array vorliegen.
    private String imageUrl;
    // ID und Name des Erstellers
    private String creatorId;
    private String creatorName;
    // Sammlung der Bewertungen: Key = Spieler-ID, Value = Rating-Objekt
    private Map<String, Rating> ratings;
    // Liste der Spieler, die den Meme-Brudi/Sis Bonus vergeben haben (jede ID wird nur einmalig gespeichert)
    private List<String> memeBrudiList;
    // Die berechnete Gesamtpunktzahl des Memes
    private int totalScore;

    // Standardkonstruktor initialisiert die Collections
    public Meme() {
        this.ratings = new HashMap<>();
        this.memeBrudiList = new ArrayList<>();
    }

    // Konstruktor mit Parametern
    public Meme(String imageUrl, String creatorId, String creatorName) {
        this();
        this.imageUrl = imageUrl;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
    }

    // Getter und Setter
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
    public String getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
    public Map<String, Rating> getRatings() {
        return ratings;
    }
    public void setRatings(Map<String, Rating> ratings) {
        this.ratings = ratings;
    }
    public List<String> getMemeBrudiList() {
        return memeBrudiList;
    }
    public void setMemeBrudiList(List<String> memeBrudiList) {
        this.memeBrudiList = memeBrudiList;
    }
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Fügt eine neue Bewertung hinzu oder aktualisiert eine bestehende Bewertung.
     * @param rating Die Bewertung, die hinzugefügt oder aktualisiert werden soll.
     */
    public void addOrUpdateRating(com.example.Bewertungssystem.model.Rating rating) {
        // Ersetzt die vorhandene Bewertung, falls der Spieler bereits bewertet hat.
        ratings.put(rating.getPlayerId(), rating);
    }

    /**
     * Fügt einen Meme-Brudi/Sis Bonus hinzu, sofern der Spieler diesen Bonus noch nicht vergeben hat.
     * @param playerId Die ID des Spielers, der den Bonus vergibt.
     */
    public void addMemeBrudi(String playerId) {
        if (!memeBrudiList.contains(playerId)) {
            memeBrudiList.add(playerId);
        }
    }

    /**
     * Aggregiert alle Bewertungen und addiert Bonus-Punkte für jeden Meme-Brudi/Sis.
     * Die einzelnen Bewertungen könnten z. B. +1 (Upvote), -1 (Downvote) oder 0 (neutral) sein.
     */
    public void calculateScore() {
        int score = 0;
        // Summiere alle abgegebenen Bewertungen
        for (Rating rating : ratings.values()) {
            score += rating.getValue();
        }
        // Addiere den Bonus: 20 Punkte pro Meme-Brudi/Sis
        score += memeBrudiList.size() * 20;
        this.totalScore = score;
    }

    // Optional: Eine Methode zum Export in JSON könnte hier ergänzt werden,
    // wobei z. B. Jackson genutzt wird, um diese Klasse direkt zu serialisieren.
}
