package edu.neu.util;

import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperties;
import org.odata4j.core.OProperty;
import org.odata4j.exceptions.ServerErrorException;
import org.odata4j.format.FormatType;

import java.util.ArrayList;
import java.util.List;

import edu.neu.pattern.Apartment;

public class ApartmentHandler extends AbstractHandler {
    private static String entitySet = "Apartment";

    /**
     * @param number
     * @param address
     * @param area
     * @param owner
     */
    public static void addApartment(String number, String address, int area, int owner) throws ServerErrorException {
        ODataConsumer c = ODataConsumers.create(serviceURL);
        int id = c.getEntities(entitySet).execute().toList().size() + 1;// Get current count and plus 1
        c.createEntity(entitySet)
                .properties(OProperties.int32("id", id))
                .properties(OProperties.string("number", number))
                .properties(OProperties.string("address", address))
                .properties(OProperties.int32("area", area))
                .properties(OProperties.int32("owner", owner)).execute();
    }

    /**
     * @param apartment
     * @param number
     * @param address
     * @param area
     * @param owner
     */
    public void updateApartment(OEntity apartment, int apartmentid, String number, String address, int area, int owner) throws ServerErrorException {

        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        c.updateEntity(apartment)
                .properties(OProperties.string("number", number))
                .properties(OProperties.string("address", address))
                .properties(OProperties.int32("area", area))
                .properties(OProperties.int32("owner", owner)).execute();
    }

    /**
     * @param id
     * @return
     */
    public static Apartment findApartment(int id) {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        OEntity apartmentEntity = c.getEntity(entitySet, id).execute();
        Apartment a = new Apartment();
        a.setId(id);
        for (OProperty<?> p : apartmentEntity.getProperties()) {
            if (p.getName().equals("address")) {
                a.setAddress((String) p.getValue());
            } else if (p.getName().equals("number")) {
                a.setNumber((String) p.getValue());
            } else if (p.getName().equals("area")) {
                a.setArea((Integer) p.getValue());
            } else if (p.getName().equals("owner")) {
                a.setOwner((Integer) p.getValue());
            }
        }
        return a;
    }

    /**
     *
     * @param address
     * @return
     */
    public static List<Apartment> findApartments(String address) {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        List<OEntity> apartmentEntities = c.getEntities(entitySet).execute().toList();
        List<Apartment> apartments = new ArrayList<Apartment>();
        for (OEntity e : apartmentEntities) {
            String temp = (String) e.getProperty("address").getValue();
            if (!temp.contains(address))
                continue;
            Apartment a = new Apartment();
            a.setAddress(temp);
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("id")) {
                    a.setId((Integer) p.getValue());
                } else if (p.getName().equals("number")) {
                    a.setNumber((String) p.getValue());
                } else if (p.getName().equals("area")) {
                    a.setArea((Integer) p.getValue());
                } else if (p.getName().equals("owner")) {
                    a.setOwner((Integer) p.getValue());
                }
            }
            apartments.add(a);
        }
        return apartments;
    }

    /**
     * @param area
     * @return
     */
    public static List<Apartment> findApartments(int area) {
        ODataConsumer c = ODataConsumers.newBuilder(serviceURL).setFormatType(FormatType.JSON).build();
        List<OEntity> apartmentEntities = c.getEntities(entitySet).filter("area ge " + area).execute().toList();
        List<Apartment> apartments = new ArrayList<Apartment>();
        for (OEntity e : apartmentEntities) {
            Apartment a = new Apartment();
            for (OProperty<?> p : e.getProperties()) {
                if (p.getName().equals("id")) {
                    a.setId((Integer) p.getValue());
                } else if (p.getName().equals("number")) {
                    a.setNumber((String) p.getValue());
                } else if (p.getName().equals("address")) {
                    a.setAddress((String) p.getValue());
                } else if (p.getName().equals("owner")) {
                    a.setOwner((Integer) p.getValue());
                } else if (p.getName().equals("area")) {
                    a.setArea((Integer) p.getValue());
                }
            }
            apartments.add(a);
        }
        return apartments;
    }

    /**
     * @param address
     * @param area
     * @return
     */
    public static List<Apartment> findApartments(String address, int area) {
        List<Apartment> apartments = findApartments(area);
        for (int i = 0, len = apartments.size(); i < len; ++i) {
            if (!apartments.get(i).getAddress().contains(address)) {
                apartments.remove(i);
                --len;
                --i;
            }
        }
        return apartments;
    }

}
