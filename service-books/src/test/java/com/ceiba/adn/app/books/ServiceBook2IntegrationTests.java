package com.ceiba.adn.app.books;

//import com.vinsguru.jobservice.dto.JobDto;
//import com.vinsguru.jobservice.compose.BaseTest;
import com.ceiba.adn.app.books.models.service.IBookService;
import com.ceiba.adn.app.commons.models.entity.Book;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
@AutoConfigureWebTestClient
//@TestPropertySource(locations = "classpath:bootstrap.yaml")

class ServiceBook2IntegrationTests {

	@Autowired
	private WebTestClient client;

	@Autowired
	private IBookService bookService;

	private String path = "/book";

	@Before
	public void setUp() {
		path = "/book";
	}

	@Test
	public void allBooksTest() {
		this.client.get()
				.uri(path)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$").isNotEmpty();
	}

	@Test
	public void saveBookTest() {
		int booksCountBefore = bookService.findAll().size();

		Book newBook = new Book();
		newBook.setId(12l);
		newBook.setTitle("New Book");
		newBook.setAuthor("John Doe");
		newBook.setStars(100);

		this.client.post()
				.uri(path)
				.bodyValue(newBook)
				.exchange()
				.expectStatus().is2xxSuccessful();

		int booksCountAfter = bookService.findAll().size();
		assertEquals(booksCountBefore + 1, booksCountAfter);

	}


	/*
	@Test
	public void searchBySkillsTest() {
		this.client.get()
				.uri("/job/search?skills=java")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.size()").isEqualTo(3);
	}

	@Test
	public void postJobTest() {
		var dto = JobDto.create(null, "k8s engr", "google", Set.of("k8s"), 200000, true, null);
		this.client.post()
				.uri("/job")
				.bodyValue(dto)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.description").isEqualTo("k8s engr");
	}
*/

}
