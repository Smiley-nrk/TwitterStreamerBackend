package com.smileynrk.TwitterStreamer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@WebFluxTest(controllers = StreamController.class)
//@Import(EmployeeService.class)
//@Import(Flux.class)
public class StreamControllerTest {
	
	@Autowired
	private WebTestClient wsClient;
	
	@Test
	void testFetchFilters() {
		wsClient.get()
        .uri("/getFilters").accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk();
	}
	
	@Test
	void testAddFilter() {
		wsClient.get()
        .uri("/addFilters?tag=test&author=Smiley_nrk").accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk();
	}
}
