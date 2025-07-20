package com.sealcia.quizapp;

import com.sealcia.pojo.User;
import com.sealcia.utils.Configs;
import com.sealcia.utils.MyAlert;
import com.sealcia.utils.SessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML TextField txtUsername;
    @FXML TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void handleLogin(ActionEvent event) throws SQLException {
        String username = this.txtUsername.getText();
        String password = this.txtPassword.getText();

        User user = Configs.loginServices.login(username, password);
        if (user == null) {
            MyAlert.getInstance().showMsg("Username hoac Password khong dung", AlertType.WARNING);
        } else {
            MyAlert.getInstance().showMsg("Dang nhap thanh cong", AlertType.INFORMATION);
            SessionManager.getIntance().setUser(user);
            Configs.currentUser = user;
        }
    }
}
