package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pattern.Schedule;

import org.core4j.Enumerable;
import org.joda.time.LocalDateTime;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.examples.AbstractExample;
import org.odata4j.format.FormatType;

public class ScheduleHandler extends AbstractExample{

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
	 * @param id
	 * @return
	 */
	public static List<Schedule> getMeetingById(int id) {
		List<Schedule> schedule = new ArrayList<Schedule>();
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> myMeeting = c.getEntities(entitySet).filter("scheduleto eq " + id + " or schedulefrom eq " + id).execute();
		for (OEntity e : myMeeting) {
			Schedule s = new Schedule();
			s.setId(id);
			for (OProperty<?> p : e.getProperties()) {
				if (p.getName().equals("schedulefrom"))
					s.setSchedulefrom((Integer) p.getValue());
				else if (p.getName().equals("scheduleto"))
					s.setScheduleto((Integer) p.getValue());
				else if (p.getName().equals("scheduletime"))
					s.setScheduletime((LocalDateTime) p.getValue());
				else if (p.getName().equals("content"))
					s.setContent((String) p.getValue());
			}
			schedule.add(s);
		}
		return schedule;
	}
	
	/**
	 * TODO: problem caused by seconds compare with date
	 * @param date
	 * @return
	 */
	public static List<Schedule> getMeetingByTime(LocalDateTime date) {
		List<Schedule> schedule = new ArrayList<Schedule>();
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> myMeeting = c.getEntities(entitySet).filter("scheduletime ge '" + date + "'").execute();
		for (OEntity e : myMeeting) {
			Schedule s = new Schedule();
			s.setScheduletime(date);
			for (OProperty<?> p : e.getProperties()) {
				if (p.getName().equals("schedulefrom"))
					s.setSchedulefrom((Integer) p.getValue());
				else if (p.getName().equals("scheduleto"))
					s.setScheduleto((Integer) p.getValue());
				else if (p.getName().equals("id"))
					s.setId((Integer) p.getValue());
				else if (p.getName().equals("content"))
					s.setContent((String) p.getValue());
			}
			schedule.add(s);
		}
		return schedule;
	}

	public static void main(String[] args) {
		//List<Schedule> inbox = getMeetingById(16);
		System.out.println(new LocalDateTime().toString("YYYYMMDD"));
		List<Schedule> inbox = getMeetingByTime(new LocalDateTime());
		for(Schedule m : inbox)
			System.out.println(m.toString());
	}

}
