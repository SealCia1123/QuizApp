package com.sealcia.services.exam;

import com.sealcia.pojo.Question;
import java.sql.SQLException;
import java.util.List;

public abstract class ExamStrategy {
    public abstract List<Question> getQuestions() throws SQLException;
}
