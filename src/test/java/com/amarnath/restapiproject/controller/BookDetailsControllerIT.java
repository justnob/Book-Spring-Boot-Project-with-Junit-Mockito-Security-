package com.amarnath.restapiproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookDetailsControllerIT {

	String str = """

			{
				"id": "1",
				"bookName": "Harry Potter And The Philosopher's Stone",
				"dateOfPublish": "1997",
				"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
			}

							""";
	// http://localhost:RANDOM_PORT/authers/1/books/1

	private static String url = "/authers/1/books/1";
	private static String url2 = "/authers/1/books";
	private static String url3 = "/authers/1";
	private static String url4 = "/authers";

	@Autowired
	private TestRestTemplate templete;

	// {"id":"1","bookName":"Harry Potter And The Philosopher's
	// Stone","dateOfPublish":"1997","description":"Harry Potter is a Fantasy novel
	// by J. K. Rowling"}

	@Test
	void GetBooksByIdByAuther_basic() throws JSONException {

		ResponseEntity<String> responseEntity = templete.getForEntity(url, String.class);

		String responseExcepted = """

				{\"id\":\"1\",\"bookName\":\"Harry Potter And The Philosopher's Stone\",\"dateOfPublish\":\"1997\",\"description\":\"Harry Potter is a Fantasy novel by J. K. Rowling\"}
				""";
		// assertEquals(response.trim(), responseEntity.getBody());

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

		String contentType = responseEntity.getHeaders().get("Content-Type").get(0);

		assertEquals("application/json", contentType);

		JSONAssert.assertEquals(responseExcepted, responseEntity.getBody(), true);

	// [Content-Type:"application/json"]
		// Status of resopnse is it 200.
//		System.out.println(responseEntity.getBody());
//		System.out.println(responseEntity.getHeaders());

	}

	@Test
	void GetAllBooks_Test() throws JSONException {

		String response2 = """

								[
				{
				"id": "1",
				"bookName": "Harry Potter And The Philosopher's Stone",
				"dateOfPublish": "1997",
				"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
				},
				{
				"id": "2",
				"bookName": "Harry Potter And The Chambber Of Secrets ",
				"dateOfPublish": "1998",
				"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
				}
				]

												""";
		ResponseEntity<String> responseEntity2 = templete.getForEntity(url2, String.class);

		assertTrue(responseEntity2.getStatusCode().is2xxSuccessful());

		assertEquals("application/json", responseEntity2.getHeaders().get("Content-Type").get(0));

		JSONAssert.assertEquals(response2, responseEntity2.getBody(), true);

	}

	@Test
	void GetAutherById_Test() throws JSONException {

		String response3 = """

								{
				"id": "1",
				"authorName": "J. K. Rowling",
				"bookDetails": [
				{
				"id": "1",
				"bookName": "Harry Potter And The Philosopher's Stone",
				"dateOfPublish": "1997",
				"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
				},
				{
				"id": "2",
				"bookName": "Harry Potter And The Chambber Of Secrets ",
				"dateOfPublish": "1998",
				"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
				}
				]
				}

								""";

		ResponseEntity<String> responseEntity3 = templete.getForEntity(url3, String.class);

		assertTrue(responseEntity3.getStatusCode().is2xxSuccessful());

		assertEquals("application/json", responseEntity3.getHeaders().get("Content-Type").get(0));

		JSONAssert.assertEquals(response3, responseEntity3.getBody(), true);

	}

	@Test
	void GetAllAuthers() throws JSONException {

		String response4 = """

								[
				{
				"id": "1",
				"authorName": "J. K. Rowling",
				"bookDetails": [
				{
				"id": "1",
				"bookName": "Harry Potter And The Philosopher's Stone",
				"dateOfPublish": "1997",
				"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
				},
				{
				"id": "2",
				"bookName": "Harry Potter And The Chambber Of Secrets ",
				"dateOfPublish": "1998",
				"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
				}
				]
				},
				{
				"id": "2",
				"authorName": "Caroline Kepnes",
				"bookDetails": [
				{
				"id": "1",
				"bookName": "YOU",
				"dateOfPublish": "2014",
				"description": "You is a thiller novel by Caroline Kepnes"
				}
				]
				}
				]

								""";
		ResponseEntity<String> responseEntity4 = templete.getForEntity(url4, String.class);

		assertTrue(responseEntity4.getStatusCode().is2xxSuccessful());

		assertEquals("application/json", responseEntity4.getHeaders().get("Content-Type").get(0));

		JSONAssert.assertEquals(response4, responseEntity4.getBody(), true);

	}

	// url to fire the response http://localhost:8080/authers/1/books
	// post
	// body
//		{
//		"bookName": "Harry Potter And The Philosopher's Stone",
//		"dateOfPublish": "1997",
//		"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
//		}

	// Content-Type= application/json
	// if 201 response back
	// check location header

	@Test
	void ToPostAddBookRequest_Test() throws JSONException {

		String requestBody = """

						{
				"bookName": "Harry Potter And The Philosopher's Stone",
				"dateOfPublish": "1997",
				"description": "Harry Potter is a Fantasy novel by J. K. Rowling"
				}

						""";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Authorization", "Basic YW1hcm5hdGg6RHVtbXk=");
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		
		ResponseEntity<String> responseEntity5 = templete.exchange(url2, HttpMethod.POST, httpEntity, String.class);
		
		//System.out.println(responseEntity5.getHeaders());
		
		assertTrue(responseEntity5.getStatusCode().is2xxSuccessful());
		
		String location = responseEntity5.getHeaders().get("Location").get(0);
		assertTrue(location.contains("/authers/1/books"));
		
		//Delete
		//location
		
		templete.delete(location);
		
		//[Location:"http://localhost:51516/authers/1/books/3406450488" 

	}

}
