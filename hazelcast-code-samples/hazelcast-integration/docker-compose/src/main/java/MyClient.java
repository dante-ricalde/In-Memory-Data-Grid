import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;

/**
 * 
 * @author dante
 *
 */
public class MyClient {

	public static void main(String[] args) {
		ClientConfig clientConfig = new XmlClientConfigBuilder().build();
	}
}
