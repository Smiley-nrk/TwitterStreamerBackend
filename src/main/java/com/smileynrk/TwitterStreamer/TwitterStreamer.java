package com.smileynrk.TwitterStreamer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.smileynrk.TwitterStreamer.dto.Rules;
import com.smileynrk.TwitterStreamer.dto.StreamResp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class TwitterStreamer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterStreamer.class);

	@Autowired
	Environment env;

	@Bean
	WebClient buildWebClient() {
		String token = env.getProperty("bearerToken");
		return WebClient.builder().baseUrl("https://api.twitter.com/2/tweets/search")
				.defaultHeader("Authorization", "Bearer " + token).build();
	}

	@Bean("TweetStream")
	Flux<StreamResp> initiateTwitterStreamer(WebClient webClient) {
		LOGGER.info("Connection creating for streaming");
		Flux<String> stream = webClient.get().uri("/stream?tweet.fields=created_at").retrieve()
				.bodyToFlux(String.class).share().retry();

		Gson g = new Gson();
		return stream.filter(tweet -> !tweet.isEmpty()).flatMap(tweet -> Flux.just(g.fromJson(tweet, StreamResp.class)))
				.log("Tweet Input Stream");
	}

	@Bean("Filters")
	Mono<Rules> retchFilters(WebClient webClient) {

		Mono<Rules> response = webClient.get().uri("/stream/rules").retrieve().bodyToMono(Rules.class)
				.log("Rule Input Stream");

		return response;
	}

}
