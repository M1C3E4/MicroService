package com.example.demo.controller;

import com.example.demo.persist.BooksEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @Test
    @DisplayName("http://localhost:9090/allBooks -> 200")
    public void pullAllBooksShouldReturnStatus200() throws Exception {

       ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/findAllBooks")
               .contentType("application/json"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        BooksEntity booksEntity = objectMapper.readValue(jsonAsString, BooksEntity.class);
        assertEquals("Nie wiem co tu wstawić", booksEntity.getBooksEntity(booksEntity));
    }

    @Test
    @DisplayName("http://localhost:9090/findById/{id} -> 200")
    public void pullBooksByIdShouldReturnStatus200() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/findById/1")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        BooksEntity booksEntity = objectMapper.readValue(jsonAsString, BooksEntity.class);
        assertEquals("Nie wiem co tu wstawić", booksEntity.getBooksEntity(booksEntity));


    }

//    @Test
//    @DisplayName("http://localhost:9090/addBook -> 200")
//    public void saveBookShouldReturnStatus200() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/addBook")
//               .contentType("application/json"))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//
//    }



}
