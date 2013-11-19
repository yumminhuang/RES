package util;

import java.util.Calendar;
import java.util.Date;

import org.core4j.Enumerable;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.examples.AbstractExample;
import org.odata4j.format.FormatType;

public class Message extends AbstractExample {
	
	private static final String serviceURL = "http://localhost:8886/res.svc/";
	private static String entitySet = "Message";
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param content
	 */
	public static void addMessage(int from, int to, String content){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		OEntity newMessage = c.createEntity(entitySet)
				.properties(OProperties.int32("messagefrom", from))
				.properties(OProperties.int32("messageto", to))
				.properties(OProperties.string("content", content))
				.properties(OProperties.datetime("messagetime", now)).execute();
		reportEntity("created", newMessage);
	}
	
	/**
	 * 
	 * @param to
	 */
	public static void getInbox(int to){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> inbox = c.getEntities(entitySet).filter("messageto eq '" + to +"'").execute();
	    reportEntities("Inbox", inbox);
	}

	public static void main(String[] args) {
		getInbox(40);
	}

}
