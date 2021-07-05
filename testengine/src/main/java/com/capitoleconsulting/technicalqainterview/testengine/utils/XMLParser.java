package com.capitoleconsulting.technicalqainterview.testengine.utils;

import com.capitoleconsulting.technicalqainterview.testengine.models.ServiceInformation;
import io.vavr.control.Option;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XMLParser {

    public static HashMap<Class<?>, ServiceInformation> getConfigList(String pathConfig) {
        HashMap<Class<?>, ServiceInformation> serviceList = new HashMap<>();
        try{
            File fXmlFile = new File(pathConfig);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("serviceInformations");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                ServiceInformation serviceInformation = new ServiceInformation();
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nNode;
                    String callerP = element.getElementsByTagName("service_name").item(0).getTextContent();
                    serviceInformation.setServiceUrl(element.getElementsByTagName("service_url").item(0).getTextContent());
                    serviceInformation.setServicePort(Option.of(element.getElementsByTagName("service_port").item(0)).map(Node::getTextContent));
                    serviceList.put(Class.forName(callerP), serviceInformation);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return serviceList;
    }
}
