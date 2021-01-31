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
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			TransformerFactory trff= TransformerFactory.newInstance(); 
			Transformer trf = trff.newTransformer(); 
			DOMSource dom= new DOMSource(document);
		}

		catch (ParserConfigurationException p) {
			p.printStackTrace();
		}	
		catch (TransformerException t) {
			t.printStackTrace();
		}
	}

}
