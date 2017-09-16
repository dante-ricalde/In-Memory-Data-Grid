package com.hazelcast.samples;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

/**
 * 
 * @author dante
 *
 */
@SpringBootApplication
public class MyClient implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(MyClient.class);
	
	@Autowired
	private Properties hzService1EmbeddedProperties;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MyClient.class, args);
		
	}

	public void run(String... arg0) throws Exception {
		logger.debug("Starting xml client config builder.");
		ClientConfig clientConfig = new XmlClientConfigBuilder().build();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		
		logger.debug(clientConfig.toString());
		
		// ************************ config embeded instance *****************************
		logger.debug("**************************** STARTING HZ EMBEDED INSTANCE *****************");		
		// Config management center with this client to see all the instances and their queue in the management
		// center console
		logger.debug("Properties used for embedded instance: " + hzService1EmbeddedProperties.toString());
		logger.debug("Config group for embedded instance: " + hzService1EmbeddedProperties.getProperty("group.name"));
		Config config = new XmlConfigBuilder("/configFolder/member/hz.service1.embedded.xml")
				.setProperties(hzService1EmbeddedProperties).build();
		HazelcastInstance newHazelcastInstance = Hazelcast.newHazelcastInstance(config);
		logger.debug("HZ EMBEDDED INSTANCE STARTED ...");
		logger.debug("STARTING HZ EMBEDDED INSTANCE CLIENT");
		XmlClientConfigBuilder xmlClientConfigBuilder = new XmlClientConfigBuilder("/configFolder/member/hz.service1.embedded-client.xml");
		xmlClientConfigBuilder.setProperties(hzService1EmbeddedProperties);
		final HazelcastInstance embeddedClient = HazelcastClient.newHazelcastClient(xmlClientConfigBuilder.build());
		logger.debug(embeddedClient.toString());
		IQueue<Object> embeddedQueue = embeddedClient.getQueue("embeddedQueue");
		embeddedQueue.put("Hello embedded Queue" + new Date() + "!");
		
		logger.debug("**************************** END STARTING HZ EMBEDED INSTANCE *****************");
		
		
		IQueue<String> queue = client.getQueue("queue");
		queue.put("Hello!" + new Date() + "!");
		logger.debug("Message sent by Hazelcast Client!");
		
		HazelcastClient.shutdownAll();
	}
}
