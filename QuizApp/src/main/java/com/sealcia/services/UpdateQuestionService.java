package com.sealcia.services;

import com.sealcia.pojo.Choice;
import com.sealcia.pojo.Question;
import com.sealcia.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateQuestionService {
    public boolean deleteQuestion(int questionId) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();
        PreparedStatement stm = connection.prepareCall("DELETE FROM question WHERE id=?");
        stm.setInt(1, questionId);
        return stm.executeUpdate() > 0;
    }

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());
        if (stm.executeUpdate() > 0) {
            int questionId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next()) {
                questionId = r.getInt(1);
            }
            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
            for (Choice c : q.getChoices()) {
                stm = conn.prepareCall(sql);
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);
                stm.executeUpdate();
            }
            conn.commit();
        } else {
            conn.rollback();
        }
    }
}
