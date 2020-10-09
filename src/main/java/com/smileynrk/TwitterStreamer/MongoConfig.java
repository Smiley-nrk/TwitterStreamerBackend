package com.smileynrk.TwitterStreamer;

import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration
public class MongoConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoConfig.class);

	@Bean
	public IMongodConfig config() throws UnknownHostException, IOException {
//		MongodStarter starter = MongodStarter.getDefaultInstance();

		String bindIp = "localhost";
		int port = 12345;
		return new MongodConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(bindIp, port, Network.localhostIsIPv6())).build();

//		MongodExecutable mongodExecutable = null;
//		mongodExecutable = starter.prepare(mongodConfig);
//		MongodProcess mongod = mongodExecutable.start();
//
//		MongoClient mongo = new MongoClient(bindIp, port);
	}
}