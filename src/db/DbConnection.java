package db;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import java.util.Arrays;
public class DbConnection {
/*
    // instancia sin parametros
    MongoClient mongoClient = new MongoClient();

    // con ruta del host, por defecto puerto 27017
    MongoClient mongoClient = new MongoClient( "localhost" );

    // con ruta del host y puerto especificado
    MongoClient mongoClient = new MongoClient( "host1" , 27017 );

    // mediante uri
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://host1:27017"));

    // lista de servidores
    MongoClient mongoClient = new MongoClient(
            Arrays.asList(new ServerAddress("host1", 27017),
                    new ServerAddress("host2", 27017),
                    new ServerAddress("host3", 27017)));

    // con seguridad
    String user; // the user name
    String database; // the name of the database in which the user is defined
    char[] password; // the password as a character array
    // ...

    MongoCredential credential = MongoCredential.createCredential(user, database, password);
    MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
    MongoClient mongoClient = new MongoClient(new ServerAddress("host1", 27017),
            Arrays.asList(credential),
            options);
*/

    public MongoClient startConnection(){
        MongoClient databaseConnection = new MongoClient( "localhost" );

        return databaseConnection;
    }

    public MongoDatabase connectToDatabase(MongoClient databaseConnection, String databaseName){

        return databaseConnection.getDatabase(databaseName);
    }

    public MongoCollection getCollectionByName(MongoDatabase dbConnection, String collectionName){
        return dbConnection.getCollection(collectionName);
    }


}
