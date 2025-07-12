package com.sealcia.services.question;

import com.sealcia.pojo.Question;

import java.sql.SQLException;
import java.util.List;

public class LimitQuestionServicesDecorator extends QuestionDecorator {
    private int num;

    public LimitQuestionServicesDecorator(BaseQuestionServices decorator, int num) {
        super(decorator);
        this.num = num;
    }

    @Override
    public List<Question> list() throws SQLException {
        List<Question> questions = super.list();

        for (var q : questions) {
            q.setChoices(this.getChoicesByQuestionId(q.getId()));
        }
        return questions;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " ORDER BY rand() LIMIT ?";
        params.add(num);
        return sql;
    }
}
