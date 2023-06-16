package com.amarnath.restapiproject.services;

import java.util.List;

public class AutherName {
	
	public AutherName() {
		
	}
	
	private String id;
	private String authorName;
	private List<BookDetails> bookDetails;
	
	public AutherName(String id, String authorName, List<BookDetails> bookDetails) {
		super();
		this.id = id;
		this.authorName = authorName;
		this.bookDetails = bookDetails;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public List<BookDetails> getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(List<BookDetails> bookDetails) {
		this.bookDetails = bookDetails;
	}

	@Override
	public String toString() {
		return "AutherName [id=" + id + ", authorName=" + authorName + ", bookDetails=" + bookDetails + "]";
	}
	
	
	

}
