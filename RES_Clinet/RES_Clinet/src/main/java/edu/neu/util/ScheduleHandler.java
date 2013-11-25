package edu.neu.util;

import org.core4j.Enumerable;
import org.joda.time.LocalDateTime;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.exceptions.ServerErrorException;
import org.odata4j.format.FormatType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.neu.pattern.Schedule;

public class ScheduleHandler extends AbstractHandler {

    private static String entitySet = "Schedule";

    /**
     * @param from
     * @param to
     * @param content
     * @param time
     */
    public static void addMeeting(int from, int to, String content, LocalDateTime time) throws ServerErrorException {
        ODataConsumer c = ODataConsumers.create(serviceURL);
        int id = c.getEntities(entitySet).execute().toList().size() + 1;// Get current count and plus 1
        OEntity newMeeting = c.createEntity(entitySet)
                .properties(OProperties.int32("id", id))
                .properties(OProperties.int32("sfrom", from))
                .properties(OProperties.int32("sto", to))
                .properties(OProperties.datetime("stime", time))
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
        Enumerable<OEntity> myMeeting = c.getEntities(entitySet).filter("sto eq " + id + " or sfrom eq " + id).execute();
        for (OEntity e : myMeeting) {
            Schedule s = new Schedule();
            s.setId(id);
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("sfrom"))
                    s.setSchedulefrom((Integer) p.getValue());
                else if (p.getName().equals("sto"))
                    s.setScheduleto((Integer) p.getValue());
                else if (p.getName().equals("stime"))
                    s.setScheduletime(((LocalDateTime) p.getValue()).toString("MM/dd/yyyy"));
                else if (p.getName().equals("content"))
                    s.setContent((String) p.getValue());
            }
            schedule.add(s);
        }
        return schedule;
    }

    /**
     * TODO: problem caused by seconds compare with date
     *
     * @param date
     * @return
     */
    public static List<Schedule> getMeetingByTime(LocalDateTime date) {
        List<Schedule> schedule = new ArrayList<Schedule>();
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        Enumerable<OEntity> myMeeting = c.getEntities(entitySet).filter("stime ge '" + date + "'").execute();
        for (OEntity e : myMeeting) {
            Schedule s = new Schedule();
            s.setScheduletime(date.toString("MM/dd/yyyy"));
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("sfrom"))
                    s.setSchedulefrom((Integer) p.getValue());
                else if (p.getName().equals("sto"))
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
        System.out.println(new LocalDateTime().toString("mm/dd/yyyy"));
        List<Schedule> inbox = getMeetingByTime(new LocalDateTime());
        for (Schedule m : inbox)
            System.out.println(m.toString());
    }

}
