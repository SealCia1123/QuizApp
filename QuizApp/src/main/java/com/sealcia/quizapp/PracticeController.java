package com.sealcia.quizapp;

import com.sealcia.pojo.Question;
import com.sealcia.utils.Configs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PracticeController implements Initializable {
    @FXML private VBox vboxQuestions;
    @FXML private Text txtContent;

    private List<Question> questions;
    private int currentQuestion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.questions = Configs.questionService.getQuestions(3);
            loadQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleStart() {}

    private void loadQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());

        this.vboxQuestions.getChildren().clear();
        ToggleGroup toggleChoices = new ToggleGroup();
        for (var c : q.getChoices()) {
            RadioButton rdoChoice = new RadioButton(c.getContent());
            rdoChoice.setToggleGroup(toggleChoices);
            this.vboxQuestions.getChildren().add(rdoChoice);
        }
    }
}
