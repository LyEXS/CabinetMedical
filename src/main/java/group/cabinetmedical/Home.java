package group.cabinetmedical;

import javafx.scene.control.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {
	 @Override
	    public void start(Stage primaryStage) {
	        // Barre de navigation simple
	        Button btnPatients = new Button("Patients");
	        Button btnRendezVous = new Button("Rendez-vous");
	        Button btnSecretaires = new Button("Secrétaires");

	        // Conteneur pour les boutons
	        HBox navigationBar = new HBox(10, btnPatients, btnRendezVous, btnSecretaires);
	        navigationBar.setStyle("-fx-padding: 10px; -fx-background-color: #f0f0f0;");

	        // Conteneur pour le contenu principal
	        StackPane dynamicContent = new StackPane();

	        // Actions des boutons
	        btnPatients.setOnAction(e -> dynamicContent.getChildren().setAll(new PatientsPane()));
	        btnRendezVous.setOnAction(e -> dynamicContent.getChildren().setAll(new RendezVousPane()));
	        btnSecretaires.setOnAction(e -> dynamicContent.getChildren().setAll(new SecretairesPane()));

	        // Mise en page principale
	        BorderPane root = new BorderPane();
	        root.setTop(navigationBar); // La barre de navigation en haut
	        root.setCenter(dynamicContent); // Contenu dynamique au centre

	        // Configurer la scène principale
	        Scene mainScene = new Scene(root, 800, 600);

			primaryStage.setTitle("Gestion du Cabinet Médical");
	        primaryStage.setScene(mainScene);
	        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}