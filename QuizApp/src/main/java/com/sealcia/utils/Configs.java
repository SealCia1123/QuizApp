package com.sealcia.utils;

import com.sealcia.services.CategoryServices;
import com.sealcia.services.LevelServices;
import com.sealcia.services.LoginServices;
import com.sealcia.services.UpdateQuestionServices;
import com.sealcia.services.question.QuestionServices;

public class Configs {
    public static final LevelServices levelServices = new LevelServices();
    public static QuestionServices questionServices = new QuestionServices();
    public static final UpdateQuestionServices updateQuestionServices =
            new UpdateQuestionServices();
    public static final CategoryServices categoryServices = new CategoryServices();

    public static final int NUM_OF_QUESTIONS = 10;
    public static final double[] RATES = {0.4, 0.4, 0.2};

    public static final LoginServices loginServices = new LoginServices();
}
