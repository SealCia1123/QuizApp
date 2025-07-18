package com.sealcia.services.exam;

import com.sealcia.pojo.Exam;
import com.sealcia.pojo.Question;
import com.sealcia.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class ExamStrategy {
    public abstract List<Question> getQuestions() throws SQLException;
    
    public void saveExam(List<Question> questions) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        
        Exam exam = new Exam(questions);
        
        String sql = "INSERT INTO exam(title, created_date) VALUES(?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, exam.getTitle());
        stm.setString(2, exam.getCreatedDate().toString());
        
        if (stm.executeUpdate() > 0) {
            int examId = -1;
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                examId = rs.getInt(1);
            }
            
            sql = "INSERT INTO exam_question(exam_id, question_id) VALUES(?, ?)";
            stm = conn.prepareCall(sql);
            
            for (var q : exam.getQuestions()) {
                stm.setInt(1, examId);
                stm.setInt(2, q.getId());
            }
            stm.executeUpdate();
        }
    }
}
