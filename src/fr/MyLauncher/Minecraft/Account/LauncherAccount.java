package fr.MyLauncher.Minecraft.Account;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import fr.MyLauncher.Minecraft.Menu.LauncherPanel;
import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.GameFolder;
import fr.trxyy.alternative.alternative_api.utils.FontLoader;
import fr.trxyy.alternative.alternative_api.utils.config.LauncherConfig;
import fr.trxyy.alternative.alternative_api_ui.LauncherPane;
import fr.trxyy.alternative.alternative_api_ui.base.IScreen;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherButton;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherImage;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherLabel;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherPasswordField;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherTextField;
import fr.trxyy.alternative.alternative_auth.account.AccountType;
import fr.trxyy.alternative.alternative_auth.base.GameAuth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LauncherAccount extends IScreen{
	
	/*private LauncherButton closeButton,reduceButton;
	public LauncherConfig config;
	*/private GameEngine gameEngine;
	private fr.RetroCraftPvP.MyLauncherLib.GameAuth auth;
	/** LOGIN */
	/*private LauncherTextField usernameField;
	private LauncherPasswordField passwordField;
	private LauncherButton loginButton,microsoftButton,LoginButtonb;
	private LauncherLabel LoginLabel;
	
	*/private GameAuth gameAuthentication;
	public String username="",uuId="",token="";/*
	private Font customFont = FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 18F);*/

	public LauncherAccount(Pane root, final GameEngine engine, GameFolder gamefolder,File f) {
		
		this.gameEngine = engine;
		/*this.config = new LauncherConfig(engine);
		this.config.loadConfiguration();*/
		
		this.drawRect(root, 0, 0, gameEngine.getWidth(), gameEngine.getHeight(), Color.rgb(255, 255, 255, 0.10));
		/** ===================== RECTANGLE NOIR EN BAS ===================== */
		this.drawRect(root, 0, engine.getHeight() - 110, engine.getWidth(), 300, Color.rgb(0, 0, 0, 0.4));
		/*this.closeButton = new LauncherButton(root);
		this.closeButton.setInvisible();
		this.closeButton.setBounds(gameEngine.getWidth() - 50, -3, 40, 20);
		LauncherImage closeImage = new LauncherImage(root, loadImage(gameEngine, "close.png"));
		closeImage.setSize(40, 20);
		this.closeButton.setGraphic(closeImage);
		this.closeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		/** ===================== BOUTON REDUIRE ===================== */
	/*	this.reduceButton = new LauncherButton(root);
		this.reduceButton.setInvisible();
		this.reduceButton.setBounds(gameEngine.getWidth() - 91, -3, 40, 20);
		LauncherImage reduceImage = new LauncherImage(root, loadImage(gameEngine, "reduce.png"));
		reduceImage.setSize(40, 20);
		this.reduceButton.setGraphic(reduceImage);
		this.reduceButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Stage stage = (Stage) ((LauncherButton) event.getSource()).getScene().getWindow();
				stage.setIconified(true);
			}
		});
		/** ===================== CASE PSEUDONYME ===================== */
	/*	this.usernameField = new LauncherTextField(root);
		this.usernameField.setBounds(this.gameEngine.getWidth() - 540, this.gameEngine.getHeight() - 105, 220, 20);
		this.setFontSize(14.0F);
		this.usernameField.setFont(this.customFont);
		this.usernameField.addStyle("-fx-background-color: rgb(230, 230, 230);");
		this.usernameField.addStyle("-fx-text-fill: black;");
		this.usernameField.addStyle("-fx-border-radius: 0 0 0 0;");
		this.usernameField.addStyle("-fx-background-radius: 0 0 0 0;");
		this.usernameField.setVoidText("Addresse Mail");
		/** ===================== CASE MOT DE PASSE ===================== */
	/*	this.passwordField = new LauncherPasswordField(root);
		this.passwordField.setBounds(this.gameEngine.getWidth() - 540, this.gameEngine.getHeight() - 70, 220, 20);
		this.setFontSize(14.0F);
		this.passwordField.setFont(this.customFont);
		this.passwordField.addStyle("-fx-background-color: rgb(230, 230, 230);");
		this.passwordField.addStyle("-fx-text-fill: black;");
		this.passwordField.addStyle("-fx-border-radius: 0 0 0 0;");
		this.passwordField.addStyle("-fx-background-radius: 0 0 0 0;");
		this.passwordField.setVoidText("Mot de passe");
		
		/** ===================== BOUTON DE CONNEXION ===================== */
	/*	this.loginButton = new LauncherButton("S'inscrire", root);
		this.setFontSize(12.5F);
		this.loginButton.setFont(this.customFont);
		this.loginButton.setBounds(this.gameEngine.getWidth() - 300, this.gameEngine.getHeight() - 85, 90, 20);
		this.loginButton.addStyle("-fx-background-color: rgb(230, 230, 230);");
		this.loginButton.addStyle("-fx-text-fill: black;");
		this.loginButton.addStyle("-fx-border-radius: 0 0 0 0;");
		this.loginButton.addStyle("-fx-background-radius: 0 0 0 0;");
		this.loginButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				usernameField.setVisible(false);
				passwordField.setVisible(false);
				microsoftButton.setVisible(false);
				String url="jdbc:mariadb://mysql-retrocraftpvp.alwaysdata.net/retrocraftpvp_launcher?serverTimezone=UTC";String User="271319";String password="37ha6PUr";
				try {
					Connection con=DriverManager.getConnection(url,User,password);
					Statement statement=con.createStatement();
					System.out.println(usernameField.getText());System.out.println(passwordField.getText());
					statement.execute("INSERT INTO `account`(`Email`, `Mbp`, `MinSpeudo`, `MinId`, `MinTken`) VALUES ('"+usernameField.getText()+"','"+passwordField.getText()+"','"+username+"','"+uuId+"','"+token+"')");
					@SuppressWarnings("unused")
					boolean test_file=f.exists();
					if(test_file==true) {
						config.updateValue("username", username);
						new LauncherPanel(root, engine, gamefolder);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		this.microsoftButton = new LauncherButton(root);
		LauncherImage mcaImage = new LauncherImage(root, loadImage(gameEngine, "microsoft.png"));
		mcaImage.setSize(20, 20);
		this.microsoftButton.setGraphic(mcaImage);
		this.microsoftButton.setBounds(this.gameEngine.getWidth() - 500, this.gameEngine.getHeight() - 35, 150, 20);
		this.microsoftButton.setText("Associer à Microsoft");
		this.microsoftButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				gameAuthentication=new GameAuth(AccountType.MICROSOFT);
				showMicrosoftAuth(gameAuthentication);
				if (gameAuthentication.isLogged()) {
					username=gameAuthentication.getSession().username;
					uuId=gameAuthentication.getSession().uuId;
					token=gameAuthentication.getSession().token;
				}
			}
		});
		
		this.LoginLabel=new LauncherLabel(root);
		this.LoginLabel.setFont(this.customFont);
		this.LoginLabel.setBounds(this.gameEngine.getWidth()-285, this.gameEngine.getHeight()-50, 90, 20);
		this.LoginLabel.addStyle("-fx-text-fill: black;");
		this.LoginLabel.setText("Se connecter");
		
		this.LoginButtonb=new LauncherButton(root);
		this.LoginButtonb.setFont(this.customFont);
		this.LoginButtonb.setBounds(this.gameEngine.getWidth()-285, this.gameEngine.getHeight()-50, 60, 0);
		this.LoginButtonb.setInvisible();
		this.LoginButtonb.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				usernameField.setVisible(false);
				passwordField.setVisible(false);
				microsoftButton.setVisible(false);
				loginButton.setVisible(false);
				LoginLabel.setVisible(false);
				LoginButtonb.setVisible(false);
				new LauncherLogin(root, engine, gamefolder, f);
			}
		});*/
		
		auth.RetrocraftAuth(root, engine, gamefolder, f);
		
		if(auth.lg==true) {
			new LauncherLogin(root, engine, gamefolder, f);
		}else if(auth.mb==true) {
			gameAuthentication=new GameAuth(AccountType.MICROSOFT);
			showMicrosoftAuth(gameAuthentication);
			if (gameAuthentication.isLogged()) {
				username=gameAuthentication.getSession().username;
				uuId=gameAuthentication.getSession().uuId;
				token=gameAuthentication.getSession().token;
			}
		}else if(auth.panel==true) {
			new LauncherPanel(root, engine, gamefolder);
		}
		
	}
	
	private void showMicrosoftAuth(GameAuth auth) {
		Scene scene = new Scene(createMicrosoftPanel(auth));
		Stage stage = new Stage();
		scene.setFill(Color.TRANSPARENT);
		stage.setResizable(false);
		stage.setTitle("Microsoft Authentication");
		stage.setWidth(500);
		stage.setHeight(600);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}
	
	private Parent createMicrosoftPanel(GameAuth auth) {
		LauncherPane contentPane = new LauncherPane(gameEngine);
		auth.connectMicrosoft(gameEngine, contentPane);
		return contentPane;
	}
	
	/*private void setFontSize(float size) {
		this.customFont = FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", size);
	}*/

}