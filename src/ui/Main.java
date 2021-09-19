package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.VideoGameStore;

public class Main extends Application{
	
	private MainController mainController;
	private VideoGameStore videoGameStore;
	
	public Main() {
		videoGameStore = new VideoGameStore();
		mainController = new MainController(videoGameStore);
	}
	
	public static void main(String[]args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InputScreen.fxml"));
		
		fxmlLoader.setController(mainController);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Casa Dorada");
		primaryStage.show();
	}
	
}
