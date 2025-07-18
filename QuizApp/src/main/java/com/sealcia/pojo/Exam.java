package com.sealcia.pojo;

import java.time.LocalDateTime;
import java.util.List;

public class Exam {
    private int id;
    private String title;
    private LocalDateTime createdDate;
    private List<Question> questions;
    
    public Exam(List<Question> questions) {
        this.title = String.format("Exam - %s", LocalDateTime.now().toString());
        this.createdDate = LocalDateTime.now();
        this.questions = questions;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the createdDate
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions the questions to set
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    
}
