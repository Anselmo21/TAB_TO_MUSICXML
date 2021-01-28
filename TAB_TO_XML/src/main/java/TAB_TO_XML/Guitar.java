package TAB_TO_XML;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Guitar {

	public static void convert(ArrayList<Character> a) {
		
		//https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
		//This is an example code? That's there to create a xml file that looks like what's on the page above.
		
		try {
			
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		//This creates a Dom object trees that will store elements of an XML documents
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		//an instance of class obtained from the DocumentBuilderFactory.newDocumentBuilder() method. XML can be parsed from a variety of input sources
		//This method of writing XML works like a tree, there are top elements that are parents of child elements.
		Document document = documentBuilder.newDocument();
		//root element (top parent)
		Element scoreP = document.createElement("score-partwise");
		//child element of root
		document.appendChild(scoreP);
		//Sets hierarchy between child element and parent element
		Attr scorePatt = document.createAttribute("version");
		scorePatt.setValue("3.1");
		scoreP.setAttributeNode(scorePatt);
		//Creates an attribute for an element and sets the value of that attribute
		Element partL = document.createElement("part-list");
		scoreP.appendChild(partL);
		//child element of scoreP (This should explain enough on how the hierarchy on DOM works)
		Element scorepa = document.createElement("score-part");
		partL.appendChild(scorepa);
		
		Attr scorepaatt = document.createAttribute("id");
		scorepaatt.setValue("P1");
		scorepa.setAttributeNode(scorepaatt);
		
		Element partn = document.createElement("part-name");
		partn.appendChild(document.createTextNode("Music")); //Creates an text for the element
		scorepa.appendChild(partn);
		
		Element part2 = document.createElement("part");
		scoreP.appendChild(part2);
		
		Attr part2att = document.createAttribute("id");
		part2att.setValue("P1");
		part2.setAttributeNode(part2att);

		Element meas = document.createElement("measure");
		part2.appendChild(meas);
		
		Attr measatt = document.createAttribute("number");
		measatt.setValue("1");
		meas.setAttributeNode(measatt);
		
		Element attr = document.createElement("attributes");
		meas.appendChild(attr);
		
		Element divis = document.createElement("divisions");
		divis.appendChild(document.createTextNode("1"));
		attr.appendChild(divis);
		
		Element key = document.createElement("key");
		attr.appendChild(key);
		
		Element fifth = document.createElement("fifths");
		fifth.appendChild(document.createTextNode("0"));
		key.appendChild(fifth);
		
		Element time = document.createElement("time");
		attr.appendChild(time);
		
		Element beat = document.createElement("beats");
		beat.appendChild(document.createTextNode("4"));
		time.appendChild(beat);
		
		Element beatt = document.createElement("beat-type");
		beatt.appendChild(document.createTextNode("4"));
		time.appendChild(beatt);
		
		Element cle = document.createElement("clef");
		attr.appendChild(cle);
		
		Element sig = document.createElement("sign");
		sig.appendChild(document.createTextNode("G"));
		cle.appendChild(sig);
		
		Element lin = document.createElement("line");
		lin.appendChild(document.createTextNode("2"));
		cle.appendChild(lin);
		
		Element not = document.createElement("note");
		meas.appendChild(not);
		
		Element pit = document.createElement("pitch");
		not.appendChild(pit);
		
		Element ste = document.createElement("step");
		ste.appendChild(document.createTextNode("C"));
		pit.appendChild(ste);
		
		Element oct = document.createElement("octave");
		oct.appendChild(document.createTextNode("4"));
		pit.appendChild(oct);
		
		Element dur = document.createElement("duration");
		dur.appendChild(document.createTextNode("4"));
		not.appendChild(dur);
		
		Element typ = document.createElement("type");
		typ.appendChild(document.createTextNode("whole"));
		not.appendChild(typ);
		

		TransformerFactory trff= TransformerFactory.newInstance(); //Used to create Transformer objects.
		Transformer trf = trff.newTransformer(); //A class that can transform a source tree into a result tree.
		DOMSource dom= new DOMSource(document); //Document Object Model (DOM) tree that acts as a holder for a transformation source tree.
		StreamResult stm = new StreamResult(new File("C:\\Users\\xmlfile.xml")); //This creates a XML file that is being translated from the DOMSource


		trf.transform(dom, stm); //This translates your XML source to a result (in this case, everything under document to StreamResult)
		}	catch (ParserConfigurationException p) {
			p.printStackTrace();
		}	catch (TransformerException t) {
			t.printStackTrace();
		}
		
	}
}
