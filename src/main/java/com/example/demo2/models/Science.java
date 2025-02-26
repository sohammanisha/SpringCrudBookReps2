/**
 * 
 */
package com.example.demo2.models;

import java.util.Date;

import jakarta.persistence.Entity;

/**
 * 
 */
@Entity
public class Science extends Book{

	/**
	 * 
	 */
	public Science() {
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
