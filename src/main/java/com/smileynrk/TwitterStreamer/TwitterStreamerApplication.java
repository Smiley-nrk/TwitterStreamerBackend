package com.smileynrk.TwitterStreamer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class TwitterStreamerApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(TwitterStreamerApplication.class, args);
	}

}