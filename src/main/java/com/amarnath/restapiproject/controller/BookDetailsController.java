package com.amarnath.restapiproject.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amarnath.restapiproject.services.AutherName;
import com.amarnath.restapiproject.services.BookDetails;
import com.amarnath.restapiproject.services.DataStoreService;

@RestController
public class BookDetailsController {
	
	private DataStoreService dataStoreService;

	public BookDetailsController(DataStoreService dataStoreService) {
		super();
		this.dataStoreService = dataStoreService;
	}
	
	
	// To get all the authors available
	@GetMapping("/authers")
	public List<AutherName> GetAllAuthers(){
		
		List<AutherName> allAuthers = dataStoreService.getAllAuthers();
		
		if(allAuthers.isEmpty()) {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
		}
		return allAuthers;
		
	}
	
	// To search an author by id
	@GetMapping("/authers/{authersId}")
	public AutherName GetAutherById(@PathVariable String authersId){
		
		 AutherName getAutherById = dataStoreService.GetAutherById(authersId);
		 
		 if(getAutherById == null) {
			 
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			 
		 }
		 return getAutherById;
		
	}
	
	// To delete a particular author by id
	@DeleteMapping("/authers/{authersId}")
	public ResponseEntity<Object> DeleteAutherById(@PathVariable String authersId){
		
		 dataStoreService.deleteAutherById(authersId);
		 
		 return ResponseEntity.noContent().build();
		
	}
	
	// To add a author in the list
	@PostMapping("/authers")
	public ResponseEntity<Object> AddPerticularAuther(@RequestBody AutherName author){
		
		 String authorId = dataStoreService.addPerticularAuther(author);
		 
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{authorId}").buildAndExpand(authorId).toUri();
		 
		 return ResponseEntity.created(location).build();
		
	}
	
	// To get a particular author books
	@GetMapping("/authers/{authersId}/books")
	public List<BookDetails> GetBooksByAuther(@PathVariable String authersId){
		
		 List<BookDetails> getBookByAuther = dataStoreService.getBooksByAuther(authersId);
		 
		 if(getBookByAuther == null) {
			 
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			 
		 }
		 return getBookByAuther;
		
	}
	
	// To add a book to the specific author
	@PostMapping("/authers/{authersId}/books")
	public ResponseEntity<Object> AddBookByAuther(@PathVariable String authersId, @RequestBody BookDetails book){
		
		  String bookId = dataStoreService.addBookByAuther(authersId, book);
		  
		  URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{bookId}").buildAndExpand(bookId).toUri();
		  
		  return ResponseEntity.created(location).build();
		 
		
	}
	
	// To find book by id and author
	@GetMapping("/authers/{authersId}/books/{bookId}")
	public BookDetails GetBooksByIdByAuther(@PathVariable String authersId, @PathVariable String bookId){
		
		 BookDetails getBookByIdByAuther = dataStoreService.getBooksByIdByAuther(authersId, bookId);
		 
		 if(getBookByIdByAuther == null) {
			 
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			 
		 }
		 return getBookByIdByAuther;
		  
	}
	
	// To delete a particular book from author
	@DeleteMapping("/authers/{authersId}/books/{bookId}")
	public ResponseEntity<Object> DeleteBooksByIdByAuther(@PathVariable String authersId, @PathVariable String bookId){
		
		dataStoreService.deleteBooksByIdByAuther(authersId, bookId);
		 
		 
		return ResponseEntity.noContent().build();
		  
	}
	
	// To update a existing book
	@PutMapping("/authers/{authersId}/books/{bookId}")
	public ResponseEntity<Object> UpdateBooksByIdByAuther(@PathVariable String authersId, @PathVariable String bookId, @RequestBody BookDetails book){
		
		dataStoreService.updateBooksByIdByAuther(authersId, bookId, book);
		 
		 
		return ResponseEntity.noContent().build();
		  
	}
	
	// To update a existing author
	@PutMapping("/authers/{authersId}")
	public ResponseEntity<Object> UpdateAutherById(@PathVariable String authersId, @RequestBody AutherName author){
		
		 dataStoreService.updateAutherById(authersId, author);
		 
		 return ResponseEntity.noContent().build();
		
	}

}
