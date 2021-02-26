package Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Runner extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Interface/Intro.fxml"));
		primaryStage.setTitle("Allegro Tab Converter");
		Scene scene = new Scene(root, 935, 700);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("Interface/Intro.css").toExternalForm());
		Image logo = new Image("/Interface/music-background.jpg");
		primaryStage.getIcons().add(logo);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}


