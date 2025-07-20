package com.sealcia.quizapp;

import com.sealcia.utils.MyStage;
import com.sealcia.utils.SessionManager;
import com.sealcia.utils.theme.Theme;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML private ComboBox<Theme> cbThemes;
    @FXML private Text txtGreeting;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
        if (SessionManager.getIntance().getUser() == null) {
            this.txtGreeting.setText("Xin chào khách");
        } else {
            this.txtGreeting.setText("Xin chào ");
        }
    }

    public void handleQuestionManagement(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("question.fxml");
    }

    public void changeTheme(ActionEvent event) {
        this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
    }

    public void handlePractice(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("practice.fxml");
    }

    public void handleExam(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("exam.fxml");
    }

    public void handleLogin(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("login.fxml");
    }
}
