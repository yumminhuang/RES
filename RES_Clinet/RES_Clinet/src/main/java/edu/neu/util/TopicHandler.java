package edu.neu.util;

import org.core4j.Enumerable;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.exceptions.ServerErrorException;
import org.odata4j.format.FormatType;

import java.util.ArrayList;
import java.util.List;

import edu.neu.pattern.Topic;

public class TopicHandler extends AbstractHandler {

    private static String entitySet = "Topic";

    /**
     * @param titles
     * @param content
     * @param uid
     */
    public static void addTopic(String titles, String content, int uid) throws ServerErrorException {
        ODataConsumer c = ODataConsumers.create(serviceURL);
        int id = c.getEntities(entitySet).execute().toList().size() + 1;// Get current count and plus 1
        OEntity newTopic = c.createEntity(entitySet)
                .properties(OProperties.int32("id", id))
                .properties(OProperties.string("title", titles))
                .properties(OProperties.string("content", content))
                .properties(OProperties.int32("uid", uid)).execute();
        reportEntity("created", newTopic);
    }

    public static List<Topic> findAllTopic() {
        List<Topic> topics = new ArrayList<Topic>();
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        Enumerable<OEntity> topicEntities = c.getEntities(entitySet).execute();
        for (OEntity e : topicEntities) {
            Topic t = new Topic();
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("title"))
                    t.setTitle((String) p.getValue());
                else if (p.getName().equals("content"))
                    t.setContent((String) p.getValue());
                else if (p.getName().equals("id"))
                    t.setId((Integer) p.getValue());
                else if (p.getName().equals("uid"))
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

    public static List<Topic> findSomeTopic(int num) {
        List<Topic> topics = new ArrayList<Topic>();
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        Enumerable<OEntity> topicEntities = c.getEntities(entitySet).top(num).execute();
        for (OEntity e : topicEntities) {
            Topic t = new Topic();
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("title"))
                    t.setTitle((String) p.getValue());
                else if (p.getName().equals("content"))
                    t.setContent((String) p.getValue());
                else if (p.getName().equals("id"))
                    t.setId((Integer) p.getValue());
                else if (p.getName().equals("uid"))
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
     * @param keyword
     * @return
     */
    public static List<Topic> findTopic(String keyword) {
        List<Topic> topics = findAllTopic();
        for (int i = 0, len = topics.size(); i < len; ++i) {
            if (!topics.get(i).getContent().contains(keyword)) {
                topics.remove(i);
                --len;
                --i;
            }
        }
        return topics;
    }

    /**
     * @param uid
     * @return
     */
    public static List<Topic> findTopicByUser(int uid) {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        Enumerable<OEntity> topicEntities = c.getEntities(entitySet).filter("uid eq " + uid).execute();
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
        //List<Topic> topics = findTopic("B0BvjhFSMH1Qlv");
        List<Topic> topics = findAllTopic();
        for (Topic t : topics)
            System.out.println(t.toString());
    }

}
