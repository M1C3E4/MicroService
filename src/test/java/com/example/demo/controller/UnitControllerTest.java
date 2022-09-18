package com.example.demo.controller;

import com.example.demo.persist.BooksEntity;
import com.example.demo.repo.BooksRepository;
import com.example.demo.service.ServiceBooks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ContextConfiguration(classes = {ObjectMapper.class, ServiceBooks.class, BooksEntity.class, MockMvc.class})
@AutoConfigureMockMvc
public class UnitControllerTest {

    @Test
    void contextLoad(){
    }

    private ObjectMapper objectMapper;
    private ServiceBooks serviceBooks;
    private BooksEntity booksEntity;
    private MockMvc mockMvc;

    @MockBean
    private BooksRepository booksRepository;

    @Test
    @DisplayName("http://localhost:9090/findById/{id} -> 200")
    public void pullBooksByIdShouldReturnStatus200() throws Exception {

        Mockito.when(serviceBooks.findById(booksEntity.getId())).thenReturn(Optional.of(new BooksEntity(1L,"Krol Lew","WaltDisney","Dla dzieci")));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/findById/1")
                        .contentType("application/json"))
                        .andDo(print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        BooksEntity booksEntity = objectMapper.readValue(jsonAsString, BooksEntity.class);
        assertEquals(booksEntity.getBooksEntity(booksEntity), booksEntity.getBooksEntity(booksEntity));
    }



}
