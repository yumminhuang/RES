package simulate;

import jdbc.MyJDBC;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @Author: yummin Date: 13-11-18
 */
public class GenerateReply {

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

	public int getTopicID() {
		Connection con = MyJDBC.openConnection();
		Statement stm = null;
		List<Integer> ids = new ArrayList<Integer>();
		try {
			stm = con.createStatement();
			String sql = "select id from Topic";
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

	public Date generateDate() {
		Random rand = new Random();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 10, 1);
		long start = cal.getTimeInMillis();
		cal.set(2013, 12, 1);
		long end = cal.getTimeInMillis();
		Date d = new Date(start + (long) (rand.nextDouble() * (end - start)));
		return d;
	}

	public void Insert(int num) {
		Connection con = MyJDBC.openConnection();
		Statement stm = null;
		try {
			for (int i = 0; i < num; i++) {
				stm = con.createStatement();
				String content = generateContent(20), image_path = "./image.jpg";
				String sql = "insert into Reply (userid, topicid, content, postby, replytime, image1, image2) values ('"
						+ getUserID()
						+ "', '"
						+ getTopicID()
						+ "', '"
						+ content
						+ "', '"
						+ generateDate()
						+ "', '"
						+ image_path + "', '" + image_path + "');";
				System.out.println(sql);
				// stm.executeUpdate(sql);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GenerateReply gu = new GenerateReply();
		gu.Insert(5);
	}
}
