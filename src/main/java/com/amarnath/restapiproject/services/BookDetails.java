package com.amarnath.restapiproject.services;

public class BookDetails {
	
	public BookDetails() {
		
	}
	
	
	private String id;
	private String bookName;
	private String dateOfPublish;
	private String description;
	
	public BookDetails(String id, String bookName, String dateOfPublish, String description) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.dateOfPublish = dateOfPublish;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDateOfPublish() {
		return dateOfPublish;
	}

	public void setDateOfPublish(String dateOfPublish) {
		this.dateOfPublish = dateOfPublish;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "BookDetails [id=" + id + ", bookName=" + bookName + ", dateOfPublish=" + dateOfPublish
				+ ", description=" + description + "]";
	}
	
	

}
