package com.example.demo2.controller;


import java.util.*;
import com.example.demo2.models.Book;
import com.example.demo2.service.BookService;
import com.example.demo2.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity.BodyBuilder;
import jakarta.validation.Valid;

import com.example.demo2.models.*;
import com.example.demo2.repository.*;
import com.example.demo2.service.*;


@RestController
@RequestMapping(path = "/book")
@AutoConfiguration
@PropertySource("classpath:application.properties")
public class BookController {
	
	
	

   //@Autowired
   //private BookService bookService;
   //@Autowired
   //private AIService aiService;
   //@Autowired
   Environment env;
   //@Autowired 
   //private WebClient webClient;
   
   private final BookService bookService;
   private final AIService aiService;

   @Autowired  // ✅ Ensure this is present
   public BookController(BookService bookService, AIService aiService) {
       this.bookService = bookService;
       this.aiService = aiService;
   }
   /*
   public BookController() {
	   
   }
   
   */
   /*
   public BookController(BookService bookService, AIService aiService) {
	   this.bookService = bookService ;
	   this.aiService = aiService ;
   }
   */
   private static List<Book> booksList ;

   @GetMapping("/books")
   public List<Book> getAllBooks(){
	   System.out.println("Get All books") ;
	   return bookService.getAllBooks();
   }
   @GetMapping("/books/{id}")
   public Book getBookById(@PathVariable("id") int id) {
	   System.out.println("Get book by id:" + id) ;
	   Optional<Book> bookByID= bookService.getBookById(id) ;
	   if(bookByID.isEmpty()) {
		   System.out.println("Book is not present");
		   return null ;
	   }
	   else {
		   return bookByID.get() ;
	   }
	   //return bookService.getBookById(id);
   }
   
   @GetMapping("/books/search")
   public ResponseEntity<?> getBookBySearchString(
           @RequestParam(required = false, value = "title") String title,
           @RequestParam(required = false, value = "author") String author) {

       System.out.println("Searching for Title: " + title + " or Author: " + author);
       List<Book> results = bookService.getBookBySearchString(title, author);

       if (results.isEmpty() || results.size() == 0) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found with given search criteria.");
       }

       return ResponseEntity.ok(results);
   }
   
   @DeleteMapping("/books/{id}")
   public void deleteBookById(@PathVariable("id") int id) {
	   System.out.println("Delete by id:" + id) ;
	   bookService.deleteBookById(id);
   }
  
   @PutMapping("/books/{id}")
   public void updateBookById(@RequestBody Book book, @PathVariable(value="id", required=true) Long bookId) {
	   System.out.println("Update book by id:" + bookId) ;
	   System.out.println("Updated Book Details:" + bookService.saveOrUpdate(book, bookId));
   }
   /*
   @PostMapping("/createBook")
   public ResponseEntity<Object> createBook(@Valid @RequestBody Book book) {
	   
	   
	   System.out.println("Book is being created") ;
	   Book savedBook = bookService.addNewBookWithoutID(book) ;
	   booksList = (HashMap<Long, Book>) bookService.getAllBooks();
	   System.out.println("No of Books:" + booksList.size()) ;
	   //return new ResponseEntity<>("Book is created successfully", HttpStatus.CREATED);
	   return ResponseEntity.ok(savedBook) ;
   }
   */
   @PostMapping("/createBook")
   public ResponseEntity<?> createBook(@Valid @RequestBody Book book) {
       System.out.println("Book is being created");

       Book savedBook = bookService.addNewBookWithoutID(book);
       booksList = bookService.getAllBooks();
       
       System.out.println("No of Books:" + booksList.size());

       return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
   }
   
   @PostMapping("/{id}/ai-insights")
   public Mono<ResponseEntity<?>> getBookWithAIInsights(@PathVariable Long id) {
	   System.out.println("With AI_insight:"+ id);
       Optional<Book> bookOpt = bookService.getBookById(id.intValue());

       if (bookOpt.isEmpty()) {
           return Mono.just(ResponseEntity.notFound().build());
       }

       Book book = bookOpt.get();
       String prompt = "Provide a short, engaging tagline for the book titled '" + book.getBookName() +
                       "' by " + book.getAuthor() + ". Description: " + book.getDescription();
       //System.out.println("env.getProperty(\"ai.api.key\"):" + env.getProperty("ai.api.key")) ;
       //System.out.println("env.getProperty(\"ai.api.url\"): " + env.getProperty("ai.api.url"));
       

       return aiService.generateBookInsight(prompt)
               .map(aiInsight -> Map.of(
                       "book", book,
                       "aiInsight", aiInsight
               ))
               .map(ResponseEntity::ok);
   }
   
   
}
