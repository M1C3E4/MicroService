package com.example.demo;
import com.example.demo.persist.BooksEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class MicroserviceApplicationTests {

    private BooksEntity booksEntity;
    private ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    @Test
    void contextLoad(){
    }

    @Test
    @DisplayName("http://localhost:9090/allBooks -> 200")
    void shouldReturnOneRecordByIdWithStatus200() throws Exception{
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/allBooks")
                .contentType("application/json")
                .param("id", "1")
                .param("title", "Król Lew")
                .param("author", "Waltdisney")
                .param("type", "Dla dzieci"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        BooksEntity booksEntity = objectMapper.readValue(jsonAsString, BooksEntity.class);
        assertEquals("1, Król Lew, WaltDisney, Dla dzieci", booksEntity.getId());

    }

}
