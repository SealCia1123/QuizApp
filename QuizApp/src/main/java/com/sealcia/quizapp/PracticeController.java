package com.sealcia.quizapp;

import com.sealcia.pojo.Question;
import com.sealcia.utils.Configs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PracticeController implements Initializable {
    @FXML private VBox vboxChoices;
    @FXML private Text txtContent;
    @FXML private TextField txtNum;
    @FXML private Text txtResult;

    private List<Question> questions;
    private int currentQuestion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void handleStart(ActionEvent event) {
        try {
            this.questions = Configs.questionServices.getQuestions(Integer.parseInt(this.txtNum.getText()));
            this.loadQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleNext(ActionEvent event) {
        if (this.currentQuestion < this.questions.size() - 1) {
            this.txtResult.setText("");
            this.currentQuestion++;
            this.loadQuestion();
        }
    }

    public void handleCheck(ActionEvent event) {
        Question q = this.questions.get(this.currentQuestion);
        for (int i = 0; i < q.getChoices().size(); i++) {
            if (q.getChoices().get(i).isCorrect()) {
                RadioButton rdoBtn = ((RadioButton) (this.vboxChoices.getChildren().get(i)));
                if (rdoBtn.isSelected()) {
                    this.txtResult.setText("Chúc mừng bạn đã làm đúng");
                    this.txtResult.getStyleClass().clear();
                    this.txtResult.getStyleClass().add("Correct");
                } else {
                    this.txtResult.setText("Bạn đã làm sai!!!");
                    this.txtResult.getStyleClass().clear();
                    this.txtResult.getStyleClass().add("Wrong");
                }
            }
        }
    }

    private void loadQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtContent.setText(q.getContent());

        this.vboxChoices.getChildren().clear();
        ToggleGroup toggleChoices = new ToggleGroup();
        for (var c : q.getChoices()) {
            RadioButton rdoChoice = new RadioButton(c.getContent());
            rdoChoice.setToggleGroup(toggleChoices);
            this.vboxChoices.getChildren().add(rdoChoice);
        }
    }
}
