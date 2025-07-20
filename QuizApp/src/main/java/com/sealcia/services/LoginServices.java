package com.sealcia.services;

import com.sealcia.pojo.User;
import com.sealcia.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServices {
    public User login(String name, String password) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, name);
        stm.setString(2, password);

        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String userName = rs.getString("name");
            String userPassword = rs.getString("password");
            return new User(id, userName, userPassword);
        }
        return null;
    }
}
