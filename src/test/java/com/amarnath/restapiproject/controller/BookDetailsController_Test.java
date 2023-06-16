package com.amarnath.restapiproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.amarnath.restapiproject.services.BookDetails;
import com.amarnath.restapiproject.services.DataStoreService;

//BookDetailsController
@WebMvcTest(controllers = BookDetailsController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookDetailsController_Test {

	@MockBean
	private DataStoreService dataStoreService;

	@Autowired
	private MockMvc mockMvc;

	// mock this method out: dataStoreService.getBooksByIdByAuther(authersId,
	// bookId);
	// http://localhost:8080/authers/1/books/1

	private static String bookByIdUrl = "http://localhost:8080/authers/1/books/1";
	private static String bookByIdUrlFind = "http://localhost:8080/authers/1/books";

	@Test
	void GetBooksByIdByAuther_404TestToLearn() throws Exception {
		
		
		
		RequestBuilder RequestBuilder = MockMvcRequestBuilders.get(bookByIdUrl).accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(RequestBuilder).andReturn();
		
		
		System.out.println(mvcResult.getResponse().getContentAsString());
		System.out.println(mvcResult.getResponse().getStatus());
		
		assertEquals(404, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	void GetBooksByIdByAuther_TestToLearn() throws Exception {
		
		
		
		RequestBuilder RequestBuilder = MockMvcRequestBuilders.get(bookByIdUrl).accept(MediaType.APPLICATION_JSON);
		
		
		
		BookDetails bookDetail = new BookDetails("1", "Harry Potter And The Philosopher's Stone", "1997", "Harry Potter is a Fantasy novel by J. K. Rowling");
		
		when(dataStoreService.getBooksByIdByAuther("1","1")).thenReturn(bookDetail);
		
		MvcResult mvcResult = mockMvc.perform(RequestBuilder).andReturn();
		
		System.out.println(mvcResult.getResponse().getContentAsString());
		System.out.println(mvcResult.getResponse().getStatus());
		
		String expectedResponse = """
				{"id":"1","bookName":"Harry Potter And The Philosopher's Stone","dateOfPublish":"1997","description":"Harry Potter is a Fantasy novel by J. K. Rowling"}
				"""; 
		
		JSONAssert.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString(), true);
		assertEquals(200, mvcResult.getResponse().getStatus());
		
	}
	
	@Test
	void AddBookByAuther_TestToPost() throws Exception {
		
		String requestBody = """

				{
		"bookName": "Harry Potter And The Philosopher's Stone",
		"dateOfPublish": "1997",
		"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
		}

				""";
		
		when(dataStoreService.addBookByAuther(anyString(), any())).thenReturn("some_id");
		RequestBuilder RequestBuilder = MockMvcRequestBuilders.post(bookByIdUrlFind).accept(MediaType.APPLICATION_JSON).content(requestBody).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(RequestBuilder).andReturn();
		
		assertEquals(201, mvcResult.getResponse().getStatus());
		
		String locationHeader = mvcResult.getResponse().getHeader("Location");
		
		assertTrue(locationHeader.contains("/authers/1/books/some_id"));
		
	}

}
