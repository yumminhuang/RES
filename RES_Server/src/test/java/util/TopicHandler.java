package util;

import java.util.ArrayList;
import java.util.List;

import org.core4j.Enumerable;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.examples.AbstractExample;
import org.odata4j.format.FormatType;

import pattern.Topic;

public class TopicHandler extends AbstractExample {

	private static final String serviceURL = "http://localhost:8886/res.svc/";
	private static String entitySet = "Topic";

	/**
	 * 
	 * @param titles
	 * @param content
	 * @param uid
	 */
	public static void addTopic(String titles, String content, int uid) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity newTopic = c.createEntity(entitySet)
				.properties(OProperties.string("title", titles))
				.properties(OProperties.string("content", content))
				.properties(OProperties.int32("postby", uid)).execute();
		reportEntity("created", newTopic);
	}
	
	/**
	 * TODO: Fix the problem
	 * @param keyword
	 * @return
	 */
	public static List<Topic> findTopic(String keyword) {
		List<Topic> topics = new ArrayList<Topic>();
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> topicEntities = c.getEntities(entitySet).filter("substringof('" + keyword + "',content)").execute();
		for (OEntity e : topicEntities) {
			Topic t = new Topic();
			for (OProperty<?> p : e.getProperties()) {
				if (p.getName().equals("title"))
					t.setTitle((String) p.getValue());
				else if (p.getName().equals("id"))
					t.setId((Integer) p.getValue());
				else if (p.getName().equals("content"))
					t.setContent((String) p.getValue());
				else if (p.getName().equals("postby"))
					t.setPostby((Integer) p.getValue());
				else if (p.getName().equals("image1"))
					t.setImage1((String) p.getValue());
				else if (p.getName().equals("image2"))
					t.setImage2((String) p.getValue());
			}
			topics.add(t);
		}
		return topics;
	}

	/**
	 * @param uid
	 * @return
	 */
	public static List<Topic> findTopicByUser(int uid) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> topicEntities = c.getEntities(entitySet).filter("postby eq " + uid).execute();
		List<Topic> topics = new ArrayList<Topic>();
		for (OEntity e : topicEntities) {
			Topic t = new Topic();
			t.setPostby(uid);
			for (OProperty<?> p : e.getProperties()) {
				if (p.getName().equals("title"))
					t.setTitle((String) p.getValue());
				else if (p.getName().equals("content"))
					t.setContent((String) p.getValue());
				else if (p.getName().equals("id"))
					t.setId((Integer) p.getValue());
				else if (p.getName().equals("image1"))
					t.setImage1((String) p.getValue());
				else if (p.getName().equals("image2"))
					t.setImage2((String) p.getValue());
			}
			topics.add(t);
		}
		return topics;
	}

	public static void main(String[] args) {
		//List<Topic> topics = findTopicByUser(20);
		List<Topic> topics = findTopic("B0BvjhFSMH1Qlv");
		for(Topic t : topics)
			System.out.println(t.toString());
	}

}
