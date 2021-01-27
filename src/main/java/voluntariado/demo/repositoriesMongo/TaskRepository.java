package voluntariado.demo.RepositoriesMongo;
import voluntariado.demo.Document.Task;

import java.util.List;
import org.bson.Document;


public interface TaskRepositoryMongo {

    public List<Task> getTask();
    public Task createTarea(Task task);

}