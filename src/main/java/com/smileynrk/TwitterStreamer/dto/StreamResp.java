package com.smileynrk.TwitterStreamer.dto;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tweet")
public class StreamResp implements Comparable<StreamResp> {
	StreamData data;
	List<RuleData> matching_rules;
	
	
	@Override
	public int compareTo(StreamResp o) {
		if(this.data != null && this.data.created_at != null && o.data != null && o.data.created_at != null)
			return o.data.created_at.compareTo(this.data.created_at);
		else
			return 0;
	}
	
	
}
