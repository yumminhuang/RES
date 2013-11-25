package util;

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

import pattern.Reply;

public class ReplyHandler extends AbstractHandler {

    private static String entitySet = "Reply";

    public static void addReply(int uid, int topic, String content) throws ServerErrorException {
        ODataConsumer c = ODataConsumers.create(serviceURL);
        int id = c.getEntities(entitySet).execute().toList().size() + 1;// Get current count and plus 1
        OEntity newReply = c.createEntity(entitySet)
        		.properties(OProperties.int32("id", id))
                .properties(OProperties.int32("uid", uid))
                .properties(OProperties.int32("tid", topic))
                .properties(OProperties.string("content", content))
                .properties(OProperties.datetime("rtime", new LocalDateTime()))
                .execute();
        reportEntity("created", newReply);
    }

    /**
     * @param topicid
     * @return
     */
    public static List<Reply> findReplyFromTopic(int topicid) {
        List<Reply> reply = new ArrayList<Reply>();
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        Enumerable<OEntity> replies = c.getEntities(entitySet).filter("tid eq " + topicid).execute();
        for (OEntity e : replies) {
            Reply r = new Reply();
            r.setTopicId(topicid);
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("id"))
                    r.setId((Integer) p.getValue());
                else if (p.getName().equals("uid"))
                    r.setUserId((Integer) p.getValue());
                else if (p.getName().equals("content"))
                    r.setContent((String) p.getValue());
                else if (p.getName().equals("rtime"))
                    r.setReplytime(((LocalDateTime) p.getValue()).toString("MM/dd/yyyy"));
                else if (p.getName().equals("image1"))
                    r.setImage1((String) p.getValue());
                else if (p.getName().equals("image2"))
                    r.setImage2((String) p.getValue());
            }
            reply.add(r);
        }
        return reply;
    }

    public static void main(String[] args) {
//        List<Reply> inbox = findReplyFromTopic(6);
//        for (Reply m : inbox)
//            System.out.println(m.toString());
    	addReply(1, 1, "ABC");
    }

}
