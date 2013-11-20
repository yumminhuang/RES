package edu.neu.util;

import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.format.FormatType;

import edu.neu.pattern.User;

public class UserHandler extends AbstractHandler {

	private static String entitySet = "User";

	/**
	 * 
	 * @param name
	 * @param address
	 * @param phone
	 * @param email
	 * @param type
	 */
	public static void addUser(String name, String address, String phone,
			String email, String type) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL)
				.setFormatType(FormatType.JSON).build();
		OEntity newUser = c.createEntity(entitySet)
				.properties(OProperties.string("name", name))
				.properties(OProperties.string("telphone", phone))
				.properties(OProperties.string("Address", address))
				.properties(OProperties.string("email", email))
				.properties(OProperties.string("type", type)).execute();
		reportEntity("created", newUser);
	}

	/**
	 * 
	 * @param user
	 * @param name
	 * @param address
	 * @param phone
	 * @param email
	 * @param type
	 */
	public static void updateUser(OEntity user, String name, String address,
			String phone, String email, String type) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL)
				.setFormatType(FormatType.JSON).build();
		c.updateEntity(user).properties(OProperties.string("name", name))
				.properties(OProperties.string("telphone", phone))
				.properties(OProperties.string("Address", address))
				.properties(OProperties.string("email", email))
				.properties(OProperties.string("type", type)).execute();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @return
	 */
	public static User findUser(int id) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL)
				.setFormatType(FormatType.JSON).build();
		OEntity userEntity = c.getEntity(entitySet, id).execute();
		User user = new User();
		user.setId(id);
		for (OProperty<?> p : userEntity.getProperties()) {
			if (p.getName().equals("address")) {
				user.setAddress((String) p.getValue());
			} else if (p.getName().equals("telphone")) {
				user.setTelphone((Integer) p.getValue());
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

	public static int getIDFromName(String name) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL)
				.setFormatType(FormatType.JSON).build();
		OEntity id = c.getEntities(entitySet).filter("name eq '" + name + "'")
				.select("id").execute().first();
		return (Integer) id.getProperties().get(0).getValue();
	}

	public static void main(String[] args) {
		User u = findUser(17);
		System.out.println(u.toString());
	}

}
