package com.smileynrk.twitterstreamer;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration
public class MongoConfig {

	@Bean
	public IMongodConfig config() throws IOException {

		String bindIp = "localhost";
		int port = 12345;
		return new MongodConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(bindIp, port, Network.localhostIsIPv6())).build();

	}
}