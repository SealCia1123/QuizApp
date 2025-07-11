package com.sealcia.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private int id;
    private String name;

    public Category(int id) {
        this.id = id;
    }
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
