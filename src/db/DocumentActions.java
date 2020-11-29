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
		 String department_id = null;
		 
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
        
        System.out.println("Elige el departamento, escribe 1 para Tecnologia o 2 para informatica.");
        String id = sc.nextLine();
        if(id.equals("1")) {
        	department_id = "5fbcdb1f4b5f8fe039714e8b";
        }else {
        	department_id = "5fbcdb644b5f8fe039714e8d";
        }
        

		dataDocument.append("nombre", name)
            .append("apellido", surname)
            .append("email", email)
            .append("departamentos_id", new ObjectId(department_id ));

        collection.insertOne(dataDocument);
	}
	
	public void printAll(MongoCollection collection) {
        Document data=new Document();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
            	Document obj = cursor.next();//guardamos en un documento el elemento que encuentre
                System.out.println(obj.get("nombre") + " " + obj.get("apellido") + " " + obj.get("email") + " " + obj.get("departamentos_id") );//imprimimos el key del elemento que queramos

            	JSONObject json= new JSONObject();
            	//json.put(cursor.next().toJson());
                //System.out.println(cursor.next().toJson());
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
        System.out.println("Campo (nombre, apellido o email)");
        String columna = sc.nextLine();
        System.out.println("Referencia de la columna (Contenido por el que buscar)");
        String referencia = sc.nextLine();
        query.put(columna, referencia); // indicamos el campo que debe localizar para saber que row tiene que modificar
        
        //hacemos el update y set
        BasicDBObject updateQuery = new BasicDBObject();
        
        System.out.println("Campo a modificar(nombre, apellido o email)");
        columna = sc.nextLine();
        System.out.println("Nuevo contenido del campo");
        String contenido = sc.nextLine();
        updateQuery.append("$set",
        new BasicDBObject().append(columna, contenido));//el campo que queremos modificar y el nuevo valor
        dbConnection.getCollection("trabajadores").updateMany(query, updateQuery);
        
        
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
		System.out.println("Nombre");
        String name = sc.nextLine();
		collectionName.deleteOne(new Document("nombre", name));
	}
	
	public void insertCodecRegistry(MongoDatabase dbCodec) {
			String department_id = null;
	        
	        System.out.println("Nombre");
	        String name = sc.nextLine();
	        System.out.println("Apellido");
	        String surname = sc.nextLine();
	        System.out.println("Email");
	        String email = sc.nextLine();
	        
	        System.out.println("Elige el departamento, escribe 1 para Tecnologia o 2 para informatica.");
	        String id = sc.nextLine();
	        if(id.equals("1")) {
	        	department_id = "5fbcdb1f4b5f8fe039714e8b";
	        }else {
	        	department_id = "5fbcdb644b5f8fe039714e8d";
	        }
	        
		Document documento = new Document()
		         .append("nombre", name)
		         .append("apellido", surname)
		         .append("email", email)
	             .append("departamentos_id", new ObjectId(department_id));
		dbCodec.getCollection("trabajadores").insertOne(documento);
	}
}
