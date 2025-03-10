package com.example.Bewertungssystem.websocket;

import com.example.Bewertungssystem.model.Meme;
import com.example.Bewertungssystem.model.Rating;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommunicationManager {

    private final SimpMessagingTemplate messagingTemplate;

    public CommunicationManager(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Sendet das aktuelle Meme an alle verbundenen Clients.
     * Die Clients abonnieren z. B. den "/topic/meme" Endpunkt, um neue Memes zu erhalten.
     *
     * @param meme Das Meme, das an alle Clients gesendet wird.
     */
    public void broadcastMeme(Meme meme) {
        messagingTemplate.convertAndSend("/topic/meme", meme);
    }

    /**
     * Sendet Status-Updates (z. B. den Beginn oder das Ende einer Bewertungsrunde) an alle Clients.
     *
     * @param status Die Statusnachricht, die verteilt werden soll.
     */
    public void sendStatusUpdate(String status) {
        messagingTemplate.convertAndSend("/topic/status", status);
    }

    /**
     * Verarbeitet eingehende Bewertungsupdates von Clients.
     * Hier könnte man z. B. Logik integrieren, um die Bewertung im Backend zu aktualisieren,
     * und danach den aktualisierten Status des Memes oder der Bewertungsrunde an alle Clients zu senden.
     *
     * @param playerId Die ID des Spielers, der seine Bewertung geändert hat.
     * @param rating   Das aktualisierte Rating-Objekt.
     */
    public void processRatingUpdate(String playerId, Rating rating) {
        // Beispiel: Sende das aktualisierte Rating an alle Clients.
        // In einer echten Anwendung würden hier weitere Logiken (z. B. Validierung, Persistierung) folgen.
        messagingTemplate.convertAndSend("/topic/ratingUpdate", rating);
    }
}
