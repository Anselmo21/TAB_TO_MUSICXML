package TAB_TO_XML;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Drums {

	public static void convertToXML (ArrayList<Character> components) {

		try {
			//This creates a Dom object trees that will store elements of an XML documents
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance(); 
			//an instance of class obtained from the DocumentBuilderFactory.newDocumentBuilder() method. XML can be parsed from a variety of input sources
			//This method of writing XML works like a tree, there are top elements that are parents of child elements.
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			//root element (top parent)
			Document document = documentBuilder.newDocument();
			
			Element scorePW = document.createElement("score-partwise");
			Attr scorePW_att = document.createAttribute("version");
			scorePW_att.setValue("3.1");
			scorePW.setAttributeNode(scorePW_att);
			document.appendChild(scorePW);
			
			Element partList = document.createElement("part-list");
			scorePW.appendChild(partList);

			Element scorePart = document.createElement("part-list");
			Attr scorePart_att = document.createAttribute("id");
			scorePart_att.setValue("P1");
			scorePW.setAttributeNode(scorePart_att);
			partList.appendChild(scorePart);
			
			Element partName = document.createElement("part-name");
			partName.appendChild(document.createTextNode("Music"));
			scorePart.appendChild(partName);
			
			Element part = document.createElement("part");
			Attr part_att = document.createAttribute("id");
			part_att.setValue("P1");
			part.setAttributeNode(part_att);
			scorePW.appendChild(part);
			
			Element measure = document.createElement("measure");
			Attr measure_att = document.createAttribute("number");
			measure_att.setValue("1");
			measure.setAttributeNode(measure_att);
			part.appendChild(measure);
			
			Element attributes = document.createElement("attributes");
			measure.appendChild(attributes);


			//Used to create Transformer objects.
			TransformerFactory trff= TransformerFactory.newInstance();
			//A class that can transform a source tree into a result tree.
			Transformer trf = trff.newTransformer();   
			//Document Object Model (DOM) tree that acts as a holder for a transformation source tree.
			DOMSource dom= new DOMSource(document);    
			//This creates a XML file that is being translated from the DOMSource
			StreamResult streamresult = new StreamResult(new File("../TAB_TO_XML/src/main/java/"));    
			
			//This translates your XML source to a result (in this case, everything under document to StreamResult)
			trf.transform(dom, streamresult);   
			trf.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//Recordare//DTD MusicXML 3.1 Partwise//EN");
			trf.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.musicxml.org/dtds/partwise.dtd");
		}

		catch (ParserConfigurationException p) {
			p.printStackTrace();
		}	
		catch (TransformerException t) {
			t.printStackTrace();
		}
	}

}
