package com.example.demo.persist;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

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

    public BooksEntity getBooksEntity (BooksEntity booksEntity){
        return booksEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooksEntity that = (BooksEntity) o;

        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        if (!author.equals(that.author)) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
