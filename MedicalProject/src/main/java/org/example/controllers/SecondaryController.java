package org.example.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.example.App;
import org.example.domain.QuestionnaireCategory;
import org.example.utils.ContextHolder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {

    @FXML
    public GridPane category;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label labelText = new Label();
        labelText.setText("Pentru a crea o programare selectați secția dorită");
        labelText.setFont(new Font("Arial", 16));
        labelText.setStyle("-fx-text-fill:CornflowerBlue;" + "-fx-font-size:1.9em;" +
                "-fx-padding:4 0 8 0;-fx-font-weight:bold");
        category.add(labelText, 0, 0);
        int i = 1;
        for (QuestionnaireCategory q : QuestionnaireCategory.values()) {
            Label label = new Label();
            label.setText(q.getName());
            label.setFont(new Font("Arial", 20));
            label.setStyle("-fx-text-fill:MediumPurple;" + "-fx-font-size:1.5em;" +
                    "-fx-padding:6 0 10 0;-fx-font-weight:bold");
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ContextHolder.getContext().setCategory(q);
                    try {
                        App.setRoot("DoAppointment");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            category.add(label, 0, i);
            i++;
        }
    }


    public void btnswitchToMyProfile(MouseEvent mouseEvent) throws IOException {
        App.setRoot("profile");
    }

    public void btnswitchToCreateAppointment(MouseEvent mouseEvent) throws IOException {
        App.setRoot("secondary");
    }

    public void btnswitchToMyAppointments(MouseEvent mouseEvent) throws IOException {
        App.setRoot("allAppointments");
    }
}