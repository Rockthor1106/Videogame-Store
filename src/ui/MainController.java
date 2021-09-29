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
import javafx.scene.image.ImageView;
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
    private int cashiersQuantity;
    private String[] clientsGames;
    
    @FXML
    public void process(ActionEvent event) throws IOException {
    	if (cashiersNumber.getText().equals("") || racksNumber.getText().equals("") || clientsNumber.getText().equals("") || gamesToBuy.getText().equals("")) {
			alert(AlertType.WARNING, "Setting of video game store conditions", "Please fill all blanks before to continue");
		}
    	else {
			cashiersQuantity = Integer.parseInt(cashiersNumber.getText());
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
    
    private void clientsActions(int clientsQuantity, String[] clientsGames, int phase) { 
    	for(int i = 0; i<clientsQuantity; i++) {
			String[] strs = clientsGames[i].split(" "); 
			int[] games = new int[strs.length-1];
			for(int j = 1; j<= games.length; j++) {
				games[j-1] = Integer.parseInt(strs[j]);
			}
			if(phase == 1) { //after phase two
				clients[i] = new Client(Integer.parseInt(strs[0]), games, i+1);
			}
			else if(phase == 2) { //after phase three
				clients[i].increaseTime(clients[i].getGamesQuantity());
			}
			else if (phase == 3) { //payment phase
				clients[i].increaseTime((clients[i].getGamesQuantity()+clients[i].getGamesQuantity()));
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
    	clientsActions(clientsQuantity, clientsGames, 1);
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
				client2.setVisible(false);
				clientList2.setVisible(false);
				client3.setVisible(false);
				clientList3.setVisible(false);
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 2:
				clientList1.setText(clients[0].toStringGames());
				clientList2.setText(clients[1].toStringGames());
				client3.setVisible(false);
				clientList3.setVisible(false);
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 3:
				clientList1.setText(clients[0].toStringGames());
				clientList2.setText(clients[1].toStringGames());
				clientList3.setText(clients[2].toStringGames());
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 4:
				clientList1.setText(clients[0].toStringGames());
				clientList2.setText(clients[1].toStringGames());
				clientList3.setText(clients[2].toStringGames());
				clientList4.setText(clients[3].toStringGames());
				client5.setVisible(false);
				clientList5.setVisible(false);
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
				client2.setVisible(false);
				clientList2.setVisible(false);
				client3.setVisible(false);
				clientList3.setVisible(false);
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 2:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				client3.setVisible(false);
				clientList3.setVisible(false);
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 3:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 4:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
				clientList4.setText(clients[3].toStringGames2());
				client5.setVisible(false);
				clientList5.setVisible(false);
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
	
	//--------------------------------------PaymentScreen.fxml---------------------------------------------------------
	@FXML
    private ImageView clientCashier1;

    @FXML
    private TextArea listClientCashier1;

    @FXML
    private ImageView clientCashier2;

    @FXML
    private TextArea listClientCashier2;

    @FXML
    private ImageView clientCashier3;

    @FXML
    private TextArea listClientCashier3;

    @FXML
    private ImageView client1;

    @FXML
    private ImageView client2;

    @FXML
    private ImageView client3;

    @FXML
    private ImageView client4;

    @FXML
    private ImageView client5;

    @FXML
    private ImageView cashier1;

    @FXML
    private ImageView cashier2;

    @FXML
    private ImageView cashier3;
	
	@FXML
	void paymentScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paymentScreen.fxml"));
		fxmlLoader.setController(this);    	
		Parent loginScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(loginScreen);
    	
    	switch (cashiersQuantity) {
		case 1:
				cashier2.setVisible(false);
				cashier3.setVisible(false);
			break;
		case 2:
				cashier3.setVisible(false);
			break;

		default:
			break;
		}
    	
    	switch (clientsQuantity) {
		case 1:
				clientList1.setText(clients[0].toStringGames2());
				client2.setVisible(false);
				clientList2.setVisible(false);
				client3.setVisible(false);
				clientList3.setVisible(false);
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 2:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				client3.setVisible(false);
				clientList3.setVisible(false);
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 3:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
				client4.setVisible(false);
				clientList4.setVisible(false);
				client5.setVisible(false);
				clientList5.setVisible(false);
			break;
		case 4:
				clientList1.setText(clients[0].toStringGames2());
				clientList2.setText(clients[1].toStringGames2());
				clientList3.setText(clients[2].toStringGames2());
				clientList4.setText(clients[3].toStringGames2());
				client5.setVisible(false);
				clientList5.setVisible(false);
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
	
	@FXML
	public void startPaymentProcess(ActionEvent event) throws IOException {
		
		switch (cashiersQuantity) {
		case 1:
				alert(AlertType.INFORMATION, "Payment Phase", "In this case each client must go through the cashier, so they will go out in the same order");
				exitOrderScreen(event);
		case 2:
			if (clientsQuantity == 1) {
				
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
		    	exitOrderScreen(event); //the process has finished
			}
			else if (clientsQuantity == 2) {
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
				//client 2
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[1].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
		    	exitOrderScreen(event);
			}
			else if (clientsQuantity == 3) {
				
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
				//client 2
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[1].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
				//client 3
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[2].toStringGames2());
		    	client3.setVisible(false);
		    	clientList3.setVisible(false);
		    	
		    	exitOrderScreen(event);
			}
			else if (clientsQuantity == 4) {
				
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
				//client 2
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[1].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
				//client 3
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[2].toStringGames2());
		    	client3.setVisible(false);
		    	clientList3.setVisible(false);
		    	
				//client 4
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[3].toStringGames2());
		    	client4.setVisible(false);
		    	clientList4.setVisible(false);
		    	
		    	exitOrderScreen(event);
			}
			else if (clientsQuantity == 5) {
				
				
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
				//client 2
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[1].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
				//client 3
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[2].toStringGames2());
		    	client3.setVisible(false);
		    	clientList3.setVisible(false);
		    	
				//client 4
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[3].toStringGames2());
		    	client4.setVisible(false);
		    	clientList4.setVisible(false);
		    	
				//client 5
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[4].toStringGames2());
		    	client5.setVisible(false);
		    	clientList5.setVisible(false);
		    	
		    	exitOrderScreen(event);
			}
			break;
		case 3:
			if (clientsQuantity == 1) {
				
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
		    	exitOrderScreen(event); //the process has finished
			}
			else if (clientsQuantity == 2) {
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
				//client 2
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[1].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
		    	exitOrderScreen(event);
			}
			else if (clientsQuantity == 3) {
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
				//client 2
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[1].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
				//client 3
		    	clientCashier3.setVisible(true);
		    	listClientCashier3.setVisible(true);
		    	listClientCashier3.setText(clients[2].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
		    	exitOrderScreen(event);
			}
			else if (clientsQuantity == 4) {
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
				//client 2
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[1].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
				//client 3
		    	clientCashier3.setVisible(true);
		    	listClientCashier3.setVisible(true);
		    	listClientCashier3.setText(clients[2].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
				//client 4
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[3].toStringGames2());
		    	client4.setVisible(false);
		    	clientList4.setVisible(false);
		    	
		    	exitOrderScreen(event);
			}
			else if (clientsQuantity == 5) {
				
				//client 1
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[0].toStringGames2());
		    	client1.setVisible(false);
		    	clientList1.setVisible(false);
		    	
				//client 2
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[1].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
				//client 3
		    	clientCashier3.setVisible(true);
		    	listClientCashier3.setVisible(true);
		    	listClientCashier3.setText(clients[2].toStringGames2());
		    	client2.setVisible(false);
		    	clientList2.setVisible(false);
		    	
				//client 4
		    	clientCashier1.setVisible(true);
		    	listClientCashier1.setVisible(true);
		    	listClientCashier1.setText(clients[3].toStringGames2());
		    	client4.setVisible(false);
		    	clientList4.setVisible(false);

				//client 5
		    	clientCashier2.setVisible(true);
		    	listClientCashier2.setVisible(true);
		    	listClientCashier2.setText(clients[4].toStringGames2());
		    	client5.setVisible(false);
		    	clientList5.setVisible(false);
		    	
		    	exitOrderScreen(event);
		    	
		    	clientCashier1.setVisible(false);
		    	listClientCashier1.setVisible(false);
			}
			break;
			
		default:
			break;
		}

	}
	//-------------------------------------------------------------------------------------------------------------
	
	//--------------------------------------ExitOrderScreen.fxml---------------------------------------------------
	@FXML
	public void exitOrderScreen(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ExitOrderScreen.fxml"));
		fxmlLoader.setController(this);  
		Parent loginScreen = fxmlLoader.load();
		mainPane.getChildren().clear();
    	mainPane.setTop(loginScreen);
    	
    	for(int i = clients.length; i>0; i--) {
			for(int j = 0; j<i-1; j++) {
				if(clients[j].getGamesQuantity() > clients[j+1].getGamesQuantity()) {
					Client temp = clients[j+1];
					clients[j+1] = clients[j];
					clients[j] = temp;
				}
			}
		}
    	if(cashiersQuantity == 1) {
    		switch (clientsQuantity) {
    		case 1:
    				clientList1.setText(clients[0].toStringGames());
    				client2.setVisible(false);
    				clientList2.setVisible(false);
    				client3.setVisible(false);
    				clientList3.setVisible(false);
    				client4.setVisible(false);
    				clientList4.setVisible(false);
    				client5.setVisible(false);
    				clientList5.setVisible(false);
    			break;
    		case 2:
    				clientList1.setText(clients[0].toStringGames());
    				clientList2.setText(clients[1].toStringGames());
    				client3.setVisible(false);
    				clientList3.setVisible(false);
    				client4.setVisible(false);
    				clientList4.setVisible(false);
    				client5.setVisible(false);
    				clientList5.setVisible(false);
    			break;
    		case 3:
    				clientList1.setText(clients[0].toStringGames());
    				clientList2.setText(clients[1].toStringGames());
    				clientList3.setText(clients[2].toStringGames());
    				client4.setVisible(false);
    				clientList4.setVisible(false);
    				client5.setVisible(false);
    				clientList5.setVisible(false);
    			break;
    		case 4:
    				clientList1.setText(clients[0].toStringGames());
    				clientList2.setText(clients[1].toStringGames());
    				clientList3.setText(clients[2].toStringGames());
    				clientList4.setText(clients[3].toStringGames());
    				client5.setVisible(false);
    				clientList5.setVisible(false);
    			break;
    		case 5:
    				clientList1.setText(clients[0].toStringGames());
    				clientList2.setText(clients[1].toStringGames());
    				clientList3.setText(clients[2].toStringGames());
    				clientList4.setText(clients[3].toStringGames());
    				clientList5.setText(clients[4].toStringGames());
    			break;		
    		default:
    		}
    	}
    	else if (cashiersQuantity == 3) {
			clientsActions(clientsQuantity, clientsGames, 3);
    		switch (clientsQuantity) {
    		case 1:
    				clientList1.setText(clients[0].toStringGames());
    				client2.setVisible(false);
    				clientList2.setVisible(false);
    				client3.setVisible(false);
    				clientList3.setVisible(false);
    				client4.setVisible(false);
    				clientList4.setVisible(false);
    				client5.setVisible(false);
    				clientList5.setVisible(false);
    			break;
    		case 2:
    				clientList1.setText(clients[0].toStringGames());
    				clientList2.setText(clients[1].toStringGames());
    				client3.setVisible(false);
    				clientList3.setVisible(false);
    				client4.setVisible(false);
    				clientList4.setVisible(false);
    				client5.setVisible(false);
    				clientList5.setVisible(false);
    			break;
    		case 3:
    				clientList1.setText(clients[0].toStringGames());
    				clientList2.setText(clients[1].toStringGames());
    				clientList3.setText(clients[2].toStringGames());
    				client4.setVisible(false);
    				clientList4.setVisible(false);
    				client5.setVisible(false);
    				clientList5.setVisible(false);
    			break;
    		case 4:
    				clientList1.setText(clients[0].toStringGames());
    				clientList2.setText(clients[1].toStringGames());
    				clientList3.setText(clients[2].toStringGames());
    				clientList4.setText(clients[3].toStringGames());
    				client5.setVisible(false);
    				clientList5.setVisible(false);
    			break;
    		case 5:
    				clientList1.setText(clients[0].toStringGames());
    				clientList2.setText(clients[1].toStringGames());
    				clientList3.setText(clients[2].toStringGames());
    				clientList4.setText(clients[3].toStringGames());
    				clientList5.setText(clients[4].toStringGames());
    			break;		
    		default:
    		}
//    		for (Client client : clients) {
//				System.out.println(client.getID());
//			}
    	}
	}
	//-------------------------------------------------------------------------------------------------------------
}
