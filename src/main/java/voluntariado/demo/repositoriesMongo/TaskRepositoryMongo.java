package voluntariado.demo.repositoriesMongo;
import voluntariado.demo.Document.Task;

import java.util.List;
import org.bson.Document;


public interface TaskRepositoryMongo {

    public List<Task> getTasks();
    public Task createTask(Task task);

}