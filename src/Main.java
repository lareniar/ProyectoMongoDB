import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import db.DbConnection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // creamos una instancia
        DbConnection mongoConnection = new DbConnection();

        // nos conectamos al servidor
        MongoClient databaseConnection = mongoConnection.startConnection();

        // nos conectamos a la base de datos con la anterior conexión del servidor
        // (es posible que se requiera contraseña)
        MongoDatabase dbConnection = mongoConnection.connectToDatabase(databaseConnection, "ProyectoMongoDB");

        // con el objeto dbConnection podemos empezar a
//        for (String name : dbConnection.listCollectionNames()) {
//
//            System.out.println(name);
//        }

        MongoCollection datos = mongoConnection.getCollectionByName(dbConnection, "datos");

        // Mongo trabaja con documentos y sino con listas, en este caso al hacer
        // insertOne(), nos pide un documento. Si hicieramos insertMany(), nos
        // pediría una Lista de documentos.
//        Document dataDocument = new Document("_id", new ObjectId());
//        dataDocument.append("name", "Carlos")
//            .append("surname", "García")
//            .append("email", "c.g@gmail.com");

//        datos.insertOne(dataDocument);


        // recorrer datos
        Document data = (Document) datos.find().first();
        var list = new ArrayList<>(data.values());
        System.out.println("ID");
        System.out.println(list.get(0));
        System.out.println("Nombre");
        System.out.println(list.get(1));
        System.out.println("Apellido");
        System.out.println(list.get(2));


    }
}
