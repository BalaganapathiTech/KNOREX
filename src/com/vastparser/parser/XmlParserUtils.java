package com.vastparser.parser;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class XmlParserUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts an object to its JSON representation.
     *
     * @param obj The object to convert to JSON.
     * @return The JSON string representation of the object.
     */
    public static String convertToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Utility method to validate if an XML string is well-formed.
     *
     * @param xml The XML string to validate.
     * @return true if the XML is well-formed, false otherwise.
     */
    public static boolean isValidXml(String xml) {
        try {
            javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
            builder.parse(new org.xml.sax.InputSource(new java.io.StringReader(xml)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
