package com.example.Bewertungssystem.service;

import com.example.Bewertungssystem.model.Meme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class MemeManager {

    // Liste aller zu bewertenden Memes
    private List<Meme> memes;
    // Index des aktuell anzuzeigenden Memes
    private int currentMemeIndex;
    // JSON-Mapper (z.B. Jackson), um Meme-Objekte in JSON zu konvertieren
    private ObjectMapper objectMapper;

    public MemeManager() {
        this.memes = new ArrayList<>();
        this.currentMemeIndex = 0;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Lädt eine Liste von Meme-Objekten, z.B. nachdem die JSON-Dateien eingelesen und in Objekte umgewandelt wurden.
     *
     * @param memeList Liste der Memes, die bewertet werden sollen.
     */
    public void loadMemes(List<Meme> memeList) {
        this.memes = memeList;
        this.currentMemeIndex = 0;
    }

    /**
     * Liefert das nächste Meme, sofern noch vorhanden. Falls alle Memes durchlaufen wurden, wird null zurückgegeben.
     *
     * @return Das nächste Meme zur Bewertung oder null, wenn keine Memes mehr vorhanden sind.
     */
    public Meme nextMeme() {
        if (currentMemeIndex < memes.size()) {
            Meme next = memes.get(currentMemeIndex);
            currentMemeIndex++;
            return next;
        }
        return null;
    }

    /**
     * Exportiert die final bewerteten Memes als Liste von JSON-Strings.
     *
     * @return Liste von JSON-Darstellungen der Memes.
     */
    public List<String> exportResults() {
        List<String> results = new ArrayList<>();
        for (Meme meme : memes) {
            // Vor dem Export sicherstellen, dass die Score-Berechnung bereits erfolgt ist.
            meme.calculateScore();
            try {
                String json = objectMapper.writeValueAsString(meme);
                results.add(json);
            } catch (JsonProcessingException e) {
                // Fehlerbehandlung: Im Fehlerfall kann hier eine Log-Ausgabe erfolgen.
                System.err.println("Fehler beim Serialisieren des Memes: " + meme.getImageUrl());
            }
        }
        return results;
    }
}
