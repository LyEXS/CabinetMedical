package group.cabinetmedical.Objects;


import javafx.beans.property.SimpleStringProperty;


import javafx.beans.property.*;

public class Patient {
    private final IntegerProperty patientId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty dateOfBirth;
    private final StringProperty gender;
    private final StringProperty phoneNumber;
    private final StringProperty email;
    private final StringProperty address;
    private final StringProperty bloodType;
    private final StringProperty medicalHistory;
    private final StringProperty allergies;

    public Patient(int patientId, String firstName, String lastName, String dateOfBirth, String gender,
                   String phoneNumber, String email, String address, String bloodType,
                   String medicalHistory, String allergies) {
        this.patientId = new SimpleIntegerProperty(patientId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.gender = new SimpleStringProperty(gender);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.bloodType = new SimpleStringProperty(bloodType);
        this.medicalHistory = new SimpleStringProperty(medicalHistory);
        this.allergies = new SimpleStringProperty(allergies);
    }

    // Getters pour les propriétés
    public IntegerProperty patientIdProperty() {
        return patientId;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty bloodTypeProperty() {
        return bloodType;
    }

    public StringProperty medicalHistoryProperty() {
        return medicalHistory;
    }

    public StringProperty allergiesProperty() {
        return allergies;
    }

    // Getters pour les valeurs
    public int getPatientId() {
        return patientId.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getBloodType() {
        return bloodType.get();
    }

    public String getMedicalHistory() {
        return medicalHistory.get();
    }

    public String getAllergies() {
        return allergies.get();
    }
}


