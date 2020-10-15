package com.smileynrk.twitterstreamer.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document(collection = "tweet")
public class StreamResp implements Comparable<StreamResp> {
	StreamData data;
	
	@JsonProperty("matching_rules")
	List<RuleData> matchingRules;
	
	
	@Override
	public int compareTo(StreamResp o) {
		if(this.data != null && this.data.getCreatedAt() != null && o.data != null && o.data.getCreatedAt() != null)
			return o.data.getCreatedAt().compareTo(this.data.getCreatedAt());
		else
			return 0;
	}
	
	
}
