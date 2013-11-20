package util;

import java.util.ArrayList;
import java.util.List;

import org.core4j.Enumerable;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.examples.AbstractExample;
import org.odata4j.format.FormatType;

import pattern.Apartment;

public class ApartmentHandler extends AbstractExample {
	
	private static final String serviceURL = "http://localhost:8886/res.svc/";
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
	public static Apartment findApartment(int id){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity apartmentEntity = c.getEntity(entitySet, id).execute();
		Apartment a = new Apartment();
		a.setId(id);
		for (OProperty<?> p : apartmentEntity.getProperties()) {
			if (p.getName().equals("address")) {
				a.setAddress((String) p.getValue());
			} else if (p.getName().equals("number")) {
				a.setNumber((String)p.getValue());
			} else if (p.getName().equals("area")) {
				a.setArea((Double)p.getValue());
			} else if(p.getName().equals("owner")) {
				a.setOwner((Integer)p.getValue());
			}
		}
		return a;
	}
	
	public static List<Apartment> findApartment(String address){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> apartmentEntities = c.getEntities(entitySet).filter("substringof(¡®" + address + "¡¯, address) eq true").execute();
		List<Apartment> apartments = new ArrayList<Apartment>();
		for (OEntity e : apartmentEntities) {
			Apartment a = new Apartment();
			a.setAddress(address);
			for (OProperty<?> p : e.getProperties()) {
				if (p.getName().equals("id")) {
					a.setId((Integer) p.getValue());
				} else if (p.getName().equals("number")) {
					a.setNumber((String)p.getValue());
				} else if (p.getName().equals("area")) {
					a.setArea((Double)p.getValue());
				} else if(p.getName().equals("owner")) {
					a.setOwner((Integer)p.getValue());
				}
			}
			apartments.add(a);
		}
		return apartments;
	}
	
	public static List<Apartment> findApartment(double area){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> apartmentEntities = c.getEntities(entitySet).filter("address ge ¡®" + area + "¡¯").execute();
		List<Apartment> apartments = new ArrayList<Apartment>();
		for (OEntity e : apartmentEntities) {
			Apartment a = new Apartment();
			a.setArea(area);
			for (OProperty<?> p : e.getProperties()) {
				if (p.getName().equals("id")) {
					a.setId((Integer) p.getValue());
				} else if (p.getName().equals("number")) {
					a.setNumber((String)p.getValue());
				} else if (p.getName().equals("address")) {
					a.setAddress((String)p.getValue());
				} else if(p.getName().equals("owner")) {
					a.setOwner((Integer)p.getValue());
				}
			}
			apartments.add(a);
		}
		return apartments;
	}
	
	public static List<Apartment> findApartment(String address, double area){
		String cond1 = "address ge ¡®" + area + "¡¯";
		String cond2 = "substringof(¡®" + address + "¡¯, address) eq true";
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		Enumerable<OEntity> apartmentEntities = c.getEntities(entitySet).filter(cond1 + " and " + cond2).execute();
		List<Apartment> apartments = new ArrayList<Apartment>();
		for (OEntity e : apartmentEntities) {
			Apartment a = new Apartment();
			a.setArea(area);
		    a.setAddress(address);
			for (OProperty<?> p : e.getProperties()) {
				if (p.getName().equals("id")) {
					a.setId((Integer) p.getValue());
				} else if (p.getName().equals("number")) {
					a.setNumber((String)p.getValue());
				} else if(p.getName().equals("owner")) {
					a.setOwner((Integer)p.getValue());
				}
			}
			apartments.add(a);
		}
		return apartments;
	} 

	public static void main(String[] args) {

	}

}
