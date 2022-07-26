package fr.MyLauncher.Minecraft.Account;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.MyLauncher.Minecraft.Menu.LauncherPanel;
import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.GameFolder;
import fr.trxyy.alternative.alternative_api.utils.FontLoader;
import fr.trxyy.alternative.alternative_api.utils.config.LauncherConfig;
import fr.trxyy.alternative.alternative_api_ui.LauncherAlert;
import fr.trxyy.alternative.alternative_api_ui.base.IScreen;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherButton;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherImage;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherLabel;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherPasswordField;
import fr.trxyy.alternative.alternative_api_ui.components.LauncherTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LauncherLogin extends IScreen {

	
	private LauncherButton closeButton,reduceButton;
	public LauncherConfig config;
	private GameEngine gameEngine;
	/** LOGIN */
	private LauncherTextField usernameField;
	private LauncherPasswordField passwordField;
	private LauncherButton loginButton,inscriptionButton;
	private LauncherLabel inscriptionLabel;
	
	public String username="",Email="",pwd="";
	private Font customFont = FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", 18F);

	public LauncherLogin(Pane root, final GameEngine engine, GameFolder gamefolder,File f) {
		
		this.gameEngine = engine;
		this.config = new LauncherConfig(engine);
		this.config.loadConfiguration();
		
		this.drawRect(root, 0, 0, gameEngine.getWidth(), gameEngine.getHeight(), Color.rgb(255, 255, 255, 0.10));
		/** ===================== RECTANGLE NOIR EN BAS ===================== */
		this.drawRect(root, 0, engine.getHeight() - 110, engine.getWidth(), 300, Color.rgb(0, 0, 0, 0.4));
		this.closeButton = new LauncherButton(root);
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
		this.reduceButton = new LauncherButton(root);
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
		this.usernameField = new LauncherTextField(root);
		this.usernameField.setBounds(this.gameEngine.getWidth() - 540, this.gameEngine.getHeight() - 105, 220, 20);
		this.setFontSize(14.0F);
		this.usernameField.setFont(this.customFont);
		this.usernameField.addStyle("-fx-background-color: rgb(230, 230, 230);");
		this.usernameField.addStyle("-fx-text-fill: black;");
		this.usernameField.addStyle("-fx-border-radius: 0 0 0 0;");
		this.usernameField.addStyle("-fx-background-radius: 0 0 0 0;");
		this.usernameField.setVoidText("Addresse Mail");
		/** ===================== CASE MOT DE PASSE ===================== */
		this.passwordField = new LauncherPasswordField(root);
		this.passwordField.setBounds(this.gameEngine.getWidth() - 540, this.gameEngine.getHeight() - 70, 220, 20);
		this.setFontSize(14.0F);
		this.passwordField.setFont(this.customFont);
		this.passwordField.addStyle("-fx-background-color: rgb(230, 230, 230);");
		this.passwordField.addStyle("-fx-text-fill: black;");
		this.passwordField.addStyle("-fx-border-radius: 0 0 0 0;");
		this.passwordField.addStyle("-fx-background-radius: 0 0 0 0;");
		this.passwordField.setVoidText("Mot de passe");
		
		/** ===================== BOUTON DE CONNEXION ===================== */
		this.loginButton = new LauncherButton("Connexion", root);
		this.setFontSize(12.5F);
		this.loginButton.setFont(this.customFont);
		this.loginButton.setBounds(this.gameEngine.getWidth() - 300, this.gameEngine.getHeight() - 85, 90, 20);
		this.loginButton.addStyle("-fx-background-color: rgb(230, 230, 230);");
		this.loginButton.addStyle("-fx-text-fill: black;");
		this.loginButton.addStyle("-fx-border-radius: 0 0 0 0;");
		this.loginButton.addStyle("-fx-background-radius: 0 0 0 0;");
		this.loginButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				String url="jdbc:mariadb://localhost/mabase-de-donnée?serverTimezone=UTC",User="usr",password="mbp";
				try {
					usernameField.setVisible(false);passwordField.setVisible(false);loginButton.setVisible(false);inscriptionButton.setVisible(false);inscriptionLabel.setVisible(false);

					Connection con=DriverManager.getConnection(url,User,password);Statement statement=con.createStatement();
					ResultSet resultSet=statement.executeQuery("select * from account where Email='"+usernameField.getText().toString()+"'");
					
					while (resultSet.next()) {Email=resultSet.getString("Email");pwd=resultSet.getString("Mbp");
						username=resultSet.getString("MinSpeudo");}
						
					if(usernameField.getText()!=null && passwordField.getText()!=null ) {						
						if(Email==null && pwd==null ) {}else {@SuppressWarnings("unused")boolean test_file=f.exists();
								if(test_file==false) {config.updateValue("username", username);new LauncherPanel(root, engine, gamefolder);}}
						
					}else if(usernameField.getText()=="" && passwordField.getText()==""){
						new LauncherAlert("Auth Error","impossible de vous s'identifier car le compte n'existe pas");
						new LauncherAccount(root, engine, gamefolder, f);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		this.inscriptionLabel=new LauncherLabel(root);
		this.inscriptionLabel.setFont(this.customFont);
		this.inscriptionLabel.setBounds(this.gameEngine.getWidth()-285, this.gameEngine.getHeight()-50, 90, 20);
		this.inscriptionLabel.addStyle("-fx-text-fill: black;");
		this.inscriptionLabel.setText("S'inscrire");
		
		this.inscriptionButton=new LauncherButton(root);
		this.inscriptionButton.setFont(this.customFont);
		this.inscriptionButton.setBounds(this.gameEngine.getWidth()-285, this.gameEngine.getHeight()-50, 60, 0);
		this.inscriptionButton.setInvisible();
		this.inscriptionButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				usernameField.setVisible(false);
				passwordField.setVisible(false);
				loginButton.setVisible(false);
				inscriptionLabel.setVisible(false);
				inscriptionButton.setVisible(false);
				new LauncherAccount(root, engine, gamefolder, f);
			}
		});
		
	}
	
	private void setFontSize(float size) {
		this.customFont = FontLoader.loadFont("Comfortaa-Regular.ttf", "Comfortaa", size);
	}
	
}
