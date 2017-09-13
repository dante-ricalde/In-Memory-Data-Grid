package com.hazelcast.samples;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
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

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(MyClient.class, args);
		
	}

	public void run(String... arg0) throws Exception {
		System.out.println("Starting xml client config builder.");
		ClientConfig clientConfig = new XmlClientConfigBuilder().build();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		
		System.out.println(clientConfig.toString());
		
		// ************************ config embeded instance *****************************
		logger.debug("**************************** STARTING HZ EMBEDED INSTANCE *****************");
		HazelcastInstance newHazelcastInstance = Hazelcast.newHazelcastInstance();
		logger.debug("HZ EMBEDED INSTANCE STARTED ...");
		
		logger.debug("**************************** END STARTING HZ EMBEDED INSTANCE *****************");
		
		
		IQueue<String> queue = client.getQueue("queue");
		queue.put("Hello!");
		System.out.println("Message sent by Hazelcast Client!");
		
		HazelcastClient.shutdownAll();
	}
}
