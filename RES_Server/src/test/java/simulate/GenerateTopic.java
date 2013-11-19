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
 * @Author: yummin Date: 13-11-18
 */
public class GenerateTopic {

	public String generateContent(int num) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
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

	public void Insert(int num) {
		Connection con = MyJDBC.openConnection();
		Statement stm = null;
		try {
			for (int i = 0; i < num; i++) {
				stm = con.createStatement();
				String title = generateContent(10), content = generateContent(20), image_path = "./image.jpg";
				String sql = "insert into Topic (title, content, image1, image2, postby) values ('"
						+ title
						+ "', '"
						+ content
						+ "', '"
						+ image_path
						+ "', '" + image_path + "', " + getUserID() + ");";
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
		GenerateTopic gu = new GenerateTopic();
		gu.Insert(50);
	}
}
