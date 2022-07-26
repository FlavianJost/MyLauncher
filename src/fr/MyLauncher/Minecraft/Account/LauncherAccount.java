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
	
	private GameEngine gameEngine;
	private fr.RetroCraftPvP.MyLauncherLib.GameAuth auth;
	
	private GameAuth gameAuthentication;
	public String username="",uuId="",token="";

	public LauncherAccount(Pane root, final GameEngine engine, GameFolder gamefolder,File f) {
		
		this.gameEngine = engine;
		
		this.drawRect(root, 0, 0, gameEngine.getWidth(), gameEngine.getHeight(), Color.rgb(255, 255, 255, 0.10));
		/** ===================== RECTANGLE NOIR EN BAS ===================== */
		this.drawRect(root, 0, engine.getHeight() - 110, engine.getWidth(), 300, Color.rgb(0, 0, 0, 0.4));
		
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

}