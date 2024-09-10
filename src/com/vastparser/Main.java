package com.vastparser;

import com.vastparser.model.VastModel;
import com.vastparser.mongo.MongoConfig;
import com.vastparser.parser.FileUtil;
import com.vastparser.parser.XmlParser;
import com.vastparser.mongo.MongoService;


public class Main {

    public static void main(String[] args) {
        try {
            String xmlContent = FileUtil.readXmlFromFile("path/to/vast-sample.xml");

            VastModel vastModel = XmlParser.parseXml(xmlContent);

            String jsonOutput = XmlParser.convertToJson(vastModel);
            System.out.println("JSON Output:");
            System.out.println(jsonOutput);

            MongoService.storeVastModel(vastModel);

            String idToQuery = "20004";
            VastModel queriedModel = MongoService.queryVastModelById(idToQuery);

            System.out.println("Queried Model:");
            System.out.println(XmlParser.convertToJson(queriedModel));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MongoConfig.close();
        }
    }
}
