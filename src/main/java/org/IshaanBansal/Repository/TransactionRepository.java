package org.IshaanBansal.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.WriteModel;
import org.IshaanBansal.Config.MongoDBConnector;
import org.IshaanBansal.POJO.Transaction;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private  final MongoCollection<Document> mongoCollection;
    public TransactionRepository(MongoDBConnector connector){
        this.mongoCollection=connector.getCollection();

    }
    public void bulkSaveTransactions(List<WriteModel<Document>> transactions){
        if(!transactions.isEmpty()){
            try{
                BulkWriteOptions ops=new BulkWriteOptions().ordered(true);
                mongoCollection.bulkWrite(transactions,ops);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    public List<Transaction> getAllTransactions(){
        List<Transaction> transactions=new ArrayList<>();
        for(Document doc:mongoCollection.find()){
            double amount=doc.getDouble("amount");
            boolean isFraudulent=doc.getBoolean("isFraudulent");
            transactions.add(new Transaction(amount,isFraudulent));
        }
        return transactions;
    }
}
