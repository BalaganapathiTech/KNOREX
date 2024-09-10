package com.vastparser.parser;



import com.vastparser.model.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.StringReader;
import java.io.IOException;

import static com.vastparser.parser.XmlParserUtils.objectMapper;

public class XmlParser {

    public static VastModel parseXml(String xmlContent) {
        VastModel vastModel = new VastModel();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlContent)));

            document.getDocumentElement().normalize();

            Node vastNode = document.getDocumentElement();
            vastModel.setVersion(vastNode.getAttributes().getNamedItem("version").getNodeValue());

            NodeList adNodes = document.getElementsByTagName("Ad");
            if (adNodes.getLength() > 0) {
                Node adNode = adNodes.item(0);
                vastModel.setId(adNode.getAttributes().getNamedItem("id").getNodeValue());

                NodeList inLineNodes = ((org.w3c.dom.Element) adNode).getElementsByTagName("InLine");
                if (inLineNodes.getLength() > 0) {
                    Node inLineNode = inLineNodes.item(0);
                    NodeList childNodes = inLineNode.getChildNodes();

                    for (int i = 0; i < childNodes.getLength(); i++) {
                        Node childNode = childNodes.item(i);

                        switch (childNode.getNodeName()) {
                            case "AdTitle":
                                vastModel.setTitle(childNode.getTextContent().trim());
                                break;
                            case "Description":
                                vastModel.setDescription(childNode.getTextContent().trim());
                                break;
                            case "Impression":
                                vastModel.setImpression(new Impression(
                                        childNode.getAttributes().getNamedItem("id").getNodeValue(),
                                        childNode.getTextContent().trim()
                                ));
                                break;
                            case "Creatives":
                                NodeList creativeNodes = ((org.w3c.dom.Element) childNode).getElementsByTagName("Creative");
                                for (int j = 0; j < creativeNodes.getLength(); j++) {
                                    Node creativeNode = creativeNodes.item(j);
                                    Creative creative = new Creative();
                                    creative.setId(creativeNode.getAttributes().getNamedItem("id").getNodeValue());

                                    NodeList companionAdsNodes = ((org.w3c.dom.Element) creativeNode).getElementsByTagName("CompanionAds");
                                    for (int k = 0; k < companionAdsNodes.getLength(); k++) {
                                        Node companionAdsNode = companionAdsNodes.item(k);
                                        NodeList companionNodes = ((org.w3c.dom.Element) companionAdsNode).getElementsByTagName("Companion");
                                        for (int l = 0; l < companionNodes.getLength(); l++) {
                                            Node companionNode = companionNodes.item(l);
                                            CompanionBanner companionBanner = new CompanionBanner();
                                            companionBanner.setId(companionNode.getAttributes().getNamedItem("id").getNodeValue());
                                            companionBanner.setWidth(Integer.parseInt(companionNode.getAttributes().getNamedItem("width").getNodeValue()));
                                            companionBanner.setHeight(Integer.parseInt(companionNode.getAttributes().getNamedItem("height").getNodeValue()));

                                            NodeList staticResourceNodes = ((org.w3c.dom.Element) companionNode).getElementsByTagName("StaticResource");
                                            if (staticResourceNodes.getLength() > 0) {
                                                Node staticResourceNode = staticResourceNodes.item(0);
                                                companionBanner.setType(staticResourceNode.getAttributes().getNamedItem("creativeType").getNodeValue());
                                                companionBanner.setSource(staticResourceNode.getTextContent().trim());
                                            }

                                            NodeList clickThroughNodes = ((org.w3c.dom.Element) companionNode).getElementsByTagName("CompanionClickThrough");
                                            if (clickThroughNodes.getLength() > 0) {
                                                companionBanner.setClickThroughUrl(clickThroughNodes.item(0).getTextContent().trim());
                                            }

                                            creative.getCompanionBanners().add(companionBanner);
                                        }
                                    }

                                    NodeList linearNodes = ((org.w3c.dom.Element) creativeNode).getElementsByTagName("Linear");
                                    if (linearNodes.getLength() > 0) {
                                        Node linearNode = linearNodes.item(0);
                                        Linear linear = new Linear();
                                        NodeList durationNodes = ((org.w3c.dom.Element) linearNode).getElementsByTagName("Duration");
                                        if (durationNodes.getLength() > 0) {
                                            linear.setDuration(durationNodes.item(0).getTextContent().trim());
                                        }

                                        NodeList trackingEventNodes = ((org.w3c.dom.Element) linearNode).getElementsByTagName("Tracking");
                                        for (int m = 0; m < trackingEventNodes.getLength(); m++) {
                                            Node trackingEventNode = trackingEventNodes.item(m);
                                            TrackingEvent trackingEvent = new TrackingEvent();
                                            trackingEvent.setType(trackingEventNode.getAttributes().getNamedItem("event").getNodeValue());
                                            trackingEvent.setUrl(trackingEventNode.getTextContent().trim());
                                            linear.getTrackingEvents().add(trackingEvent);
                                        }

                                        NodeList videoClickNodes = ((org.w3c.dom.Element) linearNode).getElementsByTagName("ClickTracking");
                                        for (int m = 0; m < videoClickNodes.getLength(); m++) {
                                            Node videoClickNode = videoClickNodes.item(m);
                                            VideoClick videoClick = new VideoClick();
                                            videoClick.setId(videoClickNode.getAttributes().getNamedItem("id").getNodeValue());
                                            videoClick.setUrl(videoClickNode.getTextContent().trim());
                                            linear.getVideoClicks().add(videoClick);
                                        }

                                        NodeList mediaFileNodes = ((org.w3c.dom.Element) linearNode).getElementsByTagName("MediaFile");
                                        for (int m = 0; m < mediaFileNodes.getLength(); m++) {
                                            Node mediaFileNode = mediaFileNodes.item(m);
                                            MediaFile mediaFile = new MediaFile();
                                            mediaFile.setType(mediaFileNode.getAttributes().getNamedItem("type").getNodeValue());
                                            mediaFile.setBitrate(Integer.parseInt(mediaFileNode.getAttributes().getNamedItem("bitrate").getNodeValue()));
                                            mediaFile.setWidth(Integer.parseInt(mediaFileNode.getAttributes().getNamedItem("width").getNodeValue()));
                                            mediaFile.setHeight(Integer.parseInt(mediaFileNode.getAttributes().getNamedItem("height").getNodeValue()));
                                            mediaFile.setSource(mediaFileNode.getTextContent().trim());
                                            linear.getMediaFiles().add(mediaFile);
                                        }
                                        creative.setLinear(linear);
                                    }

                                    vastModel.getCreatives().add(creative);
                                }
                                break;
                            case "Extensions":
                                NodeList extensionNodes = ((org.w3c.dom.Element) childNode).getElementsByTagName("Extension");
                                for (int m = 0; m < extensionNodes.getLength(); m++) {
                                    Node extensionNode = extensionNodes.item(m);
                                    Extension extension = new Extension();
                                    extension.setType(extensionNode.getAttributes().getNamedItem("type").getNodeValue());
                                    NodeList totalAvailableNodes = ((org.w3c.dom.Element) extensionNode).getElementsByTagName("total_available");
                                    if (totalAvailableNodes.getLength() > 0) {
                                        extension.setTotalAvailable(totalAvailableNodes.item(0).getTextContent().trim());
                                    }
                                    vastModel.getExtensions().add(extension);
                                }
                                break;
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return vastModel;
    }

    public static String convertToJson(VastModel vastModel) {
        try {
            return objectMapper.writeValueAsString(vastModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
