import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import db.DbConnection;
import db.DocumentActions;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // creamos una instancia
        DbConnection mongoConnection = new DbConnection();

        // nos conectamos al servidor
        MongoClient databaseConnection = mongoConnection.startConnection();

        // nos conectamos a la base de datos con la anterior conexiÃ³n del servidor
        // (es posible que se requiera contraseÃ±a)
        MongoDatabase dbConnection = mongoConnection.connectToDatabase(databaseConnection, "ProyectoMongoDB");

        // con el objeto dbConnection podemos empezar a
//        for (String name : dbConnection.listCollectionNames()) {
//
//            System.out.println(name);
//        }

        DocumentActions documentActions = new DocumentActions();
        Scanner sc = new Scanner(System.in);
        String collectionName = sc.nextLine();
        // devuelve la colección para poder manipularla y hacer CRUD
        MongoCollection collection = documentActions.getCollection(dbConnection, collectionName);
        
        
        documentActions.insertDocument(collection);


        documentActions.printAll(collection);
     
        //update
        //UPDATE UN CAMPO CON EL WHERE DE OTRO CAMPO.
        BasicDBObject query = new BasicDBObject();
        query.put("surname", "García"); // indicamos el campo que debe localizar para saber que row tiene que modificar
        //hacemos el update y set
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",
        new BasicDBObject().append("name", "Carlos"));//el campo que queremos modificar y el nuevo valor
        dbConnection.getCollection("datos").updateMany(query, updateQuery);
        
       /*
        * UPDATE UN CAMPO CONCRETO *
        BasicDBObject query = new BasicDBObject();
        query.put("name", "García"); // indicamos el campo que vamos a modificar en el documento y el valor actual 
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", "Leire"); //asignamos el nuevo valor al campo seleccionado
       
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument); // hacemos el set 
       
        dbConnection.getCollection("datos").updateOne(query, updateObject); //hacemos el update pasando la query y el set del nuevo objeto
       */
        
        
        
        
        //delete
        //datos.deleteOne(new Document("_id", new ObjectId("5fb3a109c6fab0330860020a")));

    }
}
