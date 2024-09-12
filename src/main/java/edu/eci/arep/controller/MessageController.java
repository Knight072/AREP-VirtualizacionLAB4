package edu.eci.arep.controller;

import edu.eci.arep.model.Message;
import edu.eci.arep.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

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
