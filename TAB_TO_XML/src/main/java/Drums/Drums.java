package Drums;

import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
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
			
			Element ident = document.createElement("identification");
			scoreP.appendChild(ident);
			
			Element enco = document.createElement("encoding");
			ident.appendChild(enco);
			
			Element soft = document.createElement("software");
			soft.appendChild(document.createTextNode("Finale v25 for Windows"));
			enco.appendChild(soft);
			
			Element encod = document.createElement("encoding-date");
			LocalDateTime dano = LocalDateTime.now();
			encod.appendChild(document.createTextNode(dano.toString()));
			encod.appendChild(encod);
			
			Element defa = document.createElement("defaults");
			scoreP.appendChild(defa);
			
			Element scal = document.createElement("scaling");
			defa.appendChild(scal);
			
			Element mill = document.createElement("millimeters");
			mill.appendChild(document.createTextNode("6.35"));
			scal.appendChild(mill);
			
			Element tent = document.createElement("tenths");
			tent.appendChild(document.createTextNode("40"));
			scal.appendChild(tent);
			
			Element pala = document.createElement("page-layout");
			defa.appendChild(pala);
			
			Element pahe = document.createElement("page-height");
			pahe.appendChild(document.createTextNode("1760"));
			pala.appendChild(pahe);
			
			Element pawi = document.createElement("page-width");
			pawi.appendChild(document.createTextNode("1360"));
			pala.appendChild(pawi);
			
			Element pama = document.createElement("page-margins");
			pala.appendChild(pama);
			
			Attr pamaty = document.createAttribute("type");
			pamaty.setValue("both");
			pama.setAttributeNode(pamaty);
			
			Element lema = document.createElement("left-margin");
			lema.appendChild(document.createTextNode("80"));
			pama.appendChild(lema);
			
			Element rima = document.createElement("right-margin");
			rima.appendChild(document.createTextNode("80"));
			pama.appendChild(rima);
			
			Element toma = document.createElement("top-margin");
			toma.appendChild(document.createTextNode("80"));
			pama.appendChild(toma);
			
			Element boma = document.createElement("bottom-margin");
			boma.appendChild(document.createTextNode("80"));
			pama.appendChild(boma);
			
			Element syla = document.createElement("system-layout");
			defa.appendChild(syla);
			
			Element syma = document.createElement("system-margins");
			syla.appendChild(syma);
			
			Element lema2 = document.createElement("left-margin");
			lema2.appendChild(document.createTextNode("0"));
			syma.appendChild(lema2);
			
			Element rima2 = document.createElement("right-margin");
			rima2.appendChild(document.createTextNode("0"));
			syma.appendChild(rima2);
			
			Element sydi = document.createElement("system-distance");
			sydi.appendChild(document.createTextNode("173"));
			syla.appendChild(sydi);
			
			Element tsyd = document.createElement("top-system-distance");
			tsyd.appendChild(document.createTextNode("68"));
			syla.appendChild(tsyd);
			
			Element stla = document.createElement("staff-layout");
			stla.appendChild(document.createTextNode("67"));
			defa.appendChild(stla);
			
			Element appe = document.createElement("appearance");
			scoreP.appendChild(appe);
			
			Element liwityst = document.createElement("line-width");
			liwityst.appendChild(document.createTextNode("0.8333"));
			appe.appendChild(liwityst);
			
			Attr tyst = document.createAttribute("type");
			tyst.setValue("steam");
			liwityst.setAttributeNode(tyst);
			
			Element liwitybe = document.createElement("line-width");
			liwitybe.appendChild(document.createTextNode("5"));
			appe.appendChild(liwitybe);
			
			Attr tyst1 = document.createAttribute("type");
			tyst1.setValue("beam");
			liwitybe.setAttributeNode(tyst1);
			
			Element liwitysta = document.createElement("line-width");
			liwitysta.appendChild(document.createTextNode("1.25"));
			appe.appendChild(liwitysta);
			
			Attr tyst2 = document.createAttribute("type");
			tyst2.setValue("staff");
			liwitysta.setAttributeNode(tyst2);
			
			Element liwitylb = document.createElement("line-width");
			liwitylb.appendChild(document.createTextNode("1.4583"));
			appe.appendChild(liwitylb);
			
			Attr tyst3 = document.createAttribute("type");
			tyst3.setValue("light barline");
			liwitylb.setAttributeNode(tyst3);
			
			Element liwityhb = document.createElement("line-width");
			liwityhb.appendChild(document.createTextNode("5"));
			appe.appendChild(liwityhb);
			
			Attr tyst4 = document.createAttribute("type");
			tyst4.setValue("heavy barline");
			liwityhb.setAttributeNode(tyst4);
			
			Element liwitylg = document.createElement("line-width");
			liwitylg.appendChild(document.createTextNode("1.875"));
			appe.appendChild(liwitylg);
			
			//
			Attr tyst5 = document.createAttribute("type");
			tyst5.setValue("leger");
			liwitylg.setAttributeNode(tyst5);
			
			Element liwityec = document.createElement("line-width");
			liwityec.appendChild(document.createTextNode("1.4583"));
			appe.appendChild(liwityec);
			
			Attr tyst6 = document.createAttribute("type");
			tyst6.setValue("encoding");
			liwityec.setAttributeNode(tyst6);
			
			Element liwitywg = document.createElement("line-width");
			liwitywg.appendChild(document.createTextNode("0.9375"));
			appe.appendChild(liwitywg);
			
			Attr tyst7 = document.createAttribute("type");
			tyst7.setValue("wedge");
			liwitywg.setAttributeNode(tyst7);
			
			Element liwityenc = document.createElement("line-width");
			liwityenc.appendChild(document.createTextNode("1.4583"));
			appe.appendChild(liwityenc);
			
			Attr tyst8 = document.createAttribute("type");
			tyst8.setValue("enclosure");
			liwityst.setAttributeNode(tyst8);
			
			Element liwitytb = document.createElement("line-width");
			liwitytb.appendChild(document.createTextNode("1.4583"));
			appe.appendChild(liwitytb);
			
			Attr tyst9 = document.createAttribute("type");
			tyst9.setValue("tuplet bracket");
			liwitytb.setAttributeNode(tyst9);
			
			Element nowitygr = document.createElement("note-width");
			nowitygr.appendChild(document.createTextNode("50"));
			appe.appendChild(nowitygr);
			
			Attr tyst10 = document.createAttribute("type");
			tyst10.setValue("grace");
			nowitygr.setAttributeNode(tyst10);
			
			Element nowitycu = document.createElement("line-width");
			nowitycu.appendChild(document.createTextNode("50"));
			appe.appendChild(nowitycu);
			
			Attr tyst11 = document.createAttribute("type");
			tyst11.setValue("cue");
			nowitycu.setAttributeNode(tyst11);
			
			Element dityhy = document.createElement("line-width");
			dityhy.appendChild(document.createTextNode("60"));
			appe.appendChild(dityhy);
			
			Attr tyst12 = document.createAttribute("type");
			tyst12.setValue("hyphen");
			dityhy.setAttributeNode(tyst12);
			
			Element ditybe = document.createElement("line-width");
			ditybe.appendChild(document.createTextNode("8"));
			appe.appendChild(ditybe);
			
			Attr tyst13 = document.createAttribute("type");
			tyst13.setValue("beam");
			ditybe.setAttributeNode(tyst13);
			
			Element mufo = document.createElement("music-font");
			defa.appendChild(mufo);
			
			Attr fofa = document.createAttribute("font-family");
			fofa.setValue("Maestro,engraved");
			mufo.setAttributeNode(fofa);
			
			Attr fosi = document.createAttribute("font-size");
			fosi.setValue("18");
			mufo.setAttributeNode(fosi);
			
			Element wofo = document.createElement("word-font");
			defa.appendChild(wofo);
			
			Attr fofa1 = document.createAttribute("font-family");
			fofa1.setValue("Times New Roman");
			wofo.setAttributeNode(fofa1);
			
			Attr fosi1 = document.createAttribute("font-size");
			fosi1.setValue("9");
			wofo.setAttributeNode(fosi1);
			
			Element partL = document.createElement("part-list");
			scoreP.appendChild(partL);
			//child element of scoreP (This should explain enough on how the hierarchy on DOM works)
			Element scorepa = document.createElement("score-part");
			partL.appendChild(scorepa);
			
			Attr scorepaatt = document.createAttribute("id");
			scorepaatt.setValue("P1");
			scorepa.setAttributeNode(scorepaatt);
			
			Element partn = document.createElement("part-name");
			partn.appendChild(document.createTextNode("Drum")); //Creates an text for the element
			scorepa.appendChild(partn);
			
			Attr pro = document.createAttribute("print-object");
			pro.setValue("no");
			partn.setAttributeNode(pro);
			
			Element partab = document.createElement("part-abbreviation");
			partab.appendChild(document.createTextNode("Drum.")); //Creates an text for the element
			scorepa.appendChild(partab);
			
			Attr pro1 = document.createAttribute("print-object");
			pro1.setValue("no");
			partab.setAttributeNode(pro1);
			
			Element scoin = document.createElement("score-instrument");
			scorepa.appendChild(scoin);
			
			Attr scoinid = document.createAttribute("id");
			scoinid.setValue("P1-I1");
			scoin.setAttributeNode(scoinid);
			
			Element instname = document.createElement("instrument-name");
			instname.appendChild(document.createTextNode("Drum"));
			scoin.appendChild(instname);
			
			Element instsound = document.createElement("instrument-sound");
			instsound.appendChild(document.createTextNode("Drum"));
			scoin.appendChild(instsound);
			
			Element miinst = document.createElement("midi-instrument");
			scorepa.appendChild(miinst);
			
			Attr miinstid = document.createAttribute("id");
			miinstid.setValue("P1-I1");
			miinst.setAttributeNode(miinstid);
			
			Element mich = document.createElement("midi-channel");
			mich.appendChild(document.createTextNode("1"));
			miinst.appendChild(mich);
			
			Element mipr = document.createElement("midi-program");
			mipr.appendChild(document.createTextNode("26"));
			miinst.appendChild(mipr);
			
			Element volu = document.createElement("volume");
			volu.appendChild(document.createTextNode("80"));
			miinst.appendChild(volu);
			
			Element pan = document.createElement("pan");
			pan.appendChild(document.createTextNode("0"));
			miinst.appendChild(pan);
			
			Element scorepa1 = document.createElement("score-part");
			partL.appendChild(scorepa1);
			
			Attr scorepa1att = document.createAttribute("id");
			scorepa1att.setValue("P2");
			scorepa1.setAttributeNode(scorepa1att);
			
			Element partn1 = document.createElement("part-name");
			partn1.appendChild(document.createTextNode("Drum [TAB]"));
			scorepa1.appendChild(partn1);
			
			Attr partn1att = document.createAttribute("print-object");
			partn1att.setValue("no");
			partn1.setAttributeNode(partn1att);
			
			Element partab1 = document.createElement("part-abbreviation");
			partab1.appendChild(document.createTextNode("Gtr."));
			scorepa1.appendChild(partab1);
			
			Attr partab1att = document.createAttribute("print-object");
			partab1att.setValue("no");
			partab1.setAttributeNode(partab1att);


			//Used to create Transformer objects.
			TransformerFactory trff= TransformerFactory.newInstance();
			//A class that can transform a source tree into a result tree.
			Transformer trf = trff.newTransformer();   
			//Document Object Model (DOM) tree that acts as a holder for a transformation source tree.
			DOMSource dom= new DOMSource(document);    
			//This creates a XML file that is being translated from the DOMSource
			StreamResult streamresult = new StreamResult(new File("../TAB_TO_XML/src/main/java/Drum_Tab.musicxml"));    
			
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
