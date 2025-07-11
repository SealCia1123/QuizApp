package com.sealcia.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Level {
    private int id;
    private String name;
    private String note;

    public Level(int id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }
    
    public Level(int id) {
        this.id = id;
    }
    
    public Level(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
