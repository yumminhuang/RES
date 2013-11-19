package util;

import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.examples.AbstractExample;
import org.odata4j.format.FormatType;

public class User extends AbstractExample {

	private static final String serviceURL = "http://localhost:8886/res.svc/";
	private static String entitySet = "User";

	public static void addUser(String name, String address, String phone,
			String email, String type) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity newUser = c.createEntity(entitySet)
				.properties(OProperties.string("name", name))
				.properties(OProperties.string("telphone", phone))
				.properties(OProperties.string("Address", address))
				.properties(OProperties.string("email", email))
				.properties(OProperties.string("type", type)).execute();
		reportEntity("created", newUser);
	}
	
	public static int getIDFromName(String name) {
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity id = c.getEntities(entitySet).filter("name eq '" + name +"'").select("id").execute().first();
		return (Integer) id.getProperties().get(0).getValue();
	}

	public static void main(String[] args) {
		// addUser("a", "add", "123", "@", "t");
	}

}
