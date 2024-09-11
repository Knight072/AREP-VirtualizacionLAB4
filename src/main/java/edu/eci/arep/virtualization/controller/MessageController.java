package edu.eci.arep.virtualization.controller;

import edu.eci.arep.virtualization.model.Message;
import edu.eci.arep.virtualization.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Message controller
 * @author Andrés Arias
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class MessageController {

    /**
     * Message service
     */
    private final MessageService messageService;

    /**
     * Constructor of message controller
     * @param messageService
     */
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    /**
     * Get last ten messages
     * @param message message to save
     * @return List<Message>
     */
    @GetMapping("/message")
    public ResponseEntity<?> getMessage(@RequestParam String message) {
        Message newMessage = new Message(message);
        messageService.saveMessage(newMessage);
        try{
            return new ResponseEntity<>(messageService.getlastTenMessage(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>("{\"error\":\"Error al obtener los últimos 10 mensajes\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
