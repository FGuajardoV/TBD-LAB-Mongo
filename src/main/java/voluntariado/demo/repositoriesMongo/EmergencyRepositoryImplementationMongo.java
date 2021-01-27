package voluntariado.demo.repositoriesMongo;

import com.mongodb.BasicDBList;
import com.mongodb.client.*;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import static com.mongodb.client.model.Aggregates.lookup;
import static com.mongodb.client.model.Aggregates.unwind;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EmergencyRepositoryImplementationMongo implements EmergencyRepositoryMongo {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<String> getAllEmergency(){
        MongoClient mongoClient = MongoClients.create();
        MongoDatabase voluntariadodb = mongoClient.getDatabase("voluntariado");
        MongoCollection<Document> collection = voluntariadodb.getCollection("emergencias");
        MongoCursor<Document> iterator = collection.find().iterator();

        BasicDBList list = new BasicDBList();
        while (iterator.hasNext()) {
            Document doc = iterator.next();
            list.add(doc);
        }

        List<String> listArray = new ArrayList<String>();
        for (Object object : list) {
            listArray.add(object.toString());
        }
        return listArray;
        //return mongoTemplate.findAll(Emergency.class);
    }

    @Override
    public List<Document> join() {
        /* hay que pasar esto a java */

        /*
            db.tareas.aggregate([
            {
                $lookup:
                  {
                    from: "emergencias",
                    localField: "id_emergencia",
                    foreignField: "_id",
                    as: "tareas_porEmergencia"
                  }
             },
             {
                $unwind: "$tareas_porEmergencia"
             }
            ]).pretty()
        * */

        //String connectionString = System.getProperty("mongodb.uri");
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase db = mongoClient.getDatabase("voluntariado");
            MongoCollection<Document> emergencias = db.getCollection("emergencias");
            MongoCollection<Document> tareas = db.getCollection("tareas");
            List<Document> resp = tareasPorEmergencia(tareas, emergencias);
            return resp;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Document> tareasPorEmergencia(MongoCollection<Document> tareas, MongoCollection<Document> emergencias) {
        //Bson filter = lookup("tareas", "_id", "id_emergencia", "tareas_porEmergencia"),unwind("$tareas_porEmergencia");
        Bson lookup = lookup("tareas", "_id", "id_emergencia", "tareas_porEmergencia");
        Bson unwind = unwind("$tareas_porEmergencia");

        List<Document> results = emergencias.aggregate(Arrays.asList(lookup, unwind)).into(new ArrayList<>());
        return results;
    }
}
