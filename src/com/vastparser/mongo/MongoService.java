package com.vastparser.mongo;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.vastparser.model.VastModel;
import org.bson.Document;

import static com.vastparser.parser.XmlParserUtils.convertToJson;
import static com.vastparser.parser.XmlParserUtils.objectMapper;

public class MongoService {

    private static final String COLLECTION_NAME = "vastData";

    /**
     * Store a VastModel object into MongoDB.
     *
     * @param vastModel The VastModel object to store.
     */
    public static void storeVastModel(VastModel vastModel) {
        MongoDatabase database = MongoConfig.getDatabase();
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        Document doc = Document.parse(convertToJson(vastModel));
        collection.insertOne(doc);
    }

    /**
     * Query a VastModel by ID from MongoDB.
     *
     * @param id The ID of the VastModel to query.
     * @return The VastModel object if found, otherwise null.
     */
    public static VastModel queryVastModelById(String id) {
        MongoDatabase database = MongoConfig.getDatabase();
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

        Document query = new Document("id", id);
        Document doc = collection.find(query).first();

        if (doc != null) {
            // Convert Document back to VastModel
            try {
                return objectMapper.readValue(doc.toJson(), VastModel.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
