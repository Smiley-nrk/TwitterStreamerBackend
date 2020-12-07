package com.smileynrk.twitterstreamer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.smileynrk.twitterstreamer.dto.DeleteRule;
import com.smileynrk.twitterstreamer.dto.Rules;

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
	
	public Mono<String> addRule(String author,String tag) {
		String body = "\"}]}";
		
		if (author != null && !author.isEmpty() && tag != null && !tag.isEmpty()) {
			body = "{\"add\": [{\"value\": \""+tag+" from:" + author + "\", \"tag\": \"b-" + author+":"+tag + body;
		}		
		else if (author != null && !author.isEmpty()) {
			body = "{\"add\": [{\"value\": \"from:" + author + "\", \"tag\": \"a-" + author + body;
		}
		else if (tag != null && !tag.isEmpty()) {
			body = "{\"add\": [{\"value\": \"" + tag + "\", \"tag\": \"t-" + tag + body;
		}
		else {
			throw new IllegalArgumentException("Author and Tag both cannot be empty");
		}
		
		return webClient.post().uri("/stream/rules").contentType(MediaType.APPLICATION_JSON).bodyValue(body)
				.retrieve().bodyToMono(Rules.class).log("Add Rule Stream").map((res) -> {return (res.getMeta().getSummary().getCreated() == 1)+"";});

	}
}
