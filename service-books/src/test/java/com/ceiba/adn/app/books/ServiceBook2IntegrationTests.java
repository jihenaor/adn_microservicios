package com.ceiba.adn.app.books;

//import com.vinsguru.jobservice.dto.JobDto;
//import com.vinsguru.jobservice.compose.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ServiceBook2IntegrationTests {

	@Autowired
	private WebTestClient client;

	@Test
	public void allBooksTest() {
		this.client.get()
				.uri("/book")
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody()
				.jsonPath("$").isNotEmpty();
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