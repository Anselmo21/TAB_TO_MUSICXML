package TAB_TO_XML;

import java.awt.Desktop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/*
 * Javafx GUI class
 */

public class GUI extends Application {

	public static final String xmlFilePath = "C:\\Users\\xmlfile.musicxml";

	private Desktop dt = Desktop.getDesktop(); //User Desktop 
	TextArea browseTextBox = new TextArea(); 
	final Button ob = new Button(); //Clickable button for opening the tab to search for the file to open
	final Button ob2 = new Button(); // Clickable button for converting
	final FileChooser fc = new FileChooser(); //
	final Label ol = new Label();
	final Label obl = new Label();
	final Label el = new Label();
	final ComboBox dropDownMenu = new ComboBox();
	final GridPane inputValues = new GridPane(); //The parent which concludes of children such as buttons and labels
	BufferedReader input;
	StreamResult output;
	TransformerHandler th;
	File fi;

	/*
	 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 * ACCEPT METHOD FOR A FILE
	 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 */

	/**
	 * This method checks if the file browsed is a text file or not, and returns a message accordingly.
	 * @param filename
	 * @return true if the file extension is a text file, false otherwise
	 */
	private boolean accept(File filename) { //Helper Method to determine if a file is a textfile
		if(filename.getName().endsWith(".txt")) { 
			return true;
		}
		else {
			Alert errorAlert = new Alert(AlertType.ERROR); //creates a displayable error allert window 
			errorAlert.setHeaderText("Input not valid"); 
			errorAlert.setContentText("Provide text file"); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
			errorAlert.showAndWait();
			return false;
		}
	}

	/*
	 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 * BODY STRUCTURE OF GRAPHIC USER INTERFACE
	 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 */

	/**
	 * Overridden start method that originally came from the Application class from Javafx. 
	 * This method constructs the body structure of the GUI.
	 * @param primaryStage is the primary page of this application, onto which the application scene can be set.
	 * <p> more stages can be created if the developers choose so!
	 */
	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("MusicXML converter"); //Title

		Button ob = new Button("Browse"); //Push button that is redirected to a file selecting page


		FileChooser fc = new FileChooser(); //Opens browser to select the txt file
		Label ol = new Label("~Welcome to the ultimate MusicXML converter~ \nSelect the file that you want to convert!"); //Description of the use of the desktop application
		Label obl = new Label("Please select the tablature in txt format: "); //Indicator on what the button is for
		Label el = new Label("");//Empty label

		//object that will do the "adding" of buttons in the GUI

		final Pane rg = new VBox(12); //Base class that is used to have the children of inputValues public



		//Text Customization
		Font font = Font.font("Browsed", FontWeight.EXTRA_BOLD, 40); //Font for the buttons
		Font font1 = Font.font("label font", FontWeight.NORMAL, 20); //Font for the plan texts

		ob2.setText("Convert"); //Convert button
		ob2.setFont(font);
		ob2.setStyle("-fx-text-fill: #0000ff"); //blue font

		ob.setFont(font);		
		obl.setFont(font1);
		ol.setFont(font1);

		browseTextBox.setFont(font1);
		browseTextBox.setPromptText("Browse File Path");
		browseTextBox.setPrefSize(300, 50);
		browseTextBox.setEditable(false);

		ob.setOnAction(new SingleFcButtonListener());

		ob.setOnAction( //Sets the value of the button on Action.

				new EventHandler<ActionEvent>() { //Interface for handling events

					@Override
					/**
					 * Invoked when 'browse' button is clicked...searching of files begins
					 * @param ActionEvent is the event associated when the browse button was clicked.
					 */
					public void handle(final ActionEvent e) {
						fi = fc.showOpenDialog(primaryStage); //Pops up an "Open File" file chooser dialog
						if (fi != null && accept(fi) == true) {
							browseTextBox.setText(fi.getAbsolutePath());
							inputValues.add(ob2,2,2);
							ob2.setOnAction(new EventHandler<ActionEvent>() { //if you feed a proper file..convert button will appear 
								public void handle(ActionEvent a) {
								convertToXML(fi);
								openFile(fi);
								Alert errorAlert = new Alert(AlertType.CONFIRMATION); //creates a displayable error allert window 
								errorAlert.setHeaderText("The selected file is being converted to XML"); 
								errorAlert.setContentText("The process might take a while..."); //Shows this stage and waits for it to be hidden (closed) before returning to the caller.
								errorAlert.showAndWait();
								}
							});
						
						}
					}
				}); 

	
		
			
				
	

		/*
		 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		 * DRAG AND DROP FEATURE
		 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		 */

		Label label = new Label("Drag And Drop A File Here");
		Label dropped = new Label("");

		try {
			VBox dragTarget = new VBox();

			//Extensions that are valid to be dragged and dropped
			List<String> validExtensions = Arrays.asList("txt", "log");

			dragTarget.getChildren().addAll(label,dropped);
			dragTarget.setOnDragOver(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
					if (event.getGestureSource() != dragTarget
							&& event.getDragboard().hasFiles()) { 

						//All files must have the accepted extension
						if (!validExtensions.containsAll(event.getDragboard().getFiles().stream().map(file -> getExtension(file.getName())).collect(Collectors.toList()))) {
							event.consume();
							return;
						}

						/* allow for both copying and moving, whatever user chooses */
						event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
					}
					event.consume(); // you don't want the event to be dispatched to any further event listeners.
					//events are dispatched in last in, first out fashion
				}
			});

			dragTarget.setOnDragDropped(new EventHandler<DragEvent>() {

				@Override
				public void handle(DragEvent event) {
					Dragboard db = event.getDragboard(); //assign event's dragboard to a variable for ease 
					boolean success = false;
					if (event.getGestureSource() != dragTarget && db.hasFiles()) {
						dropped.setText(db.getFiles().toString()); //The text to display in the label. 
						event.getDragboard().getFiles().forEach(file -> System.out.println(file.getAbsolutePath()));
						success = true;

					}


					/* let the source know whether the string was successfully 
					 * transferred and used */
					event.setDropCompleted(success);

					event.consume(); 
				}
			});

			/*
			 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			 * DIMENSIONS OF BUTTONS?
			 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			 */

			StackPane root = new StackPane();
			inputValues.add(dragTarget, 1, 6);
			// For some reason it's not letting me drop anything stil....lol


			//Restricts the allowable columns and rows for the location of each text or button


			
			inputValues.add(ob, 1, 2);
			inputValues.add(browseTextBox, 0, 2);
			//inputValues.add(obl, 0, 2);
			inputValues.add(el, 0, 1);
			inputValues.add(ol, 0, 0);
			inputValues.setHgap(6); //The width of the horizontal gaps between columns.
			inputValues.setVgap(6); //The length of the vertical gaps between rows       

			rg.getChildren().addAll(inputValues); //method from superclass 'Pane', this gets all the children and places them in the GUI
			rg.setPadding(new Insets(12, 12, 12, 12));

			//create a scene for the primary page
			Scene Scene1 = new Scene(rg); 
			primaryStage.setScene(Scene1); 
			primaryStage.show(); //make the primary page visible to the user
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Getter method to get the extension of the file.
	private String getExtension (String fileName) {
		String extension = "";
		int i = fileName.lastIndexOf('.');
		if (i > 0 && i < fileName.length() - 1) //if the name is not empty
			return fileName.substring(i + 1).toLowerCase();
		return extension;
	}

	/**
	 * The method that opens fc and takes a input file. Throws an IOException when the file is corrupted.
	 * @param fi is the file to be opened
	 */
	private void openFile(File fi) {

		/**
		try {
			dt.open(fi); 
		} catch (IOException ex) {
			Logger.getLogger(GUI.class.getName())
			.log(Level.SEVERE, null, ex);
		} 
		 */

		ArrayList<String> datain = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new FileReader(fi))) { //Reads the file that was selected by the user

			String line = reader.readLine();
			while (line != null) {
				datain.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (dropDownMenu.getValue() == "Guitar") { //Calls methods that sends the input from the file to XML, the class that is called for methods depends on dropdownmenu input
			Guitar.Convert(datain);
		}	else if (dropDownMenu.getValue() == "Drum") {

		}	else if (dropDownMenu.getValue() == "Bass") {

		}

	}


	/*
	 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 * IDK WHAT THIS DOES. WHOEVER KNOWS PLEASE FILL SOMETHING HERE.
	 * %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 */

	/**
	 * Private helper class
	 */
	private class SingleFcButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {

			showSingleFileChooser();
		}

		private void showSingleFileChooser() {

			try (BufferedReader reader = new BufferedReader(new FileReader(new File("file.txt")))) {

				String line;
				while ((line = reader.readLine()) != null)
					System.out.println(line);

			} 

			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Main function that runs the Application.launch() method
	 */
	public static void main(String[] args) {
		launch(args);
	}
	public void convertToXML(File fi) { //helper method to convert file to be used in gui method
		try {
			input = new BufferedReader(new FileReader(fi)); //reads the file..creates a buffering character-input stream that uses a default-sized input buffer.
			output = new StreamResult("musicXML-convertedTab.xml"); //will receive the "transformed" or converted file and will be stored in the user's pc
			createXml(); //helper method see below!
			String xmlLine;
			while ((xmlLine = input.readLine()) != null) { //converts every content of the file into a string object
				process(xmlLine); //concatenate the lines with the XML elements "
			}
			input.close(); //closes the bufferedreader, this 
			endXml(); //helper method see below!
		} catch (Exception e) {
			e.printStackTrace(); //if any exception occurs, slap an error message
		}
	}
	/**
	 * This creates the XML environment 
	 * @throws ParserConfigurationException if any error occurs during the parsing process
	 * @throws TransformerConfigurationException if any error occurs during the "conversion process"
	 * @throws SAXException if any error occurs during the parsing of the created XML document
	 */
	public void createXml() throws ParserConfigurationException, TransformerConfigurationException, SAXException {


		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		th = tf.newTransformerHandler(); 
		// The two lines above creates an object that listens for any event that requires for a file to be transformed 
		// See the Java Api for more explanation

		// customization details
		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");

		th.setResult(output); //where our result will be stored
		th.startDocument(); //the transformer handler will start creating the document
		th.startElement(null, null, "inserts", null); //the beginning tag of the doc
	}

	public void process(String s) throws SAXException {
		// the passed string will undergo the following process
		th.startElement(null, null, "note", null);  //first tag of each string 
		th.characters(s.toCharArray(), 0, s.length()); // takes the string->places them in character array->to create new string to be used 
		th.endElement(null, null, "note"); //what each strings end tag is 
	}


	public void endXml() throws SAXException {
		th.endElement(null, null, "inserts"); // the last tag of every element 
		th.endDocument(); //ends the document duh

	}
}

