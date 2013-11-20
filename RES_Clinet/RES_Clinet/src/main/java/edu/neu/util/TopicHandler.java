package util;

import org.core4j.Enumerable;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.examples.AbstractExample;
import org.odata4j.format.FormatType;

public class TopicHandler extends AbstractExample {

	private static final String serviceURL = "http://localhost:8886/res.svc/";
	private static String entitySet = "Topic";

	public static void addTopic(String titles, String content, int uid) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity newTopic = c.createEntity(entitySet)
				.properties(OProperties.string("title", titles))
				.properties(OProperties.string("content", content))
				.properties(OProperties.int32("postby", uid)).execute();
		reportEntity("created", newTopic);
	}
	
	// TODO : add return
	public static void findTopic(String keyword) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> topics = c.getEntities(entitySet).filter("substringof(¡®" + keyword + "¡¯, content) eq true").execute();
		reportEntities("Topic", topics);
	}

	public static void findTopic(int id) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> topics = c.getEntities(entitySet).filter("postby eq '" + id + "'").execute();
		reportEntities("Topic", topics);
	}

	public static void main(String[] args) {
	}

}
