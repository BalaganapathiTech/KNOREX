package com.vastparser.storage;



import com.mongodb.client.MongoCollection;
import org.bson.Document;


public class MongoDbUtils {

    public static void insertDocument(Document document) {
        MongoCollection<Document> collection = MongoDbClient.getCollection();
        collection.insertOne(document);
    }

    public static Document findDocumentById(String id) {
        MongoCollection<Document> collection = MongoDbClient.getCollection();
        return collection.find(new Document("id", id)).first();
    }

    public static void updateDocument(String id, Document updatedData) {
        MongoCollection<Document> collection = MongoDbClient.getCollection();
        collection.updateOne(new Document("id", id), new Document("$set", updatedData));
    }

    public static void deleteDocumentById(String id) {
        MongoCollection<Document> collection = MongoDbClient.getCollection();
        collection.deleteOne(new Document("id", id));
    }

    public static void findAllDocuments() {
        MongoCollection<Document> collection = MongoDbClient.getCollection();
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }
}
