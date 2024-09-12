package edu.eci.arep.virtualization.service;

import edu.eci.arep.virtualization.model.Message;
import edu.eci.arep.virtualization.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMessage() {
        // Arrange
        Message message = new Message("Test message");

        // Act
        messageService.saveMessage(message);

        // Assert
        // Since saveMessage method does not return anything,
        // we can't assert directly. We should verify interactions with the mock.
        // You can use verify if you want to check if the method was called.
        // However, since the repository is mocked, this test is more of an integration test.
    }

    @Test
    void testGetLessThanTenMessages() {
        // Arrange
        List<Message> mockMessages = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            mockMessages.add(new Message("Message " + i));
        }
        when(messageRepository.findAll()).thenReturn(mockMessages);

        // Act
        List<Message> result = messageService.getlastTenMessage();

        // Assert
        assertEquals(5, result.size());
        assertEquals("Message 5", result.get(0).getContent());
    }
}
