package com.sealcia.services;

import com.sealcia.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServices {
    public boolean login(String name, String password) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, name);
        stm.setString(2, password);
        ResultSet rs = stm.executeQuery();
        return rs.next();
    }
}
