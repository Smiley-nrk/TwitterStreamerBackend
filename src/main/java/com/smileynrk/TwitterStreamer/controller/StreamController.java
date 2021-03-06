package com.smileynrk.twitterstreamer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smileynrk.twitterstreamer.dto.Rules;
import com.smileynrk.twitterstreamer.dto.StreamResp;
import com.smileynrk.twitterstreamer.repository.TweetsRepo;
import com.smileynrk.twitterstreamer.service.RuleManagerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StreamController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StreamController.class);

	@Qualifier("TweetStream")
	@Autowired
	Flux<StreamResp> tStream;

	@Qualifier("Filters")
	@Autowired
	Mono<Rules> filters;
	
	@Autowired
	TweetsRepo twRepo;

	@Autowired
	RuleManagerService rms;

	@GetMapping(value = "/getTweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@CrossOrigin(origins ="https://twitterstreamapplication.herokuapp.com")
	public Flux<String> getTweets() {
		LOGGER.info("New Req for Streaming");
		return twRepo.saveAll(tStream).map(tweet -> {
			try {
				return (new ObjectMapper().writeValueAsString(tweet));
			} catch (JsonProcessingException e) {
				return "";
			}
		});
	}

	@GetMapping(value = "/getFilters")
	public Mono<String> getFilters() {
		LOGGER.info("New Req for Rules");
		
		return filters.map(filter -> {
			try {
				return new ObjectMapper().writeValueAsString(filter);
			} catch (JsonProcessingException e) {
				return "";
			}
		});
	}

	@GetMapping(value = "/deleteFilter")
	public Mono<String> deleteFilter(@RequestParam("id") String id) {
		LOGGER.info("Request to delete filter with ID: "+id);
		return rms.deleteRule(id);
	}

	@GetMapping(value = "/addFilter")
	public Mono<String> addFilter(@RequestParam("tag") String tag, @RequestParam("author") String author) {
		LOGGER.info("Request to add new Filter");
		try {
			return rms.addRule(author, tag);
		} catch (Exception e) {
			LOGGER.error("Exception while adding filter: "+e.getMessage());
			return Mono.just("false");
		}
	}

}
