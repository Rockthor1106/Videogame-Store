package ui;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Client;
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

    private Client[] clients;
    
    public MainController(VideoGameStore videoGameStore) {
    	this.videoGameStore = videoGameStore;
    }
    
    public MainController() {
    	
    }
    
    @FXML
    public void process(ActionEvent event) {
    	if (cashiersNumber.getText().equals("") || racksNumber.getText().equals("") || clientsNumber.getText().equals("") || gamesToBuy.getText().equals("")) {
			alert(AlertType.WARNING, "Setting of video game store conditions", "Please fill all blanks before to continue");
		}
    	else {
			int cashiersQuantity = Integer.parseInt(cashiersNumber.getText());
			int racksQuantity = Integer.parseInt(racksNumber.getText());
			int clientsQuantity = Integer.parseInt(clientsNumber.getText());
			
			videoGameStore.setCashiers(cashiersQuantity);
			videoGameStore.setRacksNumber(racksQuantity);
			videoGameStore.setClients(clientsQuantity);
			clients = new Client[clientsQuantity];
			String[] clientsGames = gamesToBuy.getText().split("\n");
			
			clientsActions(clientsQuantity, clientsGames);
		}
    }
    
    private void clientsActions(int clientsQuantity, String[] clientsGames) {
    	for(int i = 0; i<clientsQuantity; i++) {
			String[] strs = clientsGames[i].split(" "); 
			int[] games = new int[strs.length-1];
			for(int j = 1; j<= games.length; j++) {
				games[j-1] = Integer.parseInt(strs[j]);
			}
			clients[i] = new Client(Integer.parseInt(strs[0]), games, i+1+(games.length));
		}
    	//bubblesort
    	for(int i = clients.length; i>0; i--) {
			for(int j = 0; j<i-1; j++) {
				if(clients[j].getTime() > clients[j+1].getTime()) {
					Client temp = clients[j+1];
					clients[j+1] = clients[j];
					clients[j] = temp;
				}
			}
		}
    	Queue<Client> st = new LinkedList<>();
    	for(int i = 0; i<clients.length; i++) {
    		clients[i].setGamesToBuy(videoGameStore.orderList(clients[i].getWishListCode(), true));
    		st.add(clients[i]);
    	}
    	while(!st.isEmpty()) {
    		for(int i = 0; i<clientsQuantity; i++) {
    			if(i<3 && !st.isEmpty()) {
    				Client temp = st.remove();
    				temp.decreaseGames();
    				if(temp.getGamesQuantity() > 0) {
    					st.add(temp);
    				}else {
    					System.out.println(temp.toString());
    				}
    			}else if(!st.isEmpty()) {
    				st.add(st.remove());
    			}
    		}
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
	public void importVideoGamesCatalog(ActionEvent event) {
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
