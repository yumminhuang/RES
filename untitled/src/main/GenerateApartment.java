package main;
import java.util.Random;
/**
 * <pre>
 *     create table Apartment (
 id int primary key,
 number varchar(31) not null,
 address varchar(255),
 area double not null,
 owner int references User(id)
 );
 * </pre>
 * @Author: yummin
 * Date: 13-11-18
 */
public class GenerateApartment {

    public String generateAdd() {
        String streetNameBase = "abcdefghijklmnopqrstuvwxyz";
        String[] StreetType = {"Ave. ", "St. ", "Road "};
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7; i++) {
            int number = random.nextInt(streetNameBase.length());
            sb.append(streetNameBase.charAt(number));
        }
        sb.append(" " + StreetType[random.nextInt(3)] + random.nextInt(999));
        return sb.toString();
    }
    public String generateNumber(){
        String NameBase = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int number = random.nextInt(NameBase.length());
        sb.append(NameBase.charAt(number));
        sb.append(random.nextInt(999));
        return sb.toString();
    }

//    public int generateArea(){
//        Random random = new Random();
//        return random.nextInt();
//    }
}
