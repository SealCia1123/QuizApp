package com.sealcia.quizapp;

import com.sealcia.pojo.Choice;
import com.sealcia.pojo.Question;
import com.sealcia.services.exam.ExamStrategy;
import com.sealcia.services.exam.ExamType;
import com.sealcia.services.exam.FixedExamStrategy;
import com.sealcia.services.exam.SpecificExamStrategy;
import com.sealcia.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ExamController implements Initializable {
    @FXML private ComboBox<ExamType> cbExamTypes;
    @FXML private TextField txtNum;
    @FXML private ListView<Question> listViewQuestions;
    private List<Question> questions;
    private ExamStrategy s;
    private Map<Integer, Choice> results = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamType.values()));
        
        this.txtNum.setVisible(false);
        this.cbExamTypes.getSelectionModel().selectedItemProperty().addListener(event -> {
            if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamType.SPECIFIC) {
                this.txtNum.setVisible(true);
            } else {
                this.txtNum.setVisible(false);
            }
        });
    }
    
    public void handleStart(ActionEvent event) throws SQLException {
        if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamType.SPECIFIC) {
            s = new SpecificExamStrategy(Integer.parseInt(this.txtNum.getText()));
        } else {
            s = new FixedExamStrategy();
        }
        questions = s.getQuestions();
        this.listViewQuestions.setItems(FXCollections.observableList(questions));
        this.listViewQuestions.setCellFactory(params -> new ListCell<Question>() {
            @Override
            protected void updateItem(Question q, boolean empty) {
                super.updateItem(q, empty);
                
                if (q == null || empty == true) {
                    this.setGraphic(null);
                } else {
                    VBox v = new VBox(5);
                    v.setStyle("-fx-border-width: 3; -fx-border-color: gray; -fx-padding: 5;");
                    Text t = new Text(q.getContent());
                    v.getChildren().add(t);
                    
                    ToggleGroup toggle = new ToggleGroup();
                    for (var c : q.getChoices()) {
                        RadioButton r = new RadioButton(c.getContent());
                        r.setToggleGroup(toggle);
                        
                        // update UI
                        if (results.get(q.getId()) == c) {
                            r.setSelected(true);
                        }
                        
                        r.setOnAction(event -> {
                            results.put(q.getId(), c);
                        });
                        
                        v.getChildren().add(r);
                    }
                    
                    this.setGraphic(v);
                }
            }
        });
    }
    
    public void handleFinish(ActionEvent event) {
        if (!results.isEmpty()) {
            int count = 0;
            for (var c : results.values()) {
                if (c.isCorrect()) {
                    count++;
                }
            }
            MyAlert.getInstance().showMsg(
                    String.format("Bạn đã làm đúng %d/%d", count, questions.size()),
                    Alert.AlertType.INFORMATION);
        }
    }
    
    public void handleSave(ActionEvent event) {
        
    }
}
