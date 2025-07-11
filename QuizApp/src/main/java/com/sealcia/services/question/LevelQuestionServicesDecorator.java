package com.sealcia.services.question;

import com.sealcia.pojo.Level;
import java.util.List;

public class LevelQuestionServicesDecorator extends QuestionDecorator {
    private Level lv;

    public LevelQuestionServicesDecorator(BaseQuestionServices decorator, Level lv) {
        super(decorator);
        this.lv = lv;
    }
    
    public LevelQuestionServicesDecorator(BaseQuestionServices decorator, int lvId) {
        super(decorator);
        this.lv = new Level(lvId);
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND level_id = ?";
        params.add(lv.getId());
        return sql;
    }
}
