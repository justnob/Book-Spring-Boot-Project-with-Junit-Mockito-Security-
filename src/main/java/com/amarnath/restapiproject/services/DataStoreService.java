package com.amarnath.restapiproject.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class DataStoreService {
	
	private static List<AutherName> autherNamesAll = new ArrayList<>();
	private static List<BookDetails> bookDetailsAll = new ArrayList<>();
	
	static {
		
		BookDetails bookDetail1 = new BookDetails("1", "YOU", "2014", "You is a thiller novel by Caroline Kepnes");
		BookDetails bookDetail2 = new BookDetails("1", "Harry Potter And The Philosopher's Stone", "1997", "Harry Potter is a Fantasy novel by J. K. Rowling");
		BookDetails bookDetail3 = new BookDetails("2", "Harry Potter And The Chambber Of Secrets ", "1998", "Harry Potter is a Fantasy novel by J. K. Rowling");
		
		List<BookDetails> bookDetailsForJKRowling = new ArrayList<>(Arrays.asList(bookDetail2, bookDetail3));
		List<BookDetails> bookDetailsForCarolineKepnes = new ArrayList<>(Arrays.asList(bookDetail1));
		
		AutherName autherName1 = new AutherName("1", "J. K. Rowling", bookDetailsForJKRowling);
		AutherName autherName2 = new AutherName("2", "Caroline Kepnes", bookDetailsForCarolineKepnes);
		
		List<AutherName> autherNames = new ArrayList<>(Arrays.asList(autherName1, autherName2));
		
		
		bookDetailsAll.addAll(bookDetailsForCarolineKepnes);
		bookDetailsAll.addAll(bookDetailsForJKRowling);
		autherNamesAll.addAll(autherNames);
	}

	// To return all the authors available.
	public List<AutherName> getAllAuthers() {
		
		return autherNamesAll;
		
	}

	// To add a new author to the list
	public String addPerticularAuther(AutherName author) {
		
		author.setId(GeneraterandomId());
		
		autherNamesAll.add(author);
		
		return author.getId();
		
	}

	// To search an author by id
	public AutherName GetAutherById(String authersId) {
		
		Predicate<? super AutherName> predicate = autherNames -> autherNames.getId().equalsIgnoreCase(authersId);
		Optional<AutherName> findAuthor = autherNamesAll.stream().filter(predicate).findFirst();
		
		return findAuthor.get();
		
	}

	// To get a particular author books
	public List<BookDetails> getBooksByAuther(String authersId) {
		
		AutherName getAutherById = GetAutherById(authersId);
		
		List<BookDetails> bookDetails = getAutherById.getBookDetails();
		
		return bookDetails;
		
	}

	// To find book by id and author
	public BookDetails getBooksByIdByAuther(String authersId, String bookId) {
		 
		List<BookDetails> booksByAuther = getBooksByAuther(authersId);
		
		Optional<BookDetails> fingBookByIdByAuthor = booksByAuther.stream().filter(b -> b.getId().equals(bookId)).findFirst();
		
		return fingBookByIdByAuthor.get();
		
	}

	// To add a book to the specific author
	public String addBookByAuther(String authersId, BookDetails book) {
		
		List<BookDetails> booksByAuther = getBooksByAuther(authersId);
		
		book.setId(GeneraterandomId());
		
		booksByAuther.add(book);
		
		return book.getId();
		
	}

	// To generate random ID
	private String GeneraterandomId() {
		SecureRandom secureRandom = new SecureRandom();
		
		String randomId = new BigInteger(32, secureRandom).toString();
		return randomId;
	}

	// To delete a particular book from author by id
	public void deleteBooksByIdByAuther(String authersId, String bookId) {
		
		List<BookDetails> booksByAuther = getBooksByAuther(authersId);
		
		Predicate<? super BookDetails> predicate = b -> b.getId().equals(bookId);
		
		booksByAuther.removeIf(predicate);
		
		
		
	}

	// To delete a particular author by id
	public void deleteAutherById(String authersId) {
		
		Predicate<? super AutherName> predicate = autherNames -> autherNames.getId().equalsIgnoreCase(authersId);
		autherNamesAll.removeIf(predicate);
		
	}

	// To update a existing book
	public void updateBooksByIdByAuther(String authersId, String bookId, BookDetails book) {
		
		List<BookDetails> booksByAuther = getBooksByAuther(authersId);
		
		Predicate<? super BookDetails> predicate = b -> b.getId().equals(bookId);
		
		booksByAuther.removeIf(predicate);
		
		booksByAuther.add(book);
	}
	
	// To update a existing author
	public void updateAutherById(String authersId, AutherName author) {
		
		Predicate<? super AutherName> predicate = autherNames -> autherNames.getId().equalsIgnoreCase(authersId);
		autherNamesAll.removeIf(predicate);
		
		autherNamesAll.add(author);
		
	}
	

}
