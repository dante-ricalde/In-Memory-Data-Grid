package com.ricalde.hazelcast;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * 
 * @author dante
 *
 */
public class Member {

	public static void main(String[] args) throws InterruptedException {
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
		System.out.println("Instance Started...!");
		
		//BlockingQueue<String> queue = hazelcastInstance.getQueue("queue");
		Queue<String> queue = hazelcastInstance.getQueue("queue");
		Thread.sleep(50000);
		
		for(;;) {
			try {
				// Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
				//System.out.println(queue.take());
				// Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
//				String object = queue.peek();
				for (Iterator<String> iterator = queue.iterator(); iterator.hasNext();) {
					String object = (String) iterator.next();
					if (object != null) {
						System.out.println(object);					
					}					
				}
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
