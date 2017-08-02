import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

/**
 * 
 * @author dante
 *
 */
public class MyClient {

	public static void main(String[] args) throws InterruptedException {
		
		ClientConfig clientConfig = new XmlClientConfigBuilder().build();
		final HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		
		System.out.println(clientConfig.toString());
		
		IQueue<String> queue = client.getQueue("queue");
		queue.put("Hello!");
		System.out.println("Message sent by Hazelcast Client!");
		
		HazelcastClient.shutdownAll();
	}
}
