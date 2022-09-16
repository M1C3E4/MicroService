package com.example.demo.rest;

import com.example.demo.persist.BooksEntity;
import com.example.demo.repo.BooksRepository;
import com.example.demo.service.ServiceBooks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final BooksRepository booksRepository;
    private final ServiceBooks serviceBooks;

    public RestController(BooksRepository booksRepository, ServiceBooks serviceBooks) {
        this.booksRepository = booksRepository;
        this.serviceBooks = serviceBooks;
    }

    @GetMapping("/findAllBooks")
    public List<BooksEntity> pullAllBooks(){
            return serviceBooks.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<BooksEntity> pullBooksById(@PathVariable Long id){
        return serviceBooks.findById(id);
    }

    @PostMapping("/addBook")
    public BooksEntity save(@RequestBody BooksEntity booksEntity){
        return serviceBooks.add(booksEntity);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        BooksEntity booksEntity = new BooksEntity(1l, "Krol Lew", "WaltDisney", "Dla dzieci");
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
