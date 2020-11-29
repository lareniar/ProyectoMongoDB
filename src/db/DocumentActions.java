package db;

import java.util.ArrayList;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

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
        // pedirÃ­a una Lista de documentos.
        Document dataDocument = new Document("_id", new ObjectId());
        
        System.out.println("Nombre");
        String name = sc.nextLine();
        System.out.println("Apellido");
        String surname = sc.nextLine();
        System.out.println("Email");
        String email = sc.nextLine();
        
        dataDocument.append("name", name)
            .append("surname", surname)
            .append("email", email);

        collection.insertOne(dataDocument);
	}
	
	public void printAll(MongoCollection collection) {
        Document data=new Document();
        MongoCursor<Document> cursor = collection.find(Filters.eq("departamentos_id",new ObjectId("5fbcdb644b5f8fe039714e8d"))).iterator();

        try {
            while (cursor.hasNext()) {
            	Document obj = cursor.next();//guardamos en un documento el elemento que encuentre
                System.out.println(obj.get("nombre") + " " + obj.get("departamentos_id"));//imprimimos el key del elemento que queramos

            	JSONObject json= new JSONObject();
            	//json.put(cursor.next().toJson());
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }

	}
	

	public void updateField(MongoDatabase dbConnection) {
		 //update
        //UPDATE UN CAMPO CON EL WHERE DE OTRO CAMPO.
        BasicDBObject query = new BasicDBObject();
        System.out.println("Busqueda de fila");
        System.out.println("Columna");
        String columna = sc.nextLine();
        System.out.println("Referencia de la columna");
        String referencia = sc.nextLine();
        query.put(columna, referencia); // indicamos el campo que debe localizar para saber que row tiene que modificar
        
        //hacemos el update y set
        BasicDBObject updateQuery = new BasicDBObject();
        
        System.out.println("Columna");
        columna = sc.nextLine();
        System.out.println("Contenido");
        String contenido = sc.nextLine();
        updateQuery.append("$set",
        new BasicDBObject().append(columna, contenido));//el campo que queremos modificar y el nuevo valor
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
	}
	
	public void deleteField(MongoCollection collectionName ) {
		//delete
		System.out.println("Email");
        String email = sc.nextLine();
		collectionName.deleteOne(new Document("email", new ObjectId(email)));
	}
	
	public void insertCodecRegistry(MongoDatabase dbCodec) {
		
		Document documento = new Document()
		         .append("name", "Maria")
		         .append("surname", "Gutierrez")
		         .append("email", "m@maria.com");
		dbCodec.getCollection("datos").insertOne(documento);
	}
}
