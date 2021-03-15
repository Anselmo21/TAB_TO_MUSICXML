package guiTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class RunnerTest {
	
	@Start
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interface/Intro.fxml"));
			Scene scene = new Scene(root, 935, 700);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("Interface/Intro.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void browseButton(FxRobot robot) {
		robot.clickOn("#browse");
		FxAssert.verifyThat("#browse", LabeledMatchers.hasText("Browse"));
	}
	
//	void browseFileChooserOpened(FxRobot robot) {
//		robot.clickOn("#browse");
//	}
	
	void convertButton(FxRobot robot) {
		FxAssert.verifyThat("#convert", LabeledMatchers.hasText("Convert"));
	}
	
	void saveButton(FxRobot robot) {
		FxAssert.verifyThat("#save", LabeledMatchers.hasText("Save File"));
	}
}
