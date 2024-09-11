package edu.eci.arep.virtualization.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Message model of message class
 * @author Andrés Arias
 */
@Document(collection = "messages")
public class Message {

    @Id
    private String id;
    private String content;
    private String date;

    /**
     * Constructor of message CLass
     * @param messageContent
     */
    public Message(String messageContent) {
        this.id = UUID.randomUUID().toString();
        this.content = messageContent;
        this.date = LocalDateTime.now().toString();
    }

    /**
     * Empty constructor fot message class
     */
    public Message() {
    }

    /**
     * Get message
     * @return Message
     */
    public String getContent() {
        return content;
    }

    /**
     * Get date
     * @return LocalDate od message
     */
    public String getDate() {
        return date;
    }

    /**
     * Get id of message
     * @return String id
     */
    public String getId() {
        return id;
    }

}
