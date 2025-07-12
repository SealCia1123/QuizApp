package com.sealcia.quizapp;

import com.sealcia.pojo.Category;
import com.sealcia.pojo.Level;
import com.sealcia.pojo.Question;
import com.sealcia.services.FlyweightFactory;
import com.sealcia.services.question.BaseQuestionServices;
import com.sealcia.services.question.CategoryQuestionServicesDecorator;
import com.sealcia.services.question.LevelQuestionServicesDecorator;
import com.sealcia.services.question.LimitQuestionServicesDecorator;
import com.sealcia.utils.Configs;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    @FXML private ComboBox<Category> cbSearchCates;
    @FXML private ComboBox<Level> cbSearchLevels;

    private List<Question> questions;
    private int currentQuestion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbSearchCates.setItems(
                    FXCollections.observableList(
                            FlyweightFactory.getData(Configs.categoryServices, "categories")));

            this.cbSearchLevels.setItems(
                    FXCollections.observableList(
                            FlyweightFactory.getData(Configs.levelServices, "levels")));
            /*
            this.cbSearchCates.setItems(FXCollections.observableList(Configs.categoryServices.list()));
            this.cbSearchLevels.setItems(FXCollections.observableList(Configs.levelServices.list()));
            */
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleStart(ActionEvent event) {
        try {
            BaseQuestionServices s = Configs.questionServices;

            Category isCbCateSearchChosen =
                    this.cbSearchCates.getSelectionModel().getSelectedItem();
            if (isCbCateSearchChosen != null) {
                s = new CategoryQuestionServicesDecorator(Configs.questionServices,
                                                            isCbCateSearchChosen);
            }

            Level isCbLevelSearchChosen = this.cbSearchLevels.getSelectionModel().getSelectedItem();

            if (isCbLevelSearchChosen != null) {
                s = new LevelQuestionServicesDecorator(Configs.questionServices,
                                                        isCbLevelSearchChosen);
            }

            s = new LimitQuestionServicesDecorator(Configs.questionServices,
                                                    Integer.parseInt(this.txtNum.getText()));
            this.questions = s.list();
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
