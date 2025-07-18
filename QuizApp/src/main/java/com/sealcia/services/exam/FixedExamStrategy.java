package com.sealcia.services.exam;

import com.sealcia.pojo.Question;
import com.sealcia.services.question.BaseQuestionServices;
import com.sealcia.services.question.LevelQuestionServicesDecorator;
import com.sealcia.services.question.LimitQuestionServicesDecorator;
import com.sealcia.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FixedExamStrategy extends ExamStrategy {

    @Override
    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < Configs.RATES.length; i++) {
            BaseQuestionServices s =
                    new LimitQuestionServicesDecorator(
                            new LevelQuestionServicesDecorator(Configs.questionServices, i + 1),
                                (int)(Configs.RATES[i] * Configs.NUM_OF_QUESTIONS));
            questions.addAll(s.list());
        }
        
        return questions;
    }
    
}
