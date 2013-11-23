package util;

import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.exceptions.NotFoundException;
import org.odata4j.exceptions.ServerErrorException;
import org.odata4j.format.FormatType;

import pattern.User;

public class UserHandler extends AbstractHandler {

    private static String entitySet = "User";

    /**
     * TODO Fix
     * @param name
     * @param address
     * @param phone
     * @param email
     * @param type
     */
    public static void addUser(String name, String address, String phone,
                               String email, String type) throws ServerErrorException {
        ODataConsumer c = ODataConsumers.create(serviceURL);
        OEntity newUser = c.createEntity(entitySet).properties(OProperties.int32("id", 50))
                .properties(OProperties.string("name", name))
                .properties(OProperties.string("phone", phone))
                .properties(OProperties.string("address", address))
                .properties(OProperties.string("email", email))
                .properties(OProperties.string("type", type))
                .execute();
        reportEntity("created", newUser);
    }

    /**
     * @param id
     * @param name
     * @param address
     * @param phone
     * @param email
     * @param type
     */
    public static void updateUser(int id, String name, String address,
                                  String phone, String email, String type) throws ServerErrorException {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        c.mergeEntity(entitySet, id).properties(OProperties.string("name", name))
                .properties(OProperties.string("phone", phone))
                .properties(OProperties.string("address", address))
                .properties(OProperties.string("email", email))
                .properties(OProperties.string("type", type))
                .execute();
    }

    /**
     * @param id
     * @return
     */
    public static User findUser(int id) {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        OEntity userEntity = c.getEntity(entitySet, id).execute();
        User user = new User();
        user.setId(id);
        for (OProperty<?> p : userEntity.getProperties()) {
            if (p.getName().equals("address")) {
                user.setAddress((String) p.getValue());
            } else if (p.getName().equals("phone")) {
                user.setPhone((String) p.getValue());
            } else if (p.getName().equals("name")) {
                user.setName((String) p.getValue());
            } else if (p.getName().equals("email")) {
                user.setEmail((String) p.getValue());
            } else if (p.getName().equals("type")) {
                user.setType((String) p.getValue());
            }
        }
        return user;
    }

    /**
     * @param name
     * @return
     */
    public static int getIDFromName(String name) throws NotFoundException {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        OEntity id = c.getEntities(entitySet).filter("name eq '" + name + "'").select("id").execute().first();
        return (Integer) id.getProperties().get(0).getValue();
    }


    public static String getNameFromID(int id) {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        OEntity userEntity = c.getEntity(entitySet, id).execute();
        return (String) userEntity.getProperty("name").getValue();
    }

    public static void main(String[] args) {
//		User u = findUser(17);
//		System.out.println(u.toString());
//		System.out.println("Johnson's ID is" + getIDFromName("Johnson"));
    	UserHandler.addUser("name", "address", "110", "a@m", "Agent");
    }

}
