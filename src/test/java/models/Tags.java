package models;

import lombok.Getter;

@Getter
public class Tags {
    private String id;
    private String name;

    public Tags(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
