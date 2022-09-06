package com.example.demo.repo;

import com.example.demo.persist.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, UUID> {
}
