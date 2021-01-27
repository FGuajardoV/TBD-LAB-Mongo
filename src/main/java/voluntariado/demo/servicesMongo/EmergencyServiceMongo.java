package voluntariado.demo.servicesMongo;

import org.bson.Document;
import voluntariado.demo.repositoriesMongo.EmergencyRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class EmergencyServiceMongo {

    @Autowired
    private EmergencyRepositoryMongo emergencyRepository;

    @GetMapping("/emergencias")
    public List<String> getAllEmergency() {
        return emergencyRepository.getAllEmergency();
    }

    @GetMapping("/emergencias/t")
    public List<Document> join() { return emergencyRepository.join(); }

}
