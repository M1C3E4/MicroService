package com.example.demo.persist;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class BooksEntity {

    @Id
    @GeneratedValue
    private UUID uuid;
    private String title;
    private String author;
    private String type;
}
