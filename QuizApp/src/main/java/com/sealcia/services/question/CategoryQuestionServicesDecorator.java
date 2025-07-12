package com.sealcia.services.question;

import com.sealcia.pojo.Category;

import java.util.List;

public class CategoryQuestionServicesDecorator extends QuestionDecorator {
    private Category category;

    public CategoryQuestionServicesDecorator(BaseQuestionServices decorator, Category category) {
        super(decorator);
        this.category = category;
    }

    public CategoryQuestionServicesDecorator(BaseQuestionServices decorator, int cateId) {
        super(decorator);
        this.category = new Category(cateId);
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND category_id = ?";
        params.add(category.getId());
        return sql;
    }
}
