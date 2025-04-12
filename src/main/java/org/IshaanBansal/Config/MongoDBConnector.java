package org.IshaanBansal.Config;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.concurrent.TimeUnit;
// 2XFKOMC1gvCoTbMc
public class MongoDBConnector {
    private static final String URI = "mongodb+srv://Ishaan:Ishaan@cluster0.q7oqqsw.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private static final String DATABASE_NAME = "fraudDetection";
    private static final String COLLECTION_NAME = "transactions";
    private final MongoClient mongoClient;
    public MongoDBConnector(){
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(URI))
                .applyToSocketSettings(builder ->
                        builder.connectTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS))
                .build();
        mongoClient = MongoClients.create(settings);
    }
    public MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
    }
}
