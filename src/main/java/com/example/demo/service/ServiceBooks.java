package com.example.demo.service;

import com.example.demo.persist.BooksEntity;
import com.example.demo.repo.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceBooks {

    private final BooksRepository booksRepository;


    public ServiceBooks(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<BooksEntity> findAll(){
        return booksRepository.findAll();
    }

    public Optional<BooksEntity> findById(Long id){
        return booksRepository.findById(id);
    }
}
