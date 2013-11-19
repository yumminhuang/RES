package simulate;

import jdbc.MyJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     create table Message(
 * id int primary key AUTO_INCREMENT,
 * messagefrom  int references FromUser(id),
 * messageto  int references ToUser(id),
 * content text not null,
 * messagetime date not null
 * );
 * </pre>
 * 
 * @Author: yummin Date: 13-11-18
 */
public class GenerateMessage {

	public Date generateDate() {
		Random rand = new Random();
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 10, 1);
		long start = cal.getTimeInMillis();
		cal.set(2013, 12, 1);
		long end = cal.getTimeInMillis();
		Date d = new Date(start + (long) (rand.nextDouble() * (end - start)));
		return d;
	}

	public int getUserID() {
		Connection con = MyJDBC.openConnection();
		Statement stm = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
			stm = con.createStatement();
			String sql = "select id from User";
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

	public String generateContent() {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public void Insert(int num) {
		Connection con = MyJDBC.openConnection();
		Statement stm = null;
		try {
			for (int i = 0; i < num; i++) {
				stm = con.createStatement();
				int from = getUserID(), to;
				do {
					to = getUserID();
				} while (from == to);
				String sql = "insert into Message (messagefrom, messageto, content, messagetime) values ("
						+ from
						+ ", "
						+ to
						+ ", '"
						+ generateContent()
						+ "', '"
						+ generateDate() + "');";
				// System.out.println(sql);
				stm.executeUpdate(sql);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
	}

	public static void main(String[] args) {
		GenerateMessage gu = new GenerateMessage();
		gu.Insert(50);
	}
}
