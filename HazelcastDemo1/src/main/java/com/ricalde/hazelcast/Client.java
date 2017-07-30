package com.ricalde.hazelcast;

import java.util.Queue;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

/**
 * 
 * @author dante
 *
 */
public class Client {

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		
		HazelcastInstance client = HazelcastClient.newHazelcastClient();
		
		for(int i = 1;  i <= 50000; i++) {
			Queue<String> queue = client.getQueue("queue");
			queue.add("Sent from client message " + i);			
		}
		
		client.shutdown();
	}

}
