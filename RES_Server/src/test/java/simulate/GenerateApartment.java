package simulate;

import jdbc.MyJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     create table Apartment (
 * id int primary key,
 * number varchar(31) not null,
 * address varchar(255),
 * area double not null,
 * owner int references User(id)
 * );
 * </pre>
 * 
 * @Author: yummin Date: 13-11-18
 */
public class GenerateApartment {

	public String generateAdd() {
		String streetNameBase = "abcdefghijklmnopqrstuvwxyz";
		String[] StreetType = { "Ave. ", "St. ", "Road " };
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 7; i++) {
			int number = random.nextInt(streetNameBase.length());
			sb.append(streetNameBase.charAt(number));
		}
		sb.append(" " + StreetType[random.nextInt(3)] + random.nextInt(999));
		return sb.toString();
	}

	public String generateNumber() {
		String NameBase = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		int number = random.nextInt(NameBase.length());
		sb.append(NameBase.charAt(number));
		sb.append(random.nextInt(999));
		return sb.toString();
	}

	public int generateArea() {
		int low = 215;
		int high = 2152;
		return (int) (low + (high - low) * Math.random());
	}

	public int getUserID() {
		Connection con = MyJDBC.openConnection();
		Statement stm = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
			stm = con.createStatement();
			String sql = "select id from User where type != 'Tenant'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				ids.add(rs.getInt(1));
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Random random = new Random();
		return ids.get(random.nextInt(ids.size()));
	}

	public void Insert(int num) {
		Connection con = MyJDBC.openConnection();
		Statement stm = null;
		try {
			for (int i = 0; i < num; i++) {
				stm = con.createStatement();
				String sql = "insert into Apartment (id,number, address, area, owner) values ("
						+ (i + 1)
						+ ",'"
						+ generateNumber()
						+ "', '"
						+ generateAdd()
						+ "', "
						+ generateArea()
						+ ", "
						+ getUserID() + ");";
				// System.out.println(sql);
				stm.executeUpdate(sql);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GenerateApartment gu = new GenerateApartment();
		gu.Insert(40);
	}
}
