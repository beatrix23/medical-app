package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.example.App;
import org.example.domain.Patient;
import org.example.services.PatientService;
import org.example.utils.ContextHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    public TextField txtName;
    public TextField txtAge;
    public TextField txtPhone;

    public GridPane userData;

    private PatientService patientService = new PatientService();

    public void btnswitchToMyProfile(MouseEvent mouseEvent) throws IOException {
        App.setRoot("profile");
    }

    public void btnswitchToCreateAppointment(MouseEvent mouseEvent) throws IOException {
        App.setRoot("secondary");
    }

    public void btnswitchToMyAppointments(MouseEvent mouseEvent) throws IOException {
        App.setRoot("allAppointments");
    }

    /**
     * Updates the patient info (name, age, phone number)
     *
     * @param actionEvent
     */
    public void btnUpdateProfile(ActionEvent actionEvent) {
        Patient patientToUpdate = new Patient();
        patientToUpdate.setId(ContextHolder.getContext().getPatient().getId());
        patientToUpdate.setEmail(ContextHolder.getContext().getPatient().getEmail());
        patientToUpdate.setName(txtName.getText());
        try {
            Integer age = Integer.valueOf(txtAge.getText());
            if (age.compareTo(0) == 1) {
                patientToUpdate.setAge(age);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Eroare");
                errorAlert.setContentText("Vârsta nu este setată corect!");
                errorAlert.showAndWait();
                return;
            }
        } catch (NumberFormatException n) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Eroare");
            errorAlert.setContentText("Vârsta nu este setată corect!");
            errorAlert.showAndWait();
            return;
        }

        patientToUpdate.setTelephone(txtPhone.getText());
        patientToUpdate = this.patientService.updatePatient(patientToUpdate);
        ContextHolder.getContext().setPatient(patientToUpdate);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ContextHolder.getContext().getPatient().getName() != null) {
            txtName.setText(ContextHolder.getContext().getPatient().getName());
        }
        if (ContextHolder.getContext().getPatient().getAge() != null) {
            txtAge.setText(ContextHolder.getContext().getPatient().getAge().toString());
        }
        if (ContextHolder.getContext().getPatient().getTelephone() != null) {
            txtPhone.setText(ContextHolder.getContext().getPatient().getTelephone());
        }
    }
}
