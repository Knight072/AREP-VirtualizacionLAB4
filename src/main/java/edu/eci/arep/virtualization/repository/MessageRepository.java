package edu.eci.arep.virtualization.repository;

import edu.eci.arep.virtualization.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Message mongo repository
 * @author Andrés Arias
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
}
