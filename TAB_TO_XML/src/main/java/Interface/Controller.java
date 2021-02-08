package Interface;

import java.awt.Desktop;
import java.io.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller {
	
	Desktop screen = Desktop.getDesktop();
	
	@FXML
	Button browse;
	
	@FXML
	Label path;
	
	@FXML
	public void handleButtonBrowse(ActionEvent event) throws IOException {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
		File tablature = fc.showOpenDialog(null);
		
		if (tablature != null) {
			path.setText(tablature.getAbsolutePath());
			try {
				screen.open(tablature);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
