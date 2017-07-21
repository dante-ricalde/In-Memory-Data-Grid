package com.hazelcastexamples.compute.executors;

import java.io.FileNotFoundException;

import com.hazelcast.config.XmlConfigBuilder;

/**
 * 
 * @author dante
 *
 */
public class Node {

	public static void main(String [] args) throws FileNotFoundException {
		XmlConfigBuilder builder = new XmlConfigBuilder("hazelcast.xml");
	}
}
