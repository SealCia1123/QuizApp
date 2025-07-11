package com.sealcia.services.question;

import com.sealcia.pojo.Question;
import com.sealcia.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public abstract class BaseQuestionServices {
    public abstract String getSQL(List<Object> params);
    
    public List<Question> list() throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();
        
        // ***********
        List<Object> params = new ArrayList<>();
        PreparedStatement stm = connection.prepareCall(this.getSQL(params));
        for (int i = 0; i < params.size(); i++) {
            stm.setObject(i + 1, params.get(i));
        }
        // ***********
        
        ResultSet rs = stm.executeQuery();
        
        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }
}
