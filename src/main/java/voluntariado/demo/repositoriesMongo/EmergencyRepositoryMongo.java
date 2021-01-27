package voluntariado.demo.repositoriesMongo;


import org.bson.Document;

import java.util.List;

public interface EmergencyRepositoryMongo {
    public List<String> getAllEmergency();

    List<Document> join();
}
