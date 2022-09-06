package com.example.demo.rest;

import com.example.demo.persist.BooksEntity;
import com.example.demo.repo.BooksRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private BooksRepository booksRepository;

    public RestController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @GetMapping("/allBooks")
    public List<BooksEntity> pullAllBooks(){
        var listBooks = booksRepository.findAll();
        return listBooks;
    }

    @GetMapping("/byId")
    public Optional<BooksEntity> pullBookById(UUID uuid){
        var book = booksRepository.findById(uuid);
        return book;
    }

}
