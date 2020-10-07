package com.smileynrk.TwitterStreamer.dto;

import java.util.Date;

import lombok.Data;

@Data
public class StreamData {
	String id;
	String text;
	Date created_at;
}
