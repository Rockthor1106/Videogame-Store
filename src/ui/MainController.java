package ui;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Client;
import model.VideoGameStore;

public class MainController {
	
    public MainController(VideoGameStore videoGameStore) {
    	this.videoGameStore = videoGameStore;
    }
    
    public MainController() {
    	
    }
    
    public void alert(AlertType alertType, String alertTitle, String Alertmsg) {
    	Alert alert = new Alert(alertType);
    	alert.setTitle(alertTitle);
    	alert.setHeaderText(null);
    	alert.setContentText(Alertmsg);
    	alert.show();
    }
	
	//----------------------------------------InputScreen.fxml---------------------------------------------
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
    
    private int clientsQuantity;
    private String[] clientsGames;
    
    @FXML
    public void process(ActionEvent event) throws IOException {
    	if (cashiersNumber.getText().equals("") || racksNumber.getText().equals("") || clientsNumber.getText().equals("") || gamesToBuy.getText().equals("")) {
			alert(AlertType.WARNING, "Setting of video game store conditions", "Please fill all blanks before to continue");
		}
    	else {
			int cashiersQuantity = Integer.parseInt(cashiersNumber.getText());
			int racksQuantity = Integer.parseInt(racksNumber.getText());
			clientsQuantity = Integer.parseInt(clientsNumber.getText());
			
			videoGameStore.setCashiers(cashiersQuantity);
			videoGameStore.setRacksNumber(racksQuantity);
			videoGameStore.setClients(clientsQuantity);
			clients = new Client[clientsQuantity];
			clientsGames = gamesToBuy.getText().split("\n");
			
			//display next screen
			chooseSortingScreen(event);
		}
    }
    
    private void clientsActions(int clientsQuantity, String[] clientsGames, boolean phase) { //phase = true, after phase 2 and phase = false, after phase 3
    	for(int i = 0; i<clientsQuantity; i++) {
			String[] strs = clientsGames[i].split(" "); 
			int[] games = new int[strs.length-1];
			for(int j = 1; j<= games.length; j++) {
				games[j-1] = Integer.parseInt(strs[j]);
			}
			if(phase == true) {
				clients[i] = new Client(Integer.parseInt(strs[0]), games, i+1);
			}
			else {
				clients[i].increaseTime(clients[i].getGamesQuantity());
			}
			
		}
    	//bubbleSort
    	for(int i = clients.length; i>0; i--) {
			for(int j = 0; j<i-1; j++) {
				if(clients[j].getTime() > clients[j+1].getTime()) {
					Client temp = clients[j+1];
					clients[j+1] = clients[j];
					clients[j] = temp;
				}
			}
		}

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
	//-------------------------------------------------------------------------------------------------------------
	
	//------------------------------------------ChooseSortingScreen.fxml----------------------------------------------
	@FXML
	void chooseSortingScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChooseSortingScreen.fxml"));
		fxmlLoader.setController(this);    	
		Parent loginScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(loginScreen);
    	alert(AlertType.INFORMATION, "Chosee a sorting", "As result of this phase the wish lists will be sorted to get the best order to pick up the games");
	}
	
    @FXML
    void sortingOne(ActionEvent event) throws IOException {
    	clientsActions(clientsQuantity, clientsGames, true);
    	for(int i = 0; i<clients.length; i++) {
    		clients[i].setGamesToBuy(videoGameStore.orderList(clients[i].getWishListCode(), true));
    	}  	
    	afterSectionTwoScreen(event);
    }
    
    @FXML
    void sortingTwo(ActionEvent event) throws IOException {
    	
    	afterSectionTwoScreen(event);
    }


	//-------------------------------------------------------------------------------------------------------------
    
    //--------------------------------OrderToPickUpGamesScreen.fxml------------------------------------------------

    @FXML
    private TextArea clientList1;

    @FXML
    private TextArea clientList2;

    @FXML
    private TextArea clientList3;

    @FXML
    private TextArea clientList4;

    @FXML
    private TextArea clientList5;
    
    @FXML
    private Label section;
    
	@FXML
	void afterSectionTwoScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterSectionTwoScreen.fxml"));
		fxmlLoader.setController(this); 
		Parent loginScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(loginScreen);
		
		switch (clientsQuantity) {
		case 1:
				clientList1.setText(clients[0].toStringGames());
			break;
		case 2:
				clientList1.setText(clients[0].toStringGames());
				clientList2.setText(clients[1].toStringGames());
			break;
		case 3:
				clientList1.setText(clients[0].toStringGames());
				clientList2.setText(clients[1].toStringGames());
				clientList3.setText(clients[2].toStringGames());
			break;
		case 4:
				clientList1.setText(clients[0].toStringGames());
				clientList2.setText(clients[1].toStringGames());
				clientList3.setText(clients[2].toStringGames());
				clientList4.setText(clients[3].toStringGames());
			break;
		case 5:
				clientList1.setText(clients[0].toStringGames());
				clientList2.setText(clients[1].toStringGames());
				clientList3.setText(clients[2].toStringGames());
				clientList4.setText(clients[3].toStringGames());
				clientList5.setText(clients[4].toStringGames());
			break;		
		default:
			break;
		}
	}
	
	@FXML
	void afterSectionThreeScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("afterSectionThreeScreen.fxml"));
		fxmlLoader.setController(this); 
		Parent loginScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(loginScreen);
		
		switch (clientsQuantity) {
		case 1:
				clientList1.setText(clients[0].toStringGames2());
			break;
		case 2:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
			break;
		case 3:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
			break;
		case 4:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
				clientList4.setText(clients[3].toStringGames2());
			break;
		case 5:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
				clientList4.setText(clients[3].toStringGames2());
				clientList5.setText(clients[4].toStringGames2());
			break;		
		default:
			break;
		}
	}
	
	public void orderGamesToPickUp(int clientsQuantity, int cashiersQuantity) {
    	Queue<Client> st = new LinkedList<>();
    	for(int i = 0; i<clients.length; i++) {
    		clients[i].setGamesToBuy(videoGameStore.orderList(clients[i].getWishListCode(), true));
    		st.add(clients[i]);
    	}
    	while(!st.isEmpty()) {
    		for(int i = 0; i<clientsQuantity; i++) {
    			if(i<cashiersQuantity && !st.isEmpty()) {
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
    //-------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------Payment Screen---------------------------------------------------------
	@FXML
	void paymentScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paymentScreen.fxml"));
		fxmlLoader.setController(this);    	
		Parent loginScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(loginScreen);
    	
    	switch (clientsQuantity) {
		case 1:
				clientList1.setText(clients[0].toStringGames2());
			break;
		case 2:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
			break;
		case 3:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
			break;
		case 4:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
				clientList4.setText(clients[3].toStringGames2());
			break;
		case 5:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
				clientList4.setText(clients[3].toStringGames2());
				clientList5.setText(clients[4].toStringGames2());
			break;		
		default:
    	}
	}
	//-------------------------------------------------------------------------------------------------------------
}
