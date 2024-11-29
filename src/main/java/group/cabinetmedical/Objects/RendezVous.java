package group.cabinetmedical.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RendezVous {	
	private final IntegerProperty IdRendezVous ;
	private final IntegerProperty IdPatient ;
	private final StringProperty DateRendezVous ;
	private final StringProperty HeureRendezVous ;
	private final StringProperty MotifRendezVous ;
	private final StringProperty StatutRendezVous ;
	public RendezVous(int IdRendezVous, int IdPatient, String DateRendezVous, String HeureRendezVous, String MotifRendezVous, String StatusRendezVous) {
			this.IdRendezVous = new SimpleIntegerProperty(IdRendezVous);
        this.IdPatient = new SimpleIntegerProperty(IdPatient);
        this.DateRendezVous = new SimpleStringProperty(DateRendezVous);
        this.HeureRendezVous = new SimpleStringProperty(HeureRendezVous);
        this.MotifRendezVous = new SimpleStringProperty(MotifRendezVous);
        this.StatutRendezVous = new SimpleStringProperty(StatusRendezVous);
	}
	
	
	
	
	
	
}
