package com.smileynrk.TwitterStreamer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.smileynrk.TwitterStreamer.dto.DeleteRule;

import reactor.core.publisher.Mono;

@Service
public class RuleManagerService {

	@Autowired
	WebClient webClient;
	
	public Mono<String> deleteRule(String id) {	
		String body = "{\"delete\": {\"ids\": [\""+id+"\"]}}";
		
		Mono<DeleteRule> response = webClient.post().uri("/stream/rules").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(body).retrieve().bodyToMono(DeleteRule.class).log("Delete Rule Stream");
		
		return response.map(res -> (res.getMeta().getSummary().getDeleted() == 1?"true":"false"));
	}
	
	public Mono<String> addRule(String author,String tag) throws Exception {
		String body = "";
		
		if (author != null && !author.isEmpty() && tag != null && !tag.isEmpty()) {
			body = "{\"add\": [{\"value\": \""+tag+" from:" + author + "\", \"tag\": \"b-" + author+":"+tag + "\"}]}";
		}		
		else if (author != null && !author.isEmpty()) {
			body = "{\"add\": [{\"value\": \"from:" + author + "\", \"tag\": \"a-" + author + "\"}]}";
		}
		else if (tag != null && !tag.isEmpty()) {
			body = "{\"add\": [{\"value\": \"" + tag + "\", \"tag\": \"t-" + tag + "\"}]}";
		}
		else {
			throw new Exception("Author and Tag both cannot be empty");
		}
		
		Mono<String> response = webClient.post().uri("/stream/rules").contentType(MediaType.APPLICATION_JSON).bodyValue(body)
				.retrieve().bodyToMono(String.class).log("Add Rule Stream");

		
		return response;
	}
}
