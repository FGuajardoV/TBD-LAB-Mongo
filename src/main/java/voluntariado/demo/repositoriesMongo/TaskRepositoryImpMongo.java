package voluntariado.demo.repositoriesMongo;

import voluntariado.demo.Document.Task;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpMongo implements TaskRepositoryMongo{

    @Autowired
    MongoDatabase database;

    @Override
    public List<Task> getTasks() {
        MongoCollection<Task> collection = database.getCollection("Task", Task.class);
        List <Task> emergencias = collection.find().into(new ArrayList<>());

        return emergencias;
    }

    @Override
    public Task createTask(Task task) {
        MongoCollection<Task> collection = database.getCollection("task", Task.class);
        collection.insertOne(task);
        return task;
    }

}