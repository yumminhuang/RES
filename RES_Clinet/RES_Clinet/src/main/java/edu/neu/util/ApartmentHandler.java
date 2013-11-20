package edu.neu.util;

import org.core4j.Enumerable;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.format.FormatType;

public class ApartmentHandler extends AbstractHandler {

	private static String entitySet = "Apartment";

	/**
	 *
	 * @param number
	 * @param address
	 * @param area
	 * @param owner
	 */
	public static void addApartment(String number, String address, double area, int owner){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity newApartment = c.createEntity(entitySet)
				.properties(OProperties.string("number", number))
				.properties(OProperties.string("address", address))
				.properties(OProperties.decimal("area", area))
				.properties(OProperties.int32("owner", owner)).execute();
		reportEntity("created", newApartment);
	}

	/**
	 *
	 * @param apartment
	 * @param number
	 * @param address
	 * @param area
	 * @param owner
	 */
	public void updateApartment(OEntity apartment, int apartmentid,String number, String address, double area, int owner){

		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		c.updateEntity(apartment)
		.properties(OProperties.string("number", number))
		.properties(OProperties.string("address", address))
		.properties(OProperties.decimal("area", area))
		.properties(OProperties.int32("owner", owner)).execute();
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public OEntity findApartment(int id){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity apartmentEntity = c.getEntity(entitySet, id).execute();
		return apartmentEntity;
	}

	// TODO : add return
	public static void findApartment(String address){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> apartments = c.getEntities(entitySet).filter("substringof(��" + address + "��, address) eq true").execute();
		reportEntities("Apartment", apartments);
	}

	public static void findApartment(double area){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> apartments = c.getEntities(entitySet).filter("address ge ��" + area + "��").execute();
		reportEntities("Apartment", apartments);
	}

	public static void findApartment(String address, double area){
		String cond1 = "address ge ��" + area + "��";
		String cond2 = "substringof(��" + address + "��, address) eq true";
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> apartments = c.getEntities(entitySet).filter(cond1 + " and " + cond2).execute();
		reportEntities("Apartment", apartments);
	}

	//public static

	public static void main(String[] args) {

	}

}
