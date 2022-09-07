package com.example.demo.rest;

import com.example.demo.persist.BooksEntity;
import com.example.demo.repo.BooksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
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
        if(listBooks.isEmpty()){
            log.info("Baza danych jest pusta");
        }
            return listBooks;
    }

    @GetMapping("/byId/{id}")
    public Optional<BooksEntity> pullBookById(@PathVariable Long id){
        var book = booksRepository.findById(id);
        if(booksRepository.findById(id).isEmpty()){
            log.info("W bazie danych nie ma rekordu o id =" + id);
        }
        return book;
    }

    @PostMapping("/addBook")
    public BooksEntity addBook (@RequestBody BooksEntity booksEntity){
        var addedBook = booksRepository.save(booksEntity);
        return addedBook;
    }
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        BooksEntity booksEntity = new BooksEntity(1l, "Król Lew", "WaltDisney", "Dla dzieci");
        saveBooks(booksEntity);
        BooksEntity booksEntity1 = new BooksEntity(2l, "królewna śnieżka", "WaltDisney", "Dla młodzieży");
        saveBooks(booksEntity1);
        BooksEntity booksEntity2 = new BooksEntity(3l, "smerfy", "gsfsf", "dla bobasów");
        saveBooks(booksEntity2);
    }

    private BooksEntity saveBooks(BooksEntity booksEntity){
       return booksRepository.save(booksEntity);
    }


}
