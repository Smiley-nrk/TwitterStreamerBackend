package com.smileynrk.TwitterStreamer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rules {
	List<RuleData> data;
	MetaData meta;
}
