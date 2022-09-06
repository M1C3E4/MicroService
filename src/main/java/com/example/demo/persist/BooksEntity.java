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
    private Long id;
    private String title;
    private String author;
    private String type;

    public BooksEntity(Long id, String title, String author, String type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public BooksEntity() {
    }
}
