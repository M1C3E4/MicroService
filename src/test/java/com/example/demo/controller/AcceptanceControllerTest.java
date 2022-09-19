package com.example.demo.controller;

import com.example.demo.persist.BooksEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AcceptanceControllerTest {

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
                .andExpect(status().isOk());
        BooksEntity expected = new BooksEntity(1L, "Krol Lew", "WaltDisney", "Dla dzieci");
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        BooksEntity booksEntity = objectMapper.readValue(jsonAsString, BooksEntity.class);
        assertEquals(expected.getId(), booksEntity.getId());
        assertEquals(expected.getTitle(), booksEntity.getTitle());
        assertEquals(expected.getAuthor(), booksEntity.getAuthor());
        assertEquals(expected.getType(), booksEntity.getType());
    }


    @Test
    @DisplayName("http://localhost:9090/findById/{id} -> 200")
    public void pullBooksByIdShouldReturnStatus200() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/findById/1")
                        .contentType("application/json")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());
        BooksEntity expected = new BooksEntity(1L, "Krol Lew", "WaltDisney", "Dla dzieci");
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        BooksEntity booksEntity = objectMapper.readValue(jsonAsString, BooksEntity.class);
        assertEquals(expected.getId(), booksEntity.getId());
        assertEquals(expected.getTitle(), booksEntity.getTitle());
        assertEquals(expected.getAuthor(), booksEntity.getAuthor());
        assertEquals(expected.getType(), booksEntity.getType());
    }


    @Test
    @DisplayName("http://localhost:9090/addBook -> 200")
    public void saveBookShouldReturnStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/addBook")
                        .content(asJsonString(new BooksEntity(2L, "Krol Lew", "WaltDisney", "Dla dzieci")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
