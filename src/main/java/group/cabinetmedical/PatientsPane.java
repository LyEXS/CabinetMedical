package group.cabinetmedical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import group.cabinetmedical.Objects.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientsPane extends BorderPane {
    private TableView<Patient> table;

    public PatientsPane() {
        this.getStylesheets().add(getClass().getResource("/Styles.css").toExternalForm());
        // Boutons principaux
        Button afficherLesAllergies = new Button("Afficher Les Allergies");
        Button afficherHistoriqueMedical = new Button("Afficher L'historique médical");

        // Boutons d'action sur les patients
        Button ajouterPatient = new Button("Ajouter Patient");
        Button modifierPatient = new Button("Modifier Patient");
        Button supprimerPatient = new Button("Supprimer Patient");

        // Créer la table
        table = new TableView<>();
        table.setPrefSize(800, 600);
        
        // Définir les colonnes
        TableColumn<Patient, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        TableColumn<Patient, String> colFirstName = new TableColumn<>("Prénom");
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Patient, String> colLastName = new TableColumn<>("Nom");
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Patient, String> colGender = new TableColumn<>("Genre");
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Patient, String> colPhone = new TableColumn<>("Téléphone");
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Patient, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Ajouter les colonnes à la table
        table.getColumns().addAll(colId, colFirstName, colLastName, colGender, colPhone, colEmail);

        // Charger les données
        table.setItems(loadPatients());

        // Ajouter la table au panneau
        this.setCenter(table);

        // Créer une HBox pour les boutons
        HBox buttonsPane = new HBox(10); // Espacement de 10px entre les boutons
        buttonsPane.setAlignment(Pos.CENTER); // Centrer les boutons
        buttonsPane.setPadding(new Insets(10, 10, 10, 10)); // Marges autour de la HBox
        buttonsPane.getChildren().addAll(
            afficherHistoriqueMedical,
            afficherLesAllergies,
            ajouterPatient,
            modifierPatient,
            supprimerPatient
        );

        // Ajouter la HBox en bas
        this.setBottom(buttonsPane);

        // Ajouter les actions pour les boutons
        ajouterPatient.setOnAction(e -> ajouterPatient());
        modifierPatient.setOnAction(e -> modifierPatient());
        supprimerPatient.setOnAction(e -> supprimerPatient());
        afficherLesAllergies.setOnAction(e -> afficherAllergies());
        afficherHistoriqueMedical.setOnAction(e -> afficherHistoriqueMedical());
    }

    // Charger les patients depuis la base de données
    private ObservableList<Patient> loadPatients() {
        ObservableList<Patient> patients = FXCollections.observableArrayList();
        String url = "jdbc:mysql://localhost:3306/cabinetmedical";
        String user = "root";
        String password = ""; // Remplacez par votre mot de passe MySQL

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM patients";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                patients.add(new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("date_of_birth"),
                        rs.getString("gender"),
                        rs.getString("phone_number"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("blood_type"),
                        rs.getString("medical_history"),
                        rs.getString("allergies")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return patients;
    }

    private void ajouterPatient() {
        Stage stage = new Stage();
        stage.setTitle("Ajouter un Patient");
        stage.setHeight(450);

        // Formulaire pour ajouter un patient
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Prénom");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Nom");

        // DatePicker pour la date de naissance
        DatePicker dateOfBirthPicker = new DatePicker();
        dateOfBirthPicker.setPromptText("Date de naissance");

        // RadioButton pour le genre
        ToggleGroup genderGroup = new ToggleGroup();
        
        RadioButton maleRadioButton = new RadioButton("Male");
        maleRadioButton.setToggleGroup(genderGroup);

        RadioButton femaleRadioButton = new RadioButton("Female");
        femaleRadioButton.setToggleGroup(genderGroup);

        // Par défaut, on sélectionne "Homme"
        maleRadioButton.setSelected(true);

        // Champ téléphone
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Téléphone");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField addressField = new TextField();
        addressField.setPromptText("Adresse");

        

        TextField medicalHistoryField = new TextField();
        medicalHistoryField.setPromptText("Antécédents médicaux");

        TextField allergiesField = new TextField();
        allergiesField.setPromptText("Allergies");
        
        ComboBox<String> bloodTypeComboBox = new ComboBox<>();
        bloodTypeComboBox.setPromptText("Groupe sanguin");

        // Set the possible values for the blood type
        bloodTypeComboBox.getItems().addAll("A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-");

        Button saveButton = new Button("Ajouter");
        saveButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String dateOfBirth = dateOfBirthPicker.getValue() != null ? dateOfBirthPicker.getValue().toString() : "";
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String bloodType = bloodTypeComboBox.getValue();
            String medicalHistory = medicalHistoryField.getText();
            String allergies = allergiesField.getText();

           
            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
                System.out.println("Tous les champs obligatoires doivent être remplis.");
                return;
            }

            // Ajout du patient dans la base de données
            String url = "jdbc:mysql://localhost:3306/cabinetmedical";
            String user = "root";
            String password = ""; // Remplacez par votre mot de passe MySQL
            String query = "INSERT INTO patients (first_name, last_name, date_of_birth, gender, phone_number, email, address, blood_type, medical_history, allergies) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection con = DriverManager.getConnection(url, user, password)) {
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1, firstName);
                st.setString(2, lastName);
                st.setString(3, dateOfBirth);
                st.setString(4, gender);
                st.setString(5, phoneNumber);
                st.setString(6, email);
                st.setString(7, address);
                st.setString(8, bloodType);
                st.setString(9, medicalHistory);
                st.setString(10, allergies);

                int rowsAffected = st.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Patient ajouté : " + firstName + " " + lastName);
                } else {
                    System.out.println("Erreur lors de l'ajout du patient.");
                }
                table.refresh();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // Fermer le dialogue après l'ajout
            stage.close();
        });

        layout.getChildren().addAll(
        	    firstNameField, lastNameField, dateOfBirthPicker, maleRadioButton, femaleRadioButton, phoneNumberField,
        	    emailField, addressField, bloodTypeComboBox, medicalHistoryField, allergiesField, saveButton);

        Scene scene = new Scene(layout, 300, 400);
        stage.setScene(scene);
        stage.show();
    }


    // Modifier le patient sélectionné
    private void modifierPatient() {
        Patient selectedPatient = table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            Stage stage = new Stage();
            stage.setTitle("Modifier le Patient");

            // Formulaire pour modifier le patient
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));

            // Champs du formulaire pré-remplis avec les données existantes
            TextField firstNameField = new TextField(selectedPatient.getFirstName());
            firstNameField.setPromptText("Prénom");

            TextField lastNameField = new TextField(selectedPatient.getLastName());
            lastNameField.setPromptText("Nom");

            TextField phoneNumberField = new TextField(selectedPatient.getPhoneNumber());
            phoneNumberField.setPromptText("Téléphone");

            TextField emailField = new TextField(selectedPatient.getEmail());
            emailField.setPromptText("Email");

            // Autres champs à ajouter pour la mise à jour (adresse, antécédents médicaux, etc.)
            TextField addressField = new TextField(selectedPatient.getAddress());
            addressField.setPromptText("Adresse");

            TextField medicalHistoryField = new TextField(selectedPatient.getMedicalHistory());
            medicalHistoryField.setPromptText("Antécédents médicaux");

            TextField allergiesField = new TextField(selectedPatient.getAllergies());
            allergiesField.setPromptText("Allergies");

            ComboBox<String> bloodTypeComboBox = new ComboBox<>();
            bloodTypeComboBox.setPromptText("Groupe sanguin");
            bloodTypeComboBox.getItems().addAll("A+", "B+", "AB+", "O+", "A-", "B-", "AB-", "O-");
            bloodTypeComboBox.setValue(selectedPatient.getBloodType()); // Sélectionner la valeur actuelle

            // Button pour enregistrer les modifications
            Button saveButton = new Button("Modifier");
            saveButton.setOnAction(e -> {
                // Récupérer les nouvelles valeurs depuis les champs
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                String medicalHistory = medicalHistoryField.getText();
                String allergies = allergiesField.getText();
                String bloodType = bloodTypeComboBox.getValue();

                String query = "UPDATE patients SET first_name = ?, last_name = ?, date_of_birth = ?, gender = ?, " +
                               "phone_number = ?, email = ?, address = ?, blood_type = ?, medical_history = ?, allergies = ? " +
                               "WHERE patient_id = ?";

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinetmedical", "root", "")) {
                    PreparedStatement st = con.prepareStatement(query);
                    st.setString(1, firstName);
                    st.setString(2, lastName);
                    // Vous devez peut-être inclure des valeurs pour `date_of_birth` et `gender`, ici on laisse comme exemple
                    st.setString(3, selectedPatient.getDateOfBirth());  // Récupérer la date de naissance si nécessaire
                    st.setString(4, selectedPatient.getGender());       // Récupérer le genre si nécessaire
                    st.setString(5, phoneNumber);
                    st.setString(6, email);
                    st.setString(7, address);
                    st.setString(8, bloodType);
                    st.setString(9, medicalHistory);
                    st.setString(10, allergies);
                    st.setInt(11, selectedPatient.getPatientId());  // Identifier le patient à modifier

                    int rowsAffected = st.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Patient modifié avec succès : " + firstName + " " + lastName);
                        table.refresh(); // Rafraîchir la table pour afficher les changements
                    } else {
                        System.out.println("Aucun patient modifié.");
                    }
                } catch (SQLException ex) {
                    System.out.println("Erreur lors de la modification du patient.");
                    ex.printStackTrace();
                }
                stage.close();  // Fermer le dialogue après modification
            });

            layout.getChildren().addAll(firstNameField, lastNameField, phoneNumberField, emailField, addressField,
                                        medicalHistoryField, allergiesField, bloodTypeComboBox, saveButton);

            Scene scene = new Scene(layout, 300, 350);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Aucun patient sélectionné pour la modification.");
        }
    }

    // Supprimer le patient sélectionné
    private void supprimerPatient() {
        Patient selectedPatient = table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            System.out.println("Suppression du patient : " + selectedPatient.getFirstName());

            String url = "jdbc:mysql://localhost:3306/cabinetmedical";
            String user = "root";
            String password = ""; // Remplacez par votre mot de passe MySQL
            String query = "DELETE FROM patients WHERE patient_id = ?";

            try (Connection con = DriverManager.getConnection(url, user, password)) {
                PreparedStatement st = con.prepareStatement(query);
                st.setInt(1, selectedPatient.getPatientId());

                int rowsAffected = st.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Patient supprimé avec succès.");
                    // Supprimer le patient de la vue
                    table.getItems().remove(selectedPatient);
                } else {
                    System.out.println("Aucun patient supprimé. Vérifiez l'identifiant.");
                }

            } catch (SQLException ex) {
                System.out.println("Erreur lors de la suppression du patient.");
                ex.printStackTrace();
            }

            // Actualiser la table (en cas de besoin)
            table.setItems(loadPatients());  // Recharger les patients depuis la base de données
        } else {
            System.out.println("Aucun patient sélectionné pour la suppression.");
        }
    }
    
    
    private void afficherAllergies() {
        Patient selectedPatient = table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            Stage stage = new Stage();
            stage.setTitle("Allergies du Patient");

            // Créer un VBox pour afficher les allergies
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));

            // Afficher les allergies du patient
            TextField allergiesField = new TextField(selectedPatient.getAllergies());
            allergiesField.setEditable(false);  // Ne pas permettre la modification
            allergiesField.setPromptText("Allergies");

            Button closeButton = new Button("Fermer");
            closeButton.setOnAction(event -> stage.close());

            layout.getChildren().addAll(allergiesField, closeButton);

            Scene scene = new Scene(layout, 300, 150);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Aucun patient sélectionné pour afficher les allergies.");
        }
    }

    private void afficherHistoriqueMedical() {
        Patient selectedPatient = table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            Stage stage = new Stage();
            stage.setTitle("Historique Médical du Patient");

            // Créer un VBox pour afficher l'historique médical
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));

            // Afficher l'historique médical du patient
            TextField medicalHistoryField = new TextField(selectedPatient.getMedicalHistory());
            medicalHistoryField.setEditable(false); 
            medicalHistoryField.setPromptText("Historique médical");

            Button closeButton = new Button("Fermer");
            closeButton.setOnAction(event -> stage.close());

            layout.getChildren().addAll(medicalHistoryField, closeButton);

            Scene scene = new Scene(layout, 300, 150);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Aucun patient sélectionné pour afficher l'historique médical.");
        }
}
}
