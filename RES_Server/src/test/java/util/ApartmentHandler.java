package util;

import java.util.ArrayList;
import java.util.List;

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
	public static void addApartment(String number, String address, int area, int owner){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		OEntity newApartment = c.createEntity(entitySet)
				.properties(OProperties.string("number", number))
				.properties(OProperties.string("address", address))
				.properties(OProperties.int32("area", area))
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
	public void updateApartment(OEntity apartment, int apartmentid,String number, String address, int area, int owner){
		
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		c.updateEntity(apartment)
		.properties(OProperties.string("number", number))
		.properties(OProperties.string("address", address))
		.properties(OProperties.int32("area", area))
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
				a.setArea((Integer)p.getValue());
			} else if(p.getName().equals("owner")) {
				a.setOwner((Integer)p.getValue());
			}
		}
		return a;
	}
	
	/**
	 * TODO: fix problem, seems caused by substringof
	 * @param address
	 * @return
	 */
	public static List<Apartment> findApartments(String address){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		List<OEntity> apartmentEntities = c.getEntities(entitySet).filter("substringof('" + address + "',address)").execute().toList();
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
					a.setArea((Integer)p.getValue());
				} else if(p.getName().equals("owner")) {
					a.setOwner((Integer)p.getValue());
				}
			}
			apartments.add(a);
		}
		return apartments;
	}
	
	/**
	 * 
	 * @param area
	 * @return
	 */
	public static List<Apartment> findApartments(int area){
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		List<OEntity> apartmentEntities = c.getEntities(entitySet).filter("area ge " + area).execute().toList();
		List<Apartment> apartments = new ArrayList<Apartment>();
		for (OEntity e : apartmentEntities) {
			Apartment a = new Apartment();
			for (OProperty<?> p : e.getProperties()) {
				if (p.getName().equals("id")) {
					a.setId((Integer) p.getValue());
				} else if (p.getName().equals("number")) {
					a.setNumber((String)p.getValue());
				} else if (p.getName().equals("address")) {
					a.setAddress((String)p.getValue());
				} else if(p.getName().equals("owner")) {
					a.setOwner((Integer)p.getValue());
				} else if (p.getName().equals("area")) {
					a.setArea((Integer)p.getValue());
				}
			}
			apartments.add(a);
		}
		return apartments;
	}
	
	/**
	 * TODO: fix problem
	 * @param address
	 * @param area
	 * @return
	 */
	public static List<Apartment> findApartments(String address, int area){
		String cond1 = "area ge " + area;
		String cond2 = "substringof('" + address + "', address)";
		ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
		List<OEntity> apartmentEntities = c.getEntities(entitySet).filter(cond1 + " and " + cond2).execute().toList();
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
				} else if (p.getName().equals("address")) {
					a.setAddress((String)p.getValue());
				} else if(p.getName().equals("owner")) {
					a.setOwner((Integer)p.getValue());
				} else if (p.getName().equals("area")) {
					a.setArea((Integer)p.getValue());
				}
			}
			apartments.add(a);
		}
		return apartments;
	} 

	public static void main(String[] args) {
		//System.out.println(findApartment(10).toString());
		List<Apartment> aps = findApartments("jwjbdin Ave. 544"); 
		//List<Apartment> aps = findApartments(1900);
		//List<Apartment> aps = findApartments("Ave.", 1700);
		for(Apartment a : aps) 
			System.out.println(a.toString());
	}

}
