package com.example.demo.persist;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class BooksEntity {

    @Id
    @GeneratedValue
    private UUID uuid;
    private String title;
    private String author;
    private String type;

    public BooksEntity(UUID uuid, String title, String author, String type) {
        this.uuid = uuid;
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public BooksEntity() {
    }
}
