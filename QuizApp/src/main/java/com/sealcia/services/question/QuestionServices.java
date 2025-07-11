package com.sealcia.services.question;

import com.sealcia.pojo.Choice;
import com.sealcia.pojo.Question;
import com.sealcia.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionServices extends BaseQuestionServices {

    @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM question WHERE 1=1";
    }
    
    /*
    public List<Question> getQuestions() throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();

        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question");

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }
    

    public List<Question> getQuestions(String kw) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();
        String sql = "SELECT * FROM question WHERE content like concat('%', ?, '%')";
        PreparedStatement stm = connection.prepareCall(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }
    */

    public List<Question> getQuestions(int num) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();

        PreparedStatement stm =
                connection.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        stm.setInt(1, num);
        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");

            Question q = new Question.Builder(id, content).addChoices(this.getChoicesByQuestionId(id)).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Choice> getChoicesByQuestionId(int id) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();

        PreparedStatement stm = connection.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            choices.add(new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct")));
        }
        return choices;
    }
}
