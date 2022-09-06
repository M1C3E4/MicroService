package com.example.demo.rest;

import com.example.demo.persist.BooksEntity;
import com.example.demo.repo.BooksRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

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
    public Optional<BooksEntity> pullBookById(@RequestParam UUID uuid){
        var book = booksRepository.findById(uuid);
        return book;
    }

    @PostMapping("/addBook")
    public BooksEntity addBook (@RequestBody BooksEntity booksEntity){
        var addedBook = booksRepository.save(booksEntity);
        return addedBook;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        BooksEntity booksEntity = new BooksEntity(UUID.randomUUID(), "Król Lew", "WaltDisney", "Dla dzieci");
        saveBooks(booksEntity);
        BooksEntity booksEntity1 = new BooksEntity(UUID.randomUUID(), "królewna śnieżka", "WaltDisney", "Dla młodzieży");
        saveBooks(booksEntity1);
        BooksEntity booksEntity2 = new BooksEntity(UUID.randomUUID(), "smerfy", "gsfsf", "dla bobasów");
        saveBooks(booksEntity2);
    }

    private BooksEntity saveBooks(BooksEntity booksEntity){
       return booksRepository.save(booksEntity);
    }


}
