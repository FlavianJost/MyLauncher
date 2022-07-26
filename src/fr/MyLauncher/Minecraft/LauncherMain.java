package fr.MyLauncher.Minecraft;

import java.io.File;

import fr.MyLauncher.Minecraft.Account.LauncherAccount;
import fr.MyLauncher.Minecraft.Account.LauncherLogin;
import fr.MyLauncher.Minecraft.Menu.LauncherPanel;
import fr.MyLauncher.Minecraft.update.LauncherConstant;
import fr.RetroCraftPvP.MyLauncherLib.AppUpdater;
import fr.trxyy.alternative.alternative_api.GameConnect;
import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.GameFolder;
import fr.trxyy.alternative.alternative_api.GameForge;
import fr.trxyy.alternative.alternative_api.GameLinks;
import fr.trxyy.alternative.alternative_api.GameStyle;
import fr.trxyy.alternative.alternative_api.LauncherPreferences;
import fr.trxyy.alternative.alternative_api.maintenance.GameMaintenance;
import fr.trxyy.alternative.alternative_api.maintenance.Maintenance;
import fr.trxyy.alternative.alternative_api.utils.Forge;
import fr.trxyy.alternative.alternative_api.utils.Mover;
import fr.trxyy.alternative.alternative_api.utils.WindowStyle;
import fr.trxyy.alternative.alternative_api_ui.LauncherBackground;
import fr.trxyy.alternative.alternative_api_ui.LauncherPane;
import fr.trxyy.alternative.alternative_api_ui.base.AlternativeBase;
import fr.trxyy.alternative.alternative_api_ui.base.LauncherBase;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SuppressWarnings("static-access")
public class LauncherMain extends AlternativeBase{
	
	private GameFolder gamefolder = new GameFolder("MyLauncher");File f=new File(gamefolder.binDir,"auth_infos.json");
	private LauncherPreferences launcherpreferences = new LauncherPreferences("My Launcher v1.0", 950, 600, Mover.MOVE);
	private GameLinks gameLinks = new GameLinks("http://localhost/Launcher/", "1.10.2.json");
	private GameForge gameforge = new GameForge(Forge.FML_CLIENT,"1.10.2","12.18.3.2511","20210115.111550");
	private GameEngine gameEngine = new GameEngine(gamefolder, gameLinks, launcherpreferences, GameStyle.FORGE_1_8_TO_1_12_2,gameforge);
	private GameMaintenance gameMaintenance = new GameMaintenance(Maintenance.USE, gameEngine);
	//public GameConnect gameConnect = new GameConnect("Address du server ", "Port");
	
	
	
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		gameEngine.reg(gameMaintenance);//this.gameEngine.reg(gameConnect);
		LauncherBase launcher = new LauncherBase(primaryStage, scene, StageStyle.TRANSPARENT, gameEngine);
		launcher.setIconImage(primaryStage, "favicon.png");
	}
	
	private Parent createContent() {
		new AppUpdater(LauncherConstant.LAUNCHER_VERSION,LauncherConstant.API_VERSION);
		LauncherPane contentPane = new LauncherPane(gameEngine, 5, WindowStyle.TRANSPARENT);
		new LauncherBackground(gameEngine, "background.mp4", contentPane);
		@SuppressWarnings("unused")
		boolean login=f.exists();
	
		new LauncherAccount(contentPane, gameEngine, gamefolder, f);
		
		return contentPane;
	}
	 
	public static void main(String[] args) {
		Application.launch(args);
	}
}
