package Interface;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class RunnerTest {
	
	@Start
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interface/Intro.fxml"));
		primaryStage.setTitle("Allegro Tab Converter");
		Scene scene = new Scene(root, 935, 700);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("Interface/Intro.css").toExternalForm());
		Image logo = new Image("/Interface/music-background.jpg");
		primaryStage.getIcons().add(logo);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Test
	void browseButton(FxRobot robot) {
		robot.clickOn("#browse");
		FxAssert.verifyThat("#browse", LabeledMatchers.hasText("Browse File"));
	}
	
	@Test
	void convertButton(FxRobot robot) {
		FxAssert.verifyThat("#convert", LabeledMatchers.hasText("Convert"));
	}
	
	@Test
	void saveButton(FxRobot robot) {
		FxAssert.verifyThat("#save", LabeledMatchers.hasText("Save File"));
	}
}
