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
import java.util.List;

import edu.neu.pattern.Message;

public class MessageHandler extends AbstractHandler {

    private static String entitySet = "Message";

    /**
     * @param from
     * @param to
     * @param content
     */
    public static void addMessage(int from, int to, String content) throws ServerErrorException {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        OEntity newMessage = c.createEntity(entitySet)
                .properties(OProperties.int32("mfrom", from))
                .properties(OProperties.int32("mto", to))
                .properties(OProperties.string("content", content))
                .properties(OProperties.datetime("mtime", new LocalDateTime())).execute();
        reportEntity("created", newMessage);
    }

    /**
     * @param to
     * @return
     */
    public static List<Message> getInbox(int to) {
        List<Message> mess = new ArrayList<Message>();
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        Enumerable<OEntity> inbox = c.getEntities(entitySet).filter("mto eq " + to).execute();
        for (OEntity e : inbox) {
            Message m = new Message();
            m.setMessageto(to);
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("mfrom")) {
                    m.setMessagefrom((Integer) p.getValue());
                } else if (p.getName().equals("mtime")) {
                    m.setMessagetime(((LocalDateTime) p.getValue()).toString("MM/dd/yyyy"));
                } else if (p.getName().equals("content")) {
                    m.setContent((String) p.getValue());
                } else if (p.getName().equals("id"))
                    m.setId((Integer) p.getValue());
            }
            mess.add(m);
        }
        return mess;
    }

    /**
     * @param from
     * @return
     */
    public static List<Message> getOutbox(int from) {
        List<Message> mess = new ArrayList<Message>();
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        Enumerable<OEntity> inbox = c.getEntities(entitySet).filter("mfrom eq " + from).execute();
        for (OEntity e : inbox) {
            Message m = new Message();
            m.setMessagefrom(from);
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("mto")) {
                    m.setMessageto((Integer) p.getValue());
                } else if (p.getName().equals("mtime")) {
                    m.setMessagetime(((LocalDateTime) p.getValue()).toString("MM/dd/yyyy"));
                } else if (p.getName().equals("content")) {
                    m.setContent((String) p.getValue());
                } else if (p.getName().equals("id"))
                    m.setId((Integer) p.getValue());
            }
            mess.add(m);
        }
        return mess;
    }

    public static void main(String[] args) {
        List<Message> inbox = getInbox(16);
        List<Message> outbox = getOutbox(46);
        for (Message m : inbox)
            System.out.println(m.toString());
        System.out.println("======");
        for (Message m : outbox)
            System.out.println(m.toString());
    }

}
