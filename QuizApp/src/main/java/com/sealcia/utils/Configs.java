package com.sealcia.utils;

import com.sealcia.services.CategoryServices;
import com.sealcia.services.LevelServices;
import com.sealcia.services.question.QuestionServices;
import com.sealcia.services.UpdateQuestionService;

public class Configs {
    public static final LevelServices levelServices = new LevelServices();
    public static QuestionServices questionServices = new QuestionServices();
    public static final UpdateQuestionService updateQuestionServices = new UpdateQuestionService();
    public static final CategoryServices categoryServices = new CategoryServices();
}
