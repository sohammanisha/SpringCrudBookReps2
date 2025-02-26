
package com.example.demo2.controller;

import com.example.demo2.models.Book;
import com.example.demo2.service.AIService;
import com.example.demo2.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For JSON serialization

    @MockBean
    private BookService bookService;

    @MockBean
    private AIService aiService;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book();
        testBook.setBookID(1L);
        testBook.setBookName("Spring Boot Guide");
        testBook.setBookISBN("123456789");
        testBook.setBookPublisher("TechPress");
        testBook.setBookType("Programming");
        testBook.setAuthor("John Doe");
        testBook.setDate(new Date());
        testBook.setEditionNumber("1st");
        testBook.setDescription("A complete guide to mastering Spring Boot.");
    }

    @Test
    void shouldCreateBookSuccessfully() throws Exception {
        when(bookService.addNewBookWithoutID(any(Book.class))).thenReturn(testBook);

        mockMvc.perform(post("http://localhost:8080/book/createBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookID").value(1))
                .andExpect(jsonPath("$.bookName").value("Spring Boot Guide"));
    }

    @Test
    void shouldReturnValidationErrorWhenBookNameIsEmpty() throws Exception {
        testBook.setBookName("");

        mockMvc.perform(post("https://localhost:8080/book/createBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.bookName").value("Book name cannot be empty"));
    }
    /*
    @Test
    void shouldReturnBookWithAIInsightsSuccessfully() throws Exception {
        when(bookService.getBookById(1)).thenReturn(Optional.of(testBook));
        when(aiService.generateBookInsight(anyString())).thenReturn(Mono.just("An essential guide to Spring Boot."));

        mockMvc.perform(get("/books/1/ai-insights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.book.bookID").value(1))
                .andExpect(jsonPath("$.aiInsight").value("An essential guide to Spring Boot."));
    }
    */
    /*
    @Test
    void shouldReturnNotFoundForNonExistentBook() throws Exception {
        when(bookService.getBookById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/99/ai-insights"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void shouldHandleAIServiceFailure() throws Exception {
        when(bookService.getBookById(1)).thenReturn(Optional.of(testBook));
        when(aiService.generateBookInsight(anyString())).thenReturn(Mono.error(new RuntimeException("AI Service Down")));

        mockMvc.perform(get("/books/1/ai-insights"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.error").value("AI service failure: AI Service Down"));
    }
    */
}