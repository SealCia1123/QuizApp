package com.sealcia.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Question {
    private int id;
    private String content;
    private String hint;
    private String image;
    private Category category;
    private Level level;
    private List<Choice> choices = new ArrayList<>();

    public Question(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.hint = builder.hint;
        this.image = builder.image;
        this.category = builder.category;
        this.level = builder.level;
        this.choices = builder.choices;
    }

    @Override
    public String toString() {
        return this.content;
    }
    
    public static class Builder {
        private int id;
        private String content;
        private String hint;
        private String image;
        private Category category;
        private Level level;
        private List<Choice> choices = new ArrayList<>();

        public Builder(String content, Category category, Level level) throws Exception {
            if (content.isEmpty() || category == null || level == null) {
                throw new Exception("Invalid data");
            }
            this.content = content;
            this.category = category;
            this.level = level;
        }

        public Builder(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public Builder addHint(String hint) {
            this.hint = hint;
            return this;
        }

        public Builder addImage(String image) {
            this.image = image;
            return this;
        }

        public Builder addChoice(Choice choice) {
            this.choices.add(choice);
            return this;
        }

        public Builder addChoices(List<Choice> choices) {
            this.choices.addAll(choices);
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
