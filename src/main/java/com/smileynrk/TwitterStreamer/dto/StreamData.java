package com.smileynrk.twitterstreamer.dto;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamData {
	String id;
	String text;
	
	@SerializedName(value = "created_at")
	Date createdAt;
}
