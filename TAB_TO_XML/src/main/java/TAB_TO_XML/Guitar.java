package TAB_TO_XML;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

public class Guitar {

	public static void Convert(ArrayList<String> fileContent) {

		// From here down to a line of comment lines are the patterns used for the
		// algorithm on automatic generation of required assets of xml file
		// It's useful to make a reference to this on the formatting
		String titledelim = " by ";
		String divisioncount = "-|-";
		//
		try {
			// Codes below here down to line of // are fixed variables
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			// This creates a Dom object trees that will store elements of an XML documents
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			// an instance of class obtained from the
			// DocumentBuilderFactory.newDocumentBuilder() method. XML can be parsed from a
			// variety of input sources
			// This method of writing XML works like a tree, there are top elements that are
			// parents of child elements.
			Document document = documentBuilder.newDocument();
			// root element (top parent)
			Element scoreP = document.createElement("score-partwise");
			// child element of root
			document.appendChild(scoreP);
			// Sets hierarchy between child element and parent element
			Attr scorePatt = document.createAttribute("version");
			scorePatt.setValue("3.1");
			scoreP.setAttributeNode(scorePatt);
			// Creates an attribute for an element and sets the value of that attribute

			Element ident = document.createElement("identification");
			scoreP.appendChild(ident);

			Element enco = document.createElement("encoding");
			ident.appendChild(enco);

			Element soft = document.createElement("software");
			soft.appendChild(document.createTextNode("MuseScore 3.6.0"));
			enco.appendChild(soft);

			Element encod = document.createElement("encoding-date");
			LocalDateTime dano = LocalDateTime.now();
			encod.appendChild(document.createTextNode(dano.toString()));
			enco.appendChild(encod);

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

			String[] titlesubtitle = fileContent.get(0).split(titledelim);

			Element cred1 = document.createElement("credit");
			scoreP.appendChild(cred1);

			Attr crepag1 = document.createAttribute("page");
			crepag1.setValue("1");
			cred1.setAttributeNode(crepag1);

			Element credtyp1 = document.createElement("credit-type");
			credtyp1.appendChild(document.createTextNode("title"));
			cred1.appendChild(credtyp1);

			Element creword1 = document.createElement("credit-words");
			creword1.appendChild(document.createTextNode(titlesubtitle[0]));
			cred1.appendChild(credtyp1);

			Attr defax1 = document.createAttribute("default-x");
			defax1.setValue("595.4");
			creword1.setAttributeNode(defax1);

			Attr defay1 = document.createAttribute("default-y");
			defay1.setValue("1627.12");
			creword1.setAttributeNode(defay1);

			Attr justi1 = document.createAttribute("justify");
			justi1.setValue("center");
			creword1.setAttributeNode(justi1);

			Attr valign1 = document.createAttribute("valign");
			valign1.setValue("top");
			creword1.setAttributeNode(valign1);

			Attr fonsiz1 = document.createAttribute("font-size");
			fonsiz1.setValue("16");
			creword1.setAttributeNode(fonsiz1);

			Element cred2 = document.createElement("credit");
			scoreP.appendChild(cred2);

			Attr crepag2 = document.createAttribute("page");
			crepag2.setValue("1");
			cred2.setAttributeNode(crepag2);

			Element credtyp2 = document.createElement("credit-type");
			credtyp2.appendChild(document.createTextNode("subtitle"));
			cred2.appendChild(credtyp2);

			Element creword2 = document.createElement("credit-words");
			creword2.appendChild(document.createTextNode(titlesubtitle[1]));
			cred2.appendChild(credtyp2);

			Attr defax2 = document.createAttribute("default-x");
			defax2.setValue("595.4");
			creword2.setAttributeNode(defax2);

			Attr defay2 = document.createAttribute("default-y");
			defay2.setValue("1586.46");
			creword2.setAttributeNode(defay2);

			Attr justi2 = document.createAttribute("justify");
			justi2.setValue("center");
			creword2.setAttributeNode(justi2);

			Attr valign2 = document.createAttribute("valign");
			valign2.setValue("top");
			creword2.setAttributeNode(valign2);

			Attr fonsiz2 = document.createAttribute("font-size");
			fonsiz2.setValue("12");
			creword2.setAttributeNode(fonsiz2);

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
			// child element of scoreP (This should explain enough on how the hierarchy on
			// DOM works)
			Element scorepa = document.createElement("score-part");
			partL.appendChild(scorepa);

			Attr scorepaatt = document.createAttribute("id");
			scorepaatt.setValue("P1");
			scorepa.setAttributeNode(scorepaatt);

			Element partn = document.createElement("part-name");
			partn.appendChild(document.createTextNode("Guitar")); // Creates an text for the element
			scorepa.appendChild(partn);

			Attr pro = document.createAttribute("print-object");
			pro.setValue("no");
			partn.setAttributeNode(pro);

			Element partab = document.createElement("part-abbreviation");
			partab.appendChild(document.createTextNode("Gtr.")); // Creates an text for the element
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
			instname.appendChild(document.createTextNode("Acoustic Guitar (steel)"));
			scoin.appendChild(instname);

			Element instsound = document.createElement("instrument-sound");
			instsound.appendChild(document.createTextNode("pluck.guitar"));
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
			partn1.appendChild(document.createTextNode("Guitar [TAB]"));
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

			Attr measwidth = document.createAttribute("width");
			measwidth.setValue("485");
			meas.setAttributeNode(measwidth);

			Element print = document.createElement("print");
			meas.appendChild(print);

			Element measnumb = document.createElement("measure-numbering");
			measnumb.appendChild(document.createTextNode("none"));
			print.appendChild(measnumb);

			Element attr = document.createElement("attributes");
			meas.appendChild(attr);

			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			Integer k = 1;
			// loop used to count the divisions of the tablature
			for (int i = 0; i < fileContent.size(); i++) {
				if (fileContent.get(i).contains("B|")) {
					for (int j = 0; j < fileContent.get(i).length() - 1; j++) {
						if (fileContent.get(i).charAt(j) == '|') {
							if (fileContent.get(i).charAt(j - 1) == '-' && fileContent.get(i).charAt(j + 1) == '-') {
								k++;
							}
						}
					}
					break;
				}
			}

			String cdiv = k.toString();

			Element divis = document.createElement("divisions");
			divis.appendChild(document.createTextNode(cdiv));
			attr.appendChild(divis);

			Element key = document.createElement("key");
			attr.appendChild(key);

			Attr keyatt = document.createAttribute("print-object");
			keyatt.setValue("no");
			key.setAttributeNode(keyatt);

			Element fifth = document.createElement("fifths");
			fifth.appendChild(document.createTextNode("0"));
			key.appendChild(fifth);

			Element mode = document.createElement("mode");
			mode.appendChild(document.createTextNode("major"));
			key.appendChild(mode);

			Element clef = document.createElement("clef");
			attr.appendChild(clef);

			Element sign = document.createElement("sign");
			sign.appendChild(document.createTextNode("TAB"));
			clef.appendChild(sign);

			Element line = document.createElement("line");
			line.appendChild(document.createTextNode("5"));
			clef.appendChild(line);

			Element stafdet = document.createElement("staff-details");
			attr.appendChild(stafdet);

			Element stafline = document.createElement("staff-lines");
			stafline.appendChild(document.createTextNode("6"));
			stafdet.appendChild(stafline);

			Element staftune = document.createElement("staff-tuning");
			stafdet.appendChild(staftune);

			Attr staftuneatt = document.createAttribute("line");
			staftuneatt.setValue("1");
			staftune.setAttributeNode(staftuneatt);

			Element tunestep = document.createElement("tuning-step");
			tunestep.appendChild(document.createTextNode("E"));
			staftune.appendChild(tunestep);

			Element tuneoct = document.createElement("tuning-octave");
			tuneoct.appendChild(document.createTextNode("2"));
			staftune.appendChild(tuneoct);

			Element staftune1 = document.createElement("staff-tuning");
			stafdet.appendChild(staftune1);

			Attr staftuneatt1 = document.createAttribute("line");
			staftuneatt1.setValue("2");
			staftune1.setAttributeNode(staftuneatt1);

			Element tunestep1 = document.createElement("tuning-step");
			tunestep1.appendChild(document.createTextNode("A"));
			staftune1.appendChild(tunestep1);

			Element tuneoct1 = document.createElement("tuning-octave");
			tuneoct1.appendChild(document.createTextNode("2"));
			staftune1.appendChild(tuneoct1);

			Element staftune2 = document.createElement("staff-tuning");
			stafdet.appendChild(staftune2);

			Attr staftuneatt2 = document.createAttribute("line");
			staftuneatt2.setValue("3");
			staftune2.setAttributeNode(staftuneatt2);

			Element tunestep2 = document.createElement("tuning-step");
			tunestep2.appendChild(document.createTextNode("D"));
			staftune2.appendChild(tunestep2);

			Element tuneoct2 = document.createElement("tuning-octave");
			tuneoct2.appendChild(document.createTextNode("3"));
			staftune2.appendChild(tuneoct2);

			Element staftune3 = document.createElement("staff-tuning");
			stafdet.appendChild(staftune3);

			Attr staftuneatt3 = document.createAttribute("line");
			staftuneatt.setValue("4");
			staftune.setAttributeNode(staftuneatt3);

			Element tunestep3 = document.createElement("tuning-step");
			tunestep3.appendChild(document.createTextNode("G"));
			staftune3.appendChild(tunestep3);

			Element tuneoct3 = document.createElement("tuning-octave");
			tuneoct3.appendChild(document.createTextNode("3"));
			staftune3.appendChild(tuneoct3);

			Element staftune4 = document.createElement("staff-tuning");
			stafdet.appendChild(staftune4);

			Attr staftuneatt4 = document.createAttribute("line");
			staftuneatt4.setValue("5");
			staftune4.setAttributeNode(staftuneatt4);

			Element tunestep4 = document.createElement("tuning-step");
			tunestep4.appendChild(document.createTextNode("B"));
			staftune4.appendChild(tunestep4);

			Element tuneoct4 = document.createElement("tuning-octave");
			tuneoct4.appendChild(document.createTextNode("3"));
			staftune4.appendChild(tuneoct4);

			Element staftune5 = document.createElement("staff-tuning");
			stafdet.appendChild(staftune5);

			Attr staftuneatt5 = document.createAttribute("line");
			staftuneatt5.setValue("6");
			staftune5.setAttributeNode(staftuneatt5);

			Element tunestep5 = document.createElement("tuning-step");
			tunestep5.appendChild(document.createTextNode("E"));
			staftune5.appendChild(tunestep5);

			Element tuneoct5 = document.createElement("tuning-octave");
			tuneoct5.appendChild(document.createTextNode("4"));
			staftune5.appendChild(tuneoct5);

			Element stafsize = document.createElement("staff-size");
			stafsize.appendChild(document.createTextNode("167"));
			stafdet.appendChild(stafsize);

			Element soundtemp = document.createElement("sound");
			meas.appendChild(soundtemp);

			Attr tempo = document.createAttribute("tempo");
			tempo.setValue("Andantino");
			soundtemp.setAttributeNode(tempo);

			// Loops with nested loops that writes the pitches of the tablature
			// automatically. (Likely causing the java.lang.Outofmemory error)
			for (int i = 0; i < fileContent.size(); i++) { // outer for loop with the limit upto the array size

				if (fileContent.get(i).contains("E|") || fileContent.get(i).contains("B|") || fileContent.get(i).contains("G|")
						|| fileContent.get(i).contains("A|") || fileContent.get(i).contains("D|")) { // if statement with the condition set
																					// to only read variables within the
																					// lines

					Integer stringnum = 0;
					// This if statement defines which string you press (goes from E:6, B:5, G:4,
					// D:3, A:2, E:1)
					if (fileContent.get(i).contains("E|")) {
						if (fileContent.get(i + 5).contains("E|")) {
							stringnum = 6;
						} else {
							stringnum = 1;
						}
					} else if (fileContent.get(i).contains("B|")) {
						stringnum = 5;
					} else if (fileContent.get(i).contains("G|")) {
						stringnum = 4;
					} else if (fileContent.get(i).contains("A|")) {
						stringnum = 3;
					} else if (fileContent.get(i).contains("D|")) {
						stringnum = 2;
					}

					for (int j = 0; j < fileContent.get(i).length(); j++) { // This for statement is initiated once the if
																	// statement within the outer for loop is
																	// acknowledged
						// It checks every character within a string that was passed by the outer loop
						// to check for pitches to print
						if (Character.isDigit(fileContent.get(i).charAt(j))) { // If statement that initiates once it finds a
																		// pitch to print within a string
							// Don't worry about codes from here down to another line of comments
							Element notedef = document.createElement("note");
							meas.appendChild(notedef);

							double xvalue = j * 4.3;
							String xvalues = Double.toString(xvalue);

							double yvalue = -75 + (75 - 15 * (stringnum - 1));
							String yvalues = Double.toString(yvalue);

							Attr defaux = document.createAttribute("default-x");
							tempo.setValue(xvalues);
							notedef.setAttributeNode(defaux);

							Attr defauy = document.createAttribute("default-y");
							tempo.setValue(yvalues);
							notedef.setAttributeNode(defauy);

							Element pitch = document.createElement("pitch");
							notedef.appendChild(pitch);

							Element step = document.createElement("step");
							step.appendChild(document.createTextNode("C"));
							pitch.appendChild(step);

							String oct = Character.toString(fileContent.get(i).charAt(j));

							Element octa = document.createElement("octave");
							octa.appendChild(document.createTextNode(oct));
							pitch.appendChild(octa);

							int ik = 0;
							int is = 0;
							Integer dura = 1;

							while (ik == 0) {
								if (Character.isDigit(fileContent.get(i).charAt(j + is))) {
									ik = 1;
								} else {
									dura++;
								}
								is++;
							}

							String durat = Integer.toString(dura);

							Element dur = document.createElement("duration");
							dur.appendChild(document.createTextNode(durat));
							notedef.appendChild(dur);

							Element voice = document.createElement("voice");
							voice.appendChild(document.createTextNode("1"));
							notedef.appendChild(voice);

							Element typ = document.createElement("type");
							typ.appendChild(document.createTextNode("quarter"));
							notedef.appendChild(typ);

							Element stem = document.createElement("stem");
							stem.appendChild(document.createTextNode("none"));
							notedef.appendChild(stem);

							Element notations = document.createElement("notations");
							notedef.appendChild(notations);

							Element technical = document.createElement("technical");
							notations.appendChild(technical);
							/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

							if (fileContent.get(i).charAt(j + 1) == 'h') {
								Element hammer = document.createElement("hammer-on");
								hammer.appendChild(document.createTextNode("H"));
								technical.appendChild(hammer);

								Attr number = document.createAttribute("number");
								number.setValue("1");
								hammer.setAttributeNode(number);

								Attr type = document.createAttribute("type");
								type.setValue("start");
								hammer.setAttributeNode(type);

							} else if (fileContent.get(i).charAt(j + 1) == '/') {
								Element slideup = document.createElement("slide up");
								slideup.appendChild(document.createTextNode("/"));
								technical.appendChild(slideup);

								Attr number = document.createAttribute("number");
								number.setValue("1");
								slideup.setAttributeNode(number);

								Attr type = document.createAttribute("type");
								type.setValue("start");
								slideup.setAttributeNode(type);

							} else if (fileContent.get(i).charAt(j + 1) == 'p') {
								Element pulloff = document.createElement("pull-off");
								pulloff.appendChild(document.createTextNode("p"));
								technical.appendChild(pulloff);

								Attr number = document.createAttribute("number");
								number.setValue("1");
								pulloff.setAttributeNode(number);

								Attr type = document.createAttribute("type");
								type.setValue("start");
								pulloff.setAttributeNode(type);

							} /*
								 * else if (a.get(i).charAt(j-1) == '[') { Element naturalharmonics =
								 * document.createElement("natural harmonics");
								 * naturalharmonics.appendChild(document.createTextNode("[n]"));
								 * technical.appendChild(naturalharmonics);
								 * 
								 * Attr number = document.createAttribute("number"); number.setValue("1");
								 * naturalharmonics.setAttributeNode(number);
								 * 
								 * Attr type = document.createAttribute("type"); type.setValue("start");
								 * naturalharmonics.setAttributeNode(type);
								 * 
								 * }
								 */
							Element string = document.createElement("string");
							string.appendChild(document.createTextNode(Integer.toString(stringnum)));
							technical.appendChild(string);

							Element fret = document.createElement("fret");
							fret.appendChild(document.createTextNode(Character.toString(fileContent.get(i).charAt(j))));
							technical.appendChild(fret);

							Element slur = document.createElement("slur");
							notations.appendChild(slur);

							Attr bezierx = document.createAttribute("bezier-x");
							bezierx.setValue("18");
							slur.setAttributeNode(bezierx);

							Attr beziery = document.createAttribute("bezier-y");
							beziery.setValue("28");
							slur.setAttributeNode(beziery);

							Attr defaultx = document.createAttribute("default-x");
							defaultx.setValue("5");
							slur.setAttributeNode(defaultx);

							Attr defaulty = document.createAttribute("default-y");
							defaulty.setValue("-21");
							slur.setAttributeNode(defaulty);

							Attr number1 = document.createAttribute("number");
							number1.setValue("1");
							slur.setAttributeNode(number1);

							Attr placement = document.createAttribute("placement");
							placement.setValue("above");
							slur.setAttributeNode(placement);

							Attr type1 = document.createAttribute("type");
							type1.setValue("start");
							slur.setAttributeNode(type1);

						}
					}
				}

			}

			TransformerFactory trff = TransformerFactory.newInstance(); // Used to create Transformer objects.
			Transformer trf = trff.newTransformer(); // A class that can transform a source tree into a result tree.
			DOMSource dom = new DOMSource(document); // Document Object Model (DOM) tree that acts as a holder for a
														// transformation source tree.
			StreamResult stm = new StreamResult("./Streamresult.xml"); // This creates a XML file that is being
																		// translated from the DOMSource

			trf.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//Recordare//DTD MusicXML 3.1 Partwise//EN");
			trf.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://www.musicxml.org/dtds/partwise.dtd");
			trf.transform(dom, stm); // This translates your XML source to a result (in this case, everything under
										// document to StreamResult)

		} catch (ParserConfigurationException p) {
			p.printStackTrace();
		} catch (TransformerException t) {
			t.printStackTrace();
		}

	}
}
