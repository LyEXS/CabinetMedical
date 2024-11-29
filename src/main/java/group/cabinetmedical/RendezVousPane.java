package group.cabinetmedical;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class RendezVousPane extends StackPane {
    public RendezVousPane() {
        this.getChildren().add(new Text("Gestion des Rendez-vous"));
    }
}