package com.smileynrk.twitterstreamer.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class StreamData {
	String id;
	String text;
	
	@JsonProperty("created_at")
	Date createdAt;
}
