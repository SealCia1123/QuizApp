package com.sealcia.services.exam;

import com.sealcia.pojo.Question;
import com.sealcia.services.question.BaseQuestionServices;
import com.sealcia.services.question.LimitQuestionServicesDecorator;
import com.sealcia.utils.Configs;
import java.sql.SQLException;
import java.util.List;

public class SpecificExamStrategy extends ExamStrategy {
    private int num;

    public SpecificExamStrategy(int num) {
        this.num = num;
    }
    
    @Override
    public List<Question> getQuestions() throws SQLException {
        BaseQuestionServices s = new LimitQuestionServicesDecorator(Configs.questionServices, this.num);
        return s.list();
    }
}
