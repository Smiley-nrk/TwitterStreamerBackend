package com.smileynrk.twitterstreamer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
@EnableScheduling
public class TwitterStreamerApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(TwitterStreamerApplication.class, args);
	}

}