package com.smileynrk.TwitterStreamer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.smileynrk.twitterstreamer.dto.RuleData;
import com.smileynrk.twitterstreamer.dto.Rules;
import com.smileynrk.twitterstreamer.dto.StreamResp;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "30000")
@TestMethodOrder(OrderAnnotation.class)
@Slf4j
public class StreamControllerTest {
	
	@Autowired
	private WebTestClient wsClient;
	
	private final String testTag = "test";
	
	private static List<String> filterIdList = new ArrayList<String>();
	
	@Test
	@Order(1)
	void testAddFilter() {
		wsClient.get()
        .uri("/addFilter?tag="+testTag+"&author=")
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .consumeWith(new Consumer<EntityExchangeResult<String>>() {

			@Override
			public void accept(EntityExchangeResult<String> t) {
				String resp = t.getResponseBody();
				log.info("After Adding:"+resp);
				assertEquals("true", resp);				
			}
        	
		});
	}
	
	@Test
	@Order(2)
	void testFetchFilters() {
		wsClient.get()
        .uri("/getFilters").accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Rules.class)
        .consumeWith(new Consumer<EntityExchangeResult<Rules>>() {
			
			@Override
			public void accept(EntityExchangeResult<Rules> t) {
				Rules filters = t.getResponseBody();
				boolean flag = false;
				for(RuleData filter : filters.getData()) {					
					filterIdList.add(filter.getId());
					if(filter.getValue().equalsIgnoreCase(testTag))
						flag = true;
				}
				
				assertTrue(flag);
					
			}
		});
		
	}
	
	@Test
	@Order(3)
	void testStream() {
		wsClient.get()
        .uri("/getTweets")
        .exchange()
        .expectStatus().isOk()
        .returnResult(StreamResp.class).getResponseBody()
        .take(Duration.ofSeconds(5));
	}
	
	@Test
	@Order(4)
	void testDeleteFilter() {
		log.info("Total IDs:"+filterIdList.size());
		for(String id : filterIdList) {
			log.info("deleting Id= "+id);
			wsClient.get()
				.uri("/deleteFilter?id="+id)
				.exchange()
				.expectStatus().isOk()
		        .expectBody(String.class)
		        .consumeWith(new Consumer<EntityExchangeResult<String>>() {

					@Override
					public void accept(EntityExchangeResult<String> t) {
						String resp = t.getResponseBody();
						log.info("After deleting:"+resp);
						assertEquals("true", resp);				
					}
		        	
				});
			
		}
	}
}
