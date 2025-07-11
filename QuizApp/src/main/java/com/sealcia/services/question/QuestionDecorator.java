package com.sealcia.services.question;

public abstract class QuestionDecorator extends BaseQuestionServices {
    protected BaseQuestionServices decorator;
    
    public QuestionDecorator(BaseQuestionServices decorator) {
        this.decorator = decorator;
    }
}
