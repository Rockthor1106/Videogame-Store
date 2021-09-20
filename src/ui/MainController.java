package ui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.VideoGameStore;

public class MainController {
	
    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField cashiersNumber;

    @FXML
    private TextField racksNumber;

    @FXML
    private TextField clientsNumber;

    @FXML
    private TextArea gamesToBuy;
    
    private VideoGameStore videoGameStore;

    public MainController(VideoGameStore videoGameStore) {
    	this.videoGameStore = videoGameStore;
    }
    
    public MainController() {
    	
    }
    
    @FXML
    void process(ActionEvent event) {
    	if (cashiersNumber.getText().equals("") || racksNumber.getText().equals("") || clientsNumber.getText().equals("") || gamesToBuy.getText().equals("")) {
			alert(AlertType.WARNING, "Setting of video game store conditions", "Please fill all blanks before to continue");
		}
    	else {
			
		}
    }
    
    public void alert(AlertType alertType, String alertTitle, String Alertmsg) {
    	Alert alert = new Alert(alertType);
    	alert.setTitle(alertTitle);
    	alert.setHeaderText(null);
    	alert.setContentText(Alertmsg);
    	alert.show();
    }

	@FXML
	void importVideoGamesCatalog(ActionEvent event) {
		FileChooser fChooser = new FileChooser();
		fChooser.setTitle("Import video games catalog");
		File file = fChooser.showOpenDialog(mainPane.getScene().getWindow());
		
		if (file != null) {
			try {
				videoGameStore.importVideoGamesCatalog(file.getAbsolutePath());
				alert(AlertType.INFORMATION, "Import catalog", "The catalog has been imported successfully");
				
			} catch (IOException e) {
				alert(AlertType.ERROR, "Import catalog", "The catalog has not been imported. An error has ocurred");
			}
		}
	}
}
