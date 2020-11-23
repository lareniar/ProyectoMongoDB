package db;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DocumentActions {
	Scanner sc = new Scanner(System.in); 
	DbConnection mongoConnection = new DbConnection();
	 
	public MongoCollection getCollection(MongoDatabase dbConnection, String collectionName) {
		// if empty creates collection
		MongoCollection collection = mongoConnection.getCollectionByName(dbConnection, collectionName);
		return collection;
		}
	
	public void insertDocument(MongoCollection collection) {
		// Mongo trabaja con documentos y sino con listas de documentos, en este caso al hacer
        // insertOne(), nos pide un documento. Si hicieramos insertMany(), nos
        // pediría una Lista de documentos.
        Document dataDocument = new Document("_id", new ObjectId());
        
        String name = sc.nextLine();
        String surname = sc.nextLine();
        String email = sc.nextLine();
        
        dataDocument.append("name", name)
            .append("surname", surname)
            .append("email", email);

        collection.insertOne(dataDocument);
	}
	
	public void printAll(MongoCollection collection) {
		   // recorrer datos
        Document data = (Document) collection.find().first();
        ArrayList list = new ArrayList<>(data.values());
        System.out.println("ID");
        System.out.println(list.get(0));
        System.out.println("Nombre");
        System.out.println(list.get(1));
        System.out.println("Apellido");
        System.out.println(list.get(2));

	}
}
