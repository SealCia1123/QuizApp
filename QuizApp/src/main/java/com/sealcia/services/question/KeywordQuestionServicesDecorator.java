package com.sealcia.services.question;

import java.util.List;

public class KeywordQuestionServicesDecorator extends QuestionDecorator {
    private String kw;

    public KeywordQuestionServicesDecorator(BaseQuestionServices decorator, String kw) {
        super(decorator);
        this.kw = kw;
    }
    
    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND content like concat('%', ?, '%')";
        params.add(kw);
        return sql;
    }
}
