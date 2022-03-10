package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.example.App;
import org.example.domain.*;
import org.example.services.AnswerService;
import org.example.services.AppointmentsService;
import org.example.services.QuestionService;
import org.example.services.QuestionnaireService;
import org.example.utils.ContextHolder;
import org.example.utils.CustomUserData;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class QuestionnaireController implements Initializable {

    public Label stText;
    @FXML
    public GridPane grid;
    QuestionService questionService = new QuestionService();
    QuestionnaireService questionnaireService = new QuestionnaireService();

    Questionnaire questionnaireSelected = null;
    List<Question> questions = null;

    private AppointmentsService appointmentsService = new AppointmentsService();
    private AnswerService answerService = new AnswerService();

    /**
     * The questionnaire category selected by the patient and then it is displayed its question and possible answers
     *
     * @param url            offered by implementation method
     * @param resourceBundle offered by implementation method
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionnaireSelected = questionnaireService.getQuestionnaireByCategory(ContextHolder.getContext().getCategory());
        questions = questionService.getQuestionsByQuestionnaire(questionnaireSelected);
        int i = 0;

        for (Question q : questions) {
            Label label = new Label();
            label.setText(q.getQuestionText());
            label.setStyle("-fx-text-fill:Black;" + "-fx-font-size:1.1em;" +
                    "-fx-padding:10 8 8 20;");
            grid.add(label, 0, i);
            i++;
            if (q.getAnswerType() == AnswerType.FREE_TEXT) {
                TextField text = new TextField();
                text.setId(q.getId().toString());
                text.setUserData(new CustomUserData(q.getId(), null));
                grid.add(text, 0, i);
                i++;
            } else if (q.getAnswerType() == AnswerType.MULTIPLE_CHOICE) {
                ToggleGroup toggleGroup = new ToggleGroup();
                for (int j = 0; j < q.getMultipleChoiceAnswerPosibilities().size(); ++j) {
                    RadioButton radioButton1 = new RadioButton();
                    if (j == 0) {
                        radioButton1.setSelected(true);
                    }
                    radioButton1.setText(q.getMultipleChoiceAnswerPosibilities().get(j).getPossibleAnswer());
                    radioButton1.setToggleGroup(toggleGroup);
                    radioButton1.setId(q.getId().toString());
                    radioButton1.setUserData(new CustomUserData(q.getId(), q.getMultipleChoiceAnswerPosibilities().get(j).getPossibleAnswer()));
                    grid.add(radioButton1, 0, i);
                    i++;
                }
            } else if (q.getAnswerType() == AnswerType.TRUE_FALSE) {
                CheckBox checkBox = new CheckBox();
                checkBox.setText("Da");
                checkBox.setId(q.getId().toString());
                checkBox.setUserData(new CustomUserData(q.getId(), null));
                grid.add(checkBox, 0, i);
                i++;
            }
        }
    }

    /**
     * Creates an appointment with all its attributes (except id)
     * Search the answers of each question by its id and then adds them to the answer service
     *
     * @param actionEvent when the button is clicked
     * @throws Exception if it can't find matching answer id for the given question
     */
    public void btnCreateAppointment(ActionEvent actionEvent) throws Exception {
        List<Answer> answers = new ArrayList<>();
        for (Question question : questions) {
            Optional<Node> node = grid.getChildren().stream().filter(node1 -> question.getId().toString().equals(node1.getId())).findFirst();
            if (node.isEmpty()) {
                throw new Exception("Can't find node for question " + question.toString());
            } else {
                Node answerNode = node.get();
                if (question.getAnswerType() == AnswerType.MULTIPLE_CHOICE) {
                    ToggleGroup toggleGroup = ((RadioButton) answerNode).getToggleGroup();
                    RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();
                    CustomUserData userData = (CustomUserData) radioButton.getUserData();
                    answers.add(new Answer(userData.getQuestionId(), null, userData.getMultipleChoiceAnswer(), null));
                } else if (question.getAnswerType() == AnswerType.TRUE_FALSE) {
                    CheckBox checkBox = (CheckBox) answerNode;
                    answers.add(new Answer(Integer.valueOf(checkBox.getId()), null, null, checkBox.isSelected()));
                } else if (question.getAnswerType() == AnswerType.FREE_TEXT) {
                    TextField textField = (TextField) answerNode;
                    answers.add(new Answer(Integer.valueOf(textField.getId()), textField.getText(), null, null));
                }
            }
        }
        Appointment createdAppointment = new Appointment();
        createdAppointment.setDateTime(ContextHolder.getContext().getDateTime());
        createdAppointment.setPatientId(ContextHolder.getContext().getPatient().getId());
        createdAppointment.setCategory(ContextHolder.getContext().getCategory());
        createdAppointment = this.appointmentsService.addAppointment(createdAppointment);
        for (Answer answer : answers) {
            answer.setIdAppointment(createdAppointment.getId());
            this.answerService.addAnswer(answer);
        }
        App.setRoot("allAppointments");
    }
}
