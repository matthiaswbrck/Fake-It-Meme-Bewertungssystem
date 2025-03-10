package com.example.Bewertungssystem.Controller;

import com.example.Bewertungssystem.model.Bewertung;
import com.example.Bewertungssystem.model.Meme;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bewertung")
public class BewertungsController {
    private final BewertungService bewertungService;

    public BewertungsController(BewertungService bewertungService) {
        this.bewertungService = bewertungService;
    }

    @GetMapping("/memes")
    public List<Meme> getMemes() {
        return bewertungService.ladeMemes();
    }

    @PostMapping("/bewerten")
    public void bewerten(@RequestBody Bewertung bewertung) {
        bewertungService.speichereBewertung(bewertung);
    }

    @GetMapping("/finale-punkte")
    public List<Meme> berechneFinalePunkte() {
        return bewertungService.berechnePunkte();
    }
}
