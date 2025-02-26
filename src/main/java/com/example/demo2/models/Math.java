/**
 * 
 */
package com.example.demo2.models;

import java.util.Date;

/**
 * 
 */
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
public class Math extends Book{

	/**
	 * 
	 */
	public Math() {
		// TODO Auto-generated constructor stub
		super() ;
	}
	public void setBookType(String bookType) {
		this.setBookType(bookType) ;
	}
	public String getBookType( ) {
		return getBookType() ;
	}
}
