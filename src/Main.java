import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import db.DbConnection;
import db.DocumentActions;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	 View view = new View();
    	  
        // creamos una instancia
        DbConnection mongoConnection = new DbConnection();
        // nos conectamos al servidor
        MongoClient databaseConnection = mongoConnection.startConnection();
        // nos conectamos a la base de datos con la anterior conexiÃ³n del servidor
        // (es posible que se requiera contraseÃ±a)
        MongoDatabase dbConnection = mongoConnection.connectToDatabase(databaseConnection, "ProyectoMongoDB");
   
        /* CODEC REGISTRY */
        
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry());
		MongoClient cliente = new MongoClient("localhost", 
		    MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		MongoDatabase dbCodec = cliente.getDatabase("ProyectoMongoDB");
		/*/CODEC REGISTRY*/
		
        DocumentActions documentActions = new DocumentActions();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de la colección a manipular: ");
        String collectionName = sc.nextLine();
        // devuelve la colección para poder manipularla y hacer CRUD
        MongoCollection collection = documentActions.getCollection(dbConnection, collectionName);

        boolean exit = false;
		while(!exit){
            String choiceValue = view.returnChoiceValue();
            switch (choiceValue){
                case "1":
                	documentActions.insertDocument(collection);
                    break;
                    
                case "2":
                	documentActions.printAll(collection);
                    break;
                case "3":
                	documentActions.updateField(dbConnection);
                    break;

                case "4":
                	documentActions.deleteField(collection);
                	break;
            	case "5":
                	documentActions.insertCodecRegistry(dbCodec);
                	break;
                case "0":
                    exit = true;
                    break;
            }
        }
        

        
        
        
        
        

    }
}
