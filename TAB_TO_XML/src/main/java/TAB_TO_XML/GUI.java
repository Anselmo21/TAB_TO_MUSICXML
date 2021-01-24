package TAB_TO_XML;


import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

	private Desktop dt = Desktop.getDesktop(); //User Desktop 
	
	 final Button ob = new Button(); //Clickable button for opening the tab to search for the file to open
	 final FileChooser fc = new FileChooser(); //
	 final Label ol = new Label();
	 final Label obl = new Label();
	 final Label el = new Label();
	 final ComboBox dropDownMenu = new ComboBox();

	/**
	 * Overridden start method that originally came from the Application class from Javafx. 
	 * This method constructs the body structure of the GUI.
	 * @param primaryStage is the primary page of this application, onto which the application scene can be set.
	 * <p> more stages can be created if the developers choose so!
	 */
    @Override
    public void start(Stage primaryStage) {
    	
    	primaryStage.setTitle("TTT(TextToTablature)"); //Title

    	Button ob = new Button("Browse"); //Push button that is redirected to a file selecting page
        
    	FileChooser fc = new FileChooser(); //Opens browser to select the txt file
        Label ol = new Label("TTT is a desktop application used for converting "
        		+ "txt tablatures into formal tablatures."); //Description of the use of the desktop application
        Label obl = new Label("Please select the tablature in txt format: "); //Indicator on what the button is for
        Label el = new Label("");//Empty label
        
        //object that will do the "adding" of buttons in the GUI
        final GridPane inputValues = new GridPane(); //The parent which concludes of children such as buttons and labels
        final Pane rg = new VBox(12); //Base class that is used to have the children of inputValues public
        
        // DropDown menu construction
       dropDownMenu.getItems().addAll("Guitar", "Drum", "Bass");
       dropDownMenu.setValue("Instrument of Choice");
       
        
        
        
        //Text Customization
        Font font = Font.font("Browsed", FontWeight.EXTRA_BOLD, 40); //Font for the buttons
        Font font1 = Font.font("label font", FontWeight.NORMAL, 20); //Font for the plan texts

        ob.setFont(font);
        obl.setFont(font1);
        ol.setFont(font1);
        
        ob.setOnAction( //Sets the value of the button on Action.

        	new EventHandler<ActionEvent>() { //Interface for handling events
        		
        		
        @Override
        /**
         * Invoked when 'browse' button is clicked...searching of files begins
         * @param ActionEvent is the event associated when the browse button was clicked.
         */
        public void handle(final ActionEvent e) {
        	
        File fi = fc.showOpenDialog(primaryStage); //Pops up an "Open File" file chooser dialog
        if (fi != null) {
        	
        	openFile(fi); //opens the selected file 
        	
        	}
        
        else { // display error message if file doesn't exist
        	
        	final Label notification = new Label();
        	notification.setText("You have not selected a textFile");
        	
        		}
        
        	}
        
        }); 
        
        // Drag & Drop feature 
        Label label = new Label("Drag And Drop A File Here");
        Label dropped = new Label("");
        VBox dragTarget = new VBox();
        dragTarget.getChildren().addAll(label,dropped);
        dragTarget.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dragTarget
                        && event.getDragboard().hasFiles()) { 
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
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    dropped.setText(db.getFiles().toString());
                    success = true;
                }
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });
        
        StackPane root = new StackPane();
        inputValues.add(dragTarget, 1, 6);
        // For some reason it's not letting me drop anything stil....lol

       


        
        
        		
        //Restricts the allowable columns and rows for the location of each text or button

        inputValues.add(dropDownMenu, 2, 5);  //adds the drop-down menu 
        inputValues.add(ob, 1, 2);
        inputValues.add(obl, 0, 2);
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
    
    /**
	 * The method that opens fc and takes a input file. Throws an IOException when the file is corrupted.
	 * @param fi is the file to be opened
	 */
    private void openFile(File fi) {
    	
    	//This opens the file of your choice, but it's not necessary at the moment as it's only for testing whether this method works or not
    	/**
    	try {
            dt.open(fi); 
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName())
            .log(Level.SEVERE, null, ex);
        } 
    	*/ 
    	
    	try (BufferedReader reader = new BufferedReader(new FileReader(fi))) { //Reads the file that was selected by the user

            ArrayList<Character> datain = new ArrayList<Character>(); //Arraylist that stores the data from the txt file character by character
            int i = 0;
            int j = 0;
            while ((i = reader.read()) != -1) {
            	char ch = (char) i;
            	datain.add(ch);
            	/**
            	System.out.println(datain.get(j));
            	j++;
            	*/
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    /**
     * Main function that runs the Application.launch() method
     */
    public static void main(String[] args) {
        launch(args);
    }

}