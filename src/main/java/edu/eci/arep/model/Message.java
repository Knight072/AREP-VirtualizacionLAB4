package edu.eci.arep.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "messages")
public class Message {

    @Id
    private String id;
    private String content;
    private String date;

    public Message(String messageContent) {
        this.id = UUID.randomUUID().toString();
        this.content = messageContent;
        this.date = LocalDateTime.now().toString();
    }

    public Message() {
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

}
