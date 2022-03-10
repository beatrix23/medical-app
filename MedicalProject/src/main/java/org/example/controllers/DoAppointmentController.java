package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.example.App;
import org.example.domain.Questionnaire;
import org.example.services.QuestionnaireService;
import org.example.utils.ContextHolder;
import org.example.utils.DateTimePicker;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class DoAppointmentController implements Initializable {
    @FXML
    public GridPane content;
    private DateTimePicker dateTimePicker = new DateTimePicker();
    private QuestionnaireService questionnaireService = new QuestionnaireService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Questionnaire questionnaire = questionnaireService.getQuestionnaireByCategory(ContextHolder.getContext().getCategory());
        ContextHolder.getContext().setQuestionnaire(questionnaire);
        Label label = new Label();
        label.setText(" Pentru a crea o programare la secția " + questionnaire.getCategory().getName() + " apăsați pe iconița de calendar");
        label.setStyle("-fx-text-fill:LightSeaGreen;" + "-fx-font-size:1.5em;" +
                "-fx-padding:32 2 16 0;-fx-font-weight:bold");
        content.add(label, 0, 1);
        dateTimePicker.setStyle("-fx-font-size:1.6em;" +
                "-fx-padding:16 8 16 8;-fx-font-weight:bold;" + "-fx-gap-start-and-end: 3em;");
        content.add(dateTimePicker, 0, 2);
    }

    public void btnGoBack(ActionEvent actionEvent) throws IOException {
        App.setRoot("secondary");
    }

    /**
     * If the date and time is selected then it is set on the ContextHolder
     * Else is shown an alert message
     *
     * @param actionEvent
     * @throws IOException
     */
    public void btnDoAppointment(ActionEvent actionEvent) throws IOException {
        if (dateTimePicker.getDateTimeValue().isEqual(LocalDateTime.of(1990, 3, 3, 3, 3, 3, 3))) {
            Alert errorAlert = new Alert(Alert.AlertType.WARNING);
            errorAlert.setHeaderText("Atenție!");
            errorAlert.setContentText("Data programării nu a fost selectată!");
            errorAlert.showAndWait();
        } else {
            ContextHolder.getContext().setDateTime(dateTimePicker.getDateTimeValue());
            App.setRoot("questionnaire");
        }
    }
}
