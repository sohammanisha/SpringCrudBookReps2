package com.example.demo2.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.demo2.repository.*;
import com.example.demo2.models.*;
import java.util.* ;

@Service
public class BookService {
   @Autowired
   BookRepository repository;
   private HashMap<Long, Book> books = new HashMap<Long, Book>();
   @Transactional
   public Optional<Book> getBookById(int id) {	
	   return repository.findById(id);
	   
   }
   @Transactional
   public List<Book> getAllBooks(){
	   List<Book> booksList = repository.findAll();
	   /*
			  repository.findAll().forEach(book -> {
				  System.out.println("In getAllBooks Book Id: " + book.getBookID());
				  books.put(book.getBookID(), book) ;
				  });
		*/
			
	  /*Hashtable contains the previous books even if the same book with same id is deleted from repository*/
	  System.out.println("No of books " + booksList.size() + " at time: " + System.currentTimeMillis()) ;
      return booksList;
   }
   @Transactional
   public Book saveOrUpdate(Book updatedBookDetails, long id) {
	   Book book = repository.findById((int)id).get();
	   if(book != null) {
		   book.setBookISBN( updatedBookDetails.getBookISBN()) ;
		   book.setBookName(updatedBookDetails.getBookName());
		   book.setBookPublisher(updatedBookDetails.getBookPublisher()) ;
		   book.setDate( updatedBookDetails.getDate()) ;
		   book.setDescription(updatedBookDetails.getDescription()) ;
		   book.setEditionNumber(updatedBookDetails.getEditionNumber()) ;
		   book.setAuthor(updatedBookDetails.getAuthor()) ;
		   return repository.save(book);
	   }
	   else {
		   return repository.save(updatedBookDetails);
	   }
	   
   }
   @Transactional
   public Book addNewBookWithoutID(Book book) {
	   
	   //books.put(book.bookID, book) ;
	   Book savedBook  = repository.save(book);
	   System.out.println("Saved Book ID: " + savedBook.getBookID());
	   return savedBook ;
   }
   @Transactional
   public void deleteBookById(int id) {
      repository.deleteById(id);
      
   }
   @Transactional
   public List<Book> getBookBySearchString(String title, String author) {
       System.out.println("📌 Inside BookService.getBookBySearchString()");
       List<Book> booksList = repository.findAll();
       List<Book> matchingBooks = new ArrayList<>();

       for (Book book : booksList) {
           if (title != null && author != null) {
               if (book.getBookName().equalsIgnoreCase(title.trim()) && book.getAuthor().equalsIgnoreCase(author.trim())) {
                   matchingBooks.add(book);
               }
           } else if (title != null) {
               if (book.getBookName().equalsIgnoreCase(title.trim())) {
                   matchingBooks.add(book);
               }
           } else if (author != null) {
               if (book.getAuthor().equalsIgnoreCase(author.trim())) {
                   matchingBooks.add(book);
               }
           }
       }

       System.out.println("✅ Found " + matchingBooks.size() + " matching books");
       return matchingBooks;
   }

   
}