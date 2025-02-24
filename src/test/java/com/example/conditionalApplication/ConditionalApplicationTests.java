package com.example.conditionalApplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private final GenericContainer<?> myAppFirst = new GenericContainer<>("devapp")
			.withExposedPorts(8080);
	private final GenericContainer<?> myAppSecond = new GenericContainer<>("prodapp")
			.withExposedPorts(8081);

	@BeforeEach
	void setUp() {
		myAppFirst.start();
		myAppSecond.start();
	}

	@Test
	void contextLoads() {
		Integer firstAppPort = myAppFirst.getMappedPort(8080);
		Integer secondAppPort = myAppSecond.getMappedPort(8081);

		ResponseEntity<String> entityFromFirst = restTemplate.getForEntity("http://localhost:" + firstAppPort, String.class);
        assertEquals("Current profile is dev", entityFromFirst.getBody());
		ResponseEntity<String> entityFromSecond = restTemplate.getForEntity("http://localhost:" + secondAppPort, String.class);
        assertEquals("Current profile is production", entityFromSecond.getBody());
	}
}
