package com.smileynrk.TwitterStreamer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaSummary {
	int created;
	int not_created;
	int deleted;
	int not_deleted;
}