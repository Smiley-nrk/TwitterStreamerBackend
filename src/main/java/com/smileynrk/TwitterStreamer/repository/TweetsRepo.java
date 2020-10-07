package com.smileynrk.TwitterStreamer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.smileynrk.TwitterStreamer.dto.StreamResp;

@Repository
public interface TweetsRepo extends ReactiveMongoRepository<StreamResp, Long>{

}
