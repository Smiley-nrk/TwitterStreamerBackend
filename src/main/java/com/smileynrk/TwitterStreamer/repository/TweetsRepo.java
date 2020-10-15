package com.smileynrk.twitterstreamer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.smileynrk.twitterstreamer.dto.StreamResp;

@Repository
public interface TweetsRepo extends ReactiveMongoRepository<StreamResp, Long>{

}
