package com.raizunne.redstonic.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Raizunne as a part of Redstonic
 * on 16/05/2015, 05:15 PM.
 */
public class XML {

    public static String[] getTable(String name){
        DocumentBuilderFactory bFactory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder dBuilder = bFactory.newDocumentBuilder();
            if(XML.class.getResourceAsStream("BookEntries.xml")==null){
                return new String[]{"ERROR", "SOMETHING", "GOT", "SCREWED"};
            }
            Document document = dBuilder.parse(XML.class.getResourceAsStream("BookEntries.xml"));
            document.normalize();
            Element listElement = (Element)document.getElementsByTagName("bookentries").item(0);
            NodeList nodeList = listElement.getElementsByTagName(name);
            String titlesRAW = nodeList.item(0).getTextContent();
            return titlesRAW.split(",");
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"COULD NOT FIND TABLE"};
    }

    public static String getEntry(String name) {
        DocumentBuilderFactory bFactory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder dBuilder = bFactory.newDocumentBuilder();
            if(XML.class.getResourceAsStream("BookEntries.xml")==null){
                return "COULD NOT FIND ENTRY";
            }
            Document document = dBuilder.parse(XML.class.getResourceAsStream("BookEntries.xml"));
            document.normalize();
            Element listElement = (Element)document.getElementsByTagName("bookentries").item(0);
            NodeList nodeList = listElement.getElementsByTagName("entry");
            for(int i=0; i<nodeList.getLength(); i++){
                Element entryRAW = (Element)nodeList.item(i);
//                System.out.println(entryRAW.getAttribute("name") + " : " + name);
                if(name.equals(entryRAW.getAttribute("name"))){
                    return entryRAW.getTextContent();
                }
            }
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "COULD NOT FIND ENTRY";
    }

}
