package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.domain.Patient;
import org.example.services.PatientService;
import org.example.utils.ContextHolder;

import java.io.IOException;

public class PrimaryController {

    public TextField txtEmail;
    public PasswordField txtPassword;

    private PatientService patientService = new PatientService();

    /**
     * If the logIn method returns true then is called the find function and saved the patient on the ContextHolder
     * Then we switch the scene to secondary
     * If it returns false then we show an alert message
     *
     * @param actionEvent on click at the Log In button
     * @throws IOException if the scene can't be switched
     */

    public void btnLogIn(ActionEvent actionEvent) throws IOException {
        if (this.patientService.logIn(txtEmail.getText(), txtPassword.getText())) {
            ContextHolder.getContext().setPatient(this.patientService.findPatient(txtEmail.getText()));
            App.setRoot("profile");
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Eroare");
            errorAlert.setContentText("Email sau parola incorecta!");
            errorAlert.showAndWait();
        }
    }

    /**
     * The patient email and password are added in the database
     * If the add method returns a patient then it is saved in the ContextHolder
     * If the add method returns null then it is showed an error message
     *
     * @param actionEvent on clicking the Sign Up button
     * @throws IOException if the switch cannot be done
     */
    public void btnSignUp(ActionEvent actionEvent) throws IOException {
        Patient patientToSignUp = new Patient();
        patientToSignUp.setEmail(txtEmail.getText());
        patientToSignUp.setPassword(txtPassword.getText());
        patientToSignUp = patientService.addPatient(patientToSignUp);
        if (patientToSignUp != null) {
            ContextHolder.getContext().setPatient(patientToSignUp);
            App.setRoot("profile");
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Eroare");
            errorAlert.setContentText("Email-ul acesta este deja utilizat!");
            errorAlert.showAndWait();
        }
    }
}
