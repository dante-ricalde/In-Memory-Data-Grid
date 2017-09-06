package com.hazelcastexamples.compute.executors;

import java.io.FileNotFoundException;

import com.hazelcast.config.XmlConfigBuilder;

/**
 * 
 * @author dante
 *
 */
public class Node {

	public static void main(String [] args) throws FileNotFoundException, InterruptedException {
		XmlConfigBuilder builder = new XmlConfigBuilder("src/main/resources/hazelcast.xml");
		Thread.sleep(20000);
	}
}
