package main;

import jdbc.MyJDBC;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * <pre>
 * create table User(
 * id int primary key,
 * name varchar(255) not null,
 * telphone int not null,
 * Address varchar(255),
 * email varchar(255),
 * type enum('Landlord','Tenant','Agent')
 * );
 * </pre>
 *
 * @Author: yummin
 * Date: 13-11-18
 */
public class GenerateUser {

    private String file = "/Users/yummin/Program/Java/RES/untitled/name.txt";
    private String[] type = {"Landlord", "Tenant", "Agent"};

    public static void main(String[] args) {
        GenerateUser gu = new GenerateUser();
        gu.Insert(49);
    }

    public String generateName() {
        FileReader reader = null;
        Random r = new Random();
        int j = r.nextInt(70);
        try {
            reader = new FileReader(file);

            BufferedReader br = new BufferedReader(reader);
            String s1 = null;
            int i = 0;
            while ((s1 = br.readLine()) != null) {
                i++;
                if (i < j) continue;
                return s1;
            }
            br.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int generatePhone() {
        int t = new java.util.Random().nextInt(99999);
        if (t < 10000) t += 10000;
        return t;
    }

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

    public String generateEmail() {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 8; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        sb.append("@");
        for (int i = 0; i < 3; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        sb.append(".com");
        return sb.toString();
    }

    public String getType() {
        Random random = new Random();
        int i = random.nextInt(3);
        return type[i];
    }

    public void Insert(int num) {
        Connection con = MyJDBC.openConnection();
        Statement stm = null;
        try {
            for (int i = 0; i < num; i++) {
                stm = con.createStatement();
                String sql = "insert into User (name, telphone, Address, email, type) values ('" + generateName() + "', "
                        + generatePhone() + ", '" + generateAdd() + "', '"
                        + generateEmail() + "', '" + getType() + "');";
                //System.out.print(sql);
                stm.executeUpdate(sql);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
