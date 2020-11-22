package com.smileynrk.twitterstreamer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smileynrk.twitterstreamer.repository.TweetsRepo;

@Service
public class DBCleanerService {
	
	@Autowired
	TweetsRepo twRepo;
	
	@Scheduled(cron = "0 50 23 ? * *")
//	@Scheduled(fixedRate = 1000)
	   public void cronJobSch() {
	      twRepo.deleteAll().block();
	   }
}
