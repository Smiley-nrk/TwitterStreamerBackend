package com.smileynrk.twitterstreamer.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaSummary {
	int created;
	
	@SerializedName("not_created")
	int notCreated;
	
	int deleted;
	
	@SerializedName("not_deleted")
	int notDeleted;
}