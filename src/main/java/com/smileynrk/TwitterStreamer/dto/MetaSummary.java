package com.smileynrk.twitterstreamer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaSummary {
	int created;
	
	@JsonProperty("not_created")
	int notCreated;
	
	int deleted;
	
	@JsonProperty("not_deleted")
	int notDeleted;
}