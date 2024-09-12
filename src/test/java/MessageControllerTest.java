package edu.eci.arep.virtualization.controller;

import edu.eci.arep.virtualization.model.Message;
import edu.eci.arep.virtualization.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMessageSuccess() {
        // Arrange
        List<Message> mockMessages = Arrays.asList(
                new Message("Message 1"),
                new Message("Message 2"),
                new Message("Message 3"),
                new Message("Message 4"),
                new Message("Message 5"),
                new Message("Message 6"),
                new Message("Message 7"),
                new Message("Message 8"),
                new Message("Message 9"),
                new Message("Message 10")
        );
        when(messageService.getlastTenMessage()).thenReturn(mockMessages);

        // Act
        ResponseEntity<?> response = messageController.getMessage("Test message");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        assertEquals(10, ((List<?>) response.getBody()).size());
    }

    @Test
    void testGetMessageError() {
        // Arrange
        when(messageService.getlastTenMessage()).thenThrow(new RuntimeException("Error fetching messages"));

        // Act
        ResponseEntity<?> response = messageController.getMessage("Test message");

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("{\"error\":\"Error al obtener los últimos 10 mensajes\"}", response.getBody());
    }
}
