package com.hazelcast.samples;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ClientConfiguration.class);

	@Bean
	public Properties hzService1EmbeddedProperties() throws IOException {
		Properties result = new Properties();
		logger.debug("Starting properties for /configFolder/member/hz.service1.embedded.properties");
		FileInputStream file = new FileInputStream("/configFolder/member/hz.service1.embedded.properties");
		logger.debug("Properties factory bean configured 1: " + file);
		result.load(file);
		logger.debug("Properties factory bean configured: " + result);
		return result;
	}
}
