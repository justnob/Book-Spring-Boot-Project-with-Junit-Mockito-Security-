package com.amarnath.restapiproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

class JesonAssertTest {

	@Test
	void jsonAssert_learning_basic() throws JSONException {

		String response = """

				{\"id\":\"1\",\"bookName\":\"Harry Potter And The Philosopher's Stone\",\"dateOfPublish\":\"1997\",\"description\":\"Harry Potter is a Fantasy novel by J. K. Rowling\"}
				""";

		String response2 = """

				   {\"id\":\"1\",\"bookName\":\"Harry Potter And The Philosopher's Stone\",\"dateOfPublish\":\"1997\",\"description\":\"Harry Potter is a Fantasy novel by J. K. Rowling\"}
				""";

		JSONAssert.assertEquals(response, response2, true);
	}

}
