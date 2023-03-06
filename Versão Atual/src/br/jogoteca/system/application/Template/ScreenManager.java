package br.ufrpe.flight_system.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ScreenManager {
    private static ScreenManager instance;
    private Stage primaryStage;
    private Scene mainScene;
    private MainScreenController mainScreenController;
    
    private Scene addVooScene;
    private AddVooScreenController addVooScreenController;
    
    private Scene listaPassageirosScene;
    private ListaPassageirosPorVooScreenController listaPassageirosPorVooScreenController;
    
    private Scene addPassageirosScene;
    private AddPassageiroScreenController addPassageiroScreenController;
    
    private Scene emitirBilheteScene;
    private EmitirBilheteScreenController emitirBilheteScreenController;
    
    private ScreenManager() { 
        this.initialize(); 
    }
    
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }
    
    private void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            TabPane mainPane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/MainScreen.fxml").openStream());            
            this.mainScene = new Scene(mainPane); 
            this.mainScreenController = (MainScreenController) fxmlLoader.getController();
            
            fxmlLoader = new FXMLLoader();
            GridPane addVooPane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/AddVoo.fxml").openStream());            
            this.addVooScene = new Scene(addVooPane); 
            this.addVooScreenController = (AddVooScreenController) fxmlLoader.getController();
            
            fxmlLoader = new FXMLLoader();
            BorderPane listaPassageirosPane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/ListaPassageiros.fxml").openStream());            
            this.listaPassageirosScene = new Scene(listaPassageirosPane); 
            this.listaPassageirosPorVooScreenController = (ListaPassageirosPorVooScreenController) fxmlLoader.getController();
            
            fxmlLoader = new FXMLLoader();
            GridPane addPassageirosPane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/AddPassageiro.fxml").openStream());            
            this.addPassageirosScene = new Scene(addPassageirosPane); 
            this.addPassageiroScreenController = (AddPassageiroScreenController) fxmlLoader.getController();
            
            fxmlLoader = new FXMLLoader();
            BorderPane emitirBilhetePane = fxmlLoader.load(getClass()
                    .getResource("/br/ufrpe/flight_system/gui/EmitirBilhete.fxml").openStream());            
            this.emitirBilheteScene = new Scene(emitirBilhetePane); 
            this.emitirBilheteScreenController = (EmitirBilheteScreenController) fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public MainScreenController getMainScreenController() {
        return mainScreenController;
    }
    
    public Scene getAddVooScene() {
        return addVooScene;
    }

    public AddVooScreenController getAddVooScreenController() {
        return addVooScreenController;
    }
    
    public Scene getListaPassageirosScene() {
        return listaPassageirosScene;
    }

    public ListaPassageirosPorVooScreenController getListaPassageirosPorVooScreenController() {
        return listaPassageirosPorVooScreenController;
    }

    public Scene getAddPassageirosScene() {
        return addPassageirosScene;
    }

    public AddPassageiroScreenController getAddPassageiroScreenController() {
        return addPassageiroScreenController;
    }
    
    public Scene getEmitirBilheteScene() {
        return emitirBilheteScene;
    }

    public EmitirBilheteScreenController getEmitirBilheteScreenController() {
        return emitirBilheteScreenController;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
