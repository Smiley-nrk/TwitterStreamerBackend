package com.smileynrk.twitterstreamer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smileynrk.twitterstreamer.dto.Rules;
import com.smileynrk.twitterstreamer.dto.StreamResp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class TwitterStreamer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterStreamer.class);


	@Bean
	WebClient buildWebClient(Environment env) {
		String token = env.getProperty("bearerToken");
		return WebClient.builder().baseUrl("https://api.twitter.com/2/tweets/search")
				.defaultHeader("Authorization", "Bearer " + token).build();
	}

	@Bean("TweetStream")
	Flux<StreamResp> initiateTwitterStreamer(WebClient webClient) {
		LOGGER.info("Connection creating for streaming");
		Flux<String> stream = webClient.get().uri("/stream?tweet.fields=created_at").retrieve().bodyToFlux(String.class)
				.share().retry();

		Gson g =  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
		return stream.flatMap(tweet -> {
			if (tweet.isEmpty())
				return Flux.just(new StreamResp());
			else
				return Flux.just(g.fromJson(tweet, StreamResp.class));
		}).log("Tweet Input Stream");
	}

	@Bean("Filters")
	Mono<Rules> fetchFilters(WebClient webClient) {

		return webClient.get().uri("/stream/rules").retrieve().bodyToMono(Rules.class)
				.log("Rule Input Stream");

	}

}
