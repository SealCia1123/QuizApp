package com.sealcia.services;

import com.sealcia.pojo.Category;
import com.sealcia.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseServices<T> {
    public abstract PreparedStatement getStatement(Connection conn) throws SQLException;

    public abstract List<T> getResults(ResultSet rs) throws SQLException;

    public List<T> list() throws SQLException {
        List<Category> cates = new ArrayList<>();

        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement stm = this.getStatement(conn);
        return this.getResults(stm.executeQuery());
    }
}
