package edu.neu.util;

import org.joda.time.LocalDateTime;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.examples.AbstractExample;
import org.odata4j.format.FormatType;

public class ReplyHandler extends AbstractExample {

	private static final String serviceURL = "http://localhost:8886/res.svc/";
	private static String entitySet = "Reply";

	public static void addReply(int uid, int topic, String content){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity newReply = c.createEntity(entitySet)
		        .properties(OProperties.int32("userid", uid))
		        .properties(OProperties.int32("topicid", topic))
		        .properties(OProperties.string("content", content))
				.properties(OProperties.datetime("replytime", new LocalDateTime()))
		        .execute();
		reportEntity("created", newReply);
	}

	public static void main(String[] args) {

	}

}
