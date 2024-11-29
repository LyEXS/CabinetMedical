module group.cabinetmedical {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	 opens group.cabinetmedical.Objects to javafx.base;  // Permet à javafx.base d'accéder à votre package
	    exports group.cabinetmedical;
}