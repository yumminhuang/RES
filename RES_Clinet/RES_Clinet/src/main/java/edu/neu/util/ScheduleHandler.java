package edu.neu.util;

import java.util.Date;

import org.core4j.Enumerable;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.examples.AbstractExample;
import org.odata4j.format.FormatType;

public class ScheduleHandler extends AbstractExample {

	private static final String serviceURL = "http://localhost:8886/res.svc/";
	private static String entitySet = "Schedule";

	/**
	 *
	 * @param from
	 * @param to
	 * @param content
	 * @param time
	 */
	public static void addMeeting(int from, int to, String content, Date time) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity newMeeting = c.createEntity(entitySet)
				.properties(OProperties.int32("schedulefrom", from))
				.properties(OProperties.int32("scheduleto", to))
				.properties(OProperties.datetime("messagetime", time))
				.properties(OProperties.string("content", content)).execute();
		reportEntity("created", newMeeting);
	}

	/**
	 * TODO : add return
	 * @param id
	 */
	public static void getMyMeeting(int id) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL)
				.setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> myMeeting = c.getEntities(entitySet)
				.filter("scheduleto eq '" + id + "' or schedulefrom eq '" + id + "'").execute();
		reportEntities("", myMeeting);
	}

	public static void main(String[] args) {
		getMyMeeting(15);
	}

}
