package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.example.App;
import org.example.domain.Answer;
import org.example.domain.AnswerType;
import org.example.domain.Appointment;
import org.example.services.AnswerService;
import org.example.services.AppointmentsService;
import org.example.utils.ContextHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AllAppointmentsController implements Initializable {

    private AppointmentsService appointmentsService = new AppointmentsService();
    private ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private AnswerService answerService = new AnswerService();
    @FXML
    private TableView<Appointment> tblAppointments;
    @FXML
    private GridPane answers;

    public void btnswitchToMyProfile(MouseEvent mouseEvent) throws IOException {
        App.setRoot("profile");
    }

    public void btnswitchToCreateAppointment(MouseEvent mouseEvent) throws IOException {
        App.setRoot("secondary");
    }

    public void btnswitchToMyAppointments(MouseEvent mouseEvent) throws IOException {
        App.setRoot("allAppointments");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointments.addAll(appointmentsService.getAppointmentsForPatient(ContextHolder.getContext().getPatient().getId()));
        tblAppointments.setItems(appointments);

        tblAppointments.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleTableRowClick();
            }
        });
    }

    /**
     * When one appointment from the appointment table displayed is selected
     * Shows the questions and the answers chosen by the patient at each appointment created
     */
    private void handleTableRowClick() {
        try {
            Appointment appointment = tblAppointments.getSelectionModel().getSelectedItem();
            answers.getChildren().clear();
            int i = 0;
            for (Answer answer : answerService.getAnswersByAppointmentId(appointment.getId())) {
                Label question = new Label();
                question.setText(answer.getQuestion().getQuestionText());
                question.setStyle("-fx-text-fill:Black;" + "-fx-font-size:1.1em;" +
                        "-fx-padding:8 8 8 8;");
                answers.add(question, 0, i++);
                Label answerLabel = new Label();
                if (answer.getQuestion().getAnswerType() == AnswerType.FREE_TEXT) {
                    answerLabel.setText(answer.getFreeText());
                } else if (answer.getQuestion().getAnswerType() == AnswerType.MULTIPLE_CHOICE) {
                    answerLabel.setText(answer.getMultipleChoice());
                } else if (answer.getQuestion().getAnswerType() == AnswerType.TRUE_FALSE) {
                    answerLabel.setText(answer.getTrueFalse() ? "Da" : "Nu");
                }
                answerLabel.setStyle("-fx-text-fill:Black;" + "-fx-font-size:1.0em;" +
                        "-fx-padding:8 8 8 8;");
                answers.add(answerLabel, 0, i++);
                answers.add(new Label(), 0, i++);
            }
        } catch (Exception e) {

        }
    }
}