
import edu.eci.arep.virtualization.model.Message;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageTest {

    @Test
    void testMessageConstructorWithContent() {
        // Arrange
        String content = "Hello, World!";
        Message message = new Message(content);

        // Act
        String messageContent = message.getContent();
        String messageDate = message.getDate();
        String messageId = message.getId();

        // Assert
        assertEquals(content, messageContent, "Content should match the provided content");
        assertNotNull(messageDate, "Date should not be null");
        assertNotNull(messageId, "ID should not be null");
    }

    @Test
    void testMessageDefaultConstructor() {
        // Arrange
        Message message = new Message();

        // Act
        String messageContent = message.getContent();
        String messageDate = message.getDate();
        String messageId = message.getId();

        // Assert
        assertEquals(null, messageContent, "Content should be null by default");
        assertEquals(null, messageDate, "Date should be null by default");
        assertEquals(null, messageId, "ID should be null by default");
    }

    @Test
    void testMessageDateFormat() {
        // Arrange
        Message message = new Message("Sample message");

        // Act
        String messageDate = message.getDate();

        // Assert
        // Ensure that the date is in a valid format. You may need a more complex check depending on requirements.
        assertNotNull(messageDate, "Date should not be null");
        assertEquals(LocalDateTime.parse(messageDate).getDayOfYear(), LocalDateTime.now().getDayOfYear(), "Date should be today's date");
    }
}
