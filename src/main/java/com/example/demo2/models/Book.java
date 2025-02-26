package com.example.demo2.models;

import jakarta.persistence.*;
import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    @Column(nullable = false)
    @NotBlank(message = "Book name cannot be empty")
    @Size(max = 255, message = "Book name must be at most 255 characters")
    private String bookName;
    
    @NotBlank(message = "ISBN cannot be empty")
    @Size(max = 20, message = "ISBN must be at most 20 characters")
    @Column(nullable = false, unique = true)
    private String bookISBN;
    
    @NotBlank(message = "Publisher cannot be empty")
    @Size(max = 255, message = "Publisher name must be at most 255 characters")
    @Column(nullable = false)
    private String bookPublisher;
    
    @Column
    @NotBlank(message = "Book type cannot be empty")
    private String bookType;
    
    @Column
    @NotBlank(message = "Author cannot be empty")
    private String author;
    
    @Column
    @NotNull(message = "Publication date cannot be null")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Column
    @Size(max = 10, message = "Edition number must be at most 10 characters")
    private String editionNumber;
    
    @Column
    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description; 

    public Book() {}

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(String editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String toString() {
    	return "bookID: "+ bookID + " editionNumber: " + editionNumber +
    			" description: " + description + " date: " + date +
    			" author: " + author +
    			" bookName: " + bookName +
    			" bookISBN: " + bookISBN ;
    }
}
