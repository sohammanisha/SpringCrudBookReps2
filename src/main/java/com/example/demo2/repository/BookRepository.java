package com.example.demo2.repository;

import org.springframework.data.repository.CrudRepository;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo2.models.*;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>  {
}