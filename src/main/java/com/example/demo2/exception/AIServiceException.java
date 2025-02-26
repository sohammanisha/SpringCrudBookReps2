package com.example.demo2.exception;

public class AIServiceException extends RuntimeException {
	private String aiException;
	public AIServiceException() {
		// TODO Auto-generated constructor stub
	}
	public AIServiceException(String aiException) {
		this.aiException = aiException ;
	}
	public String toString() {
		return aiException ;
	}
}
