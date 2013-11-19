package simulate;

import jdbc.MyJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @Author: yummin
 * Date: 13-11-18
 */
public class GenerateFromTo {

    public static void main(String[] args) {
        GenerateFromTo gu = new GenerateFromTo();
        gu.InsertFrom(1);
        gu.InsertTo(1);
    }

    public int getFromUserID() {
        Connection con = MyJDBC.openConnection();
        Statement stm = null;
        List<Integer> ids = new ArrayList<Integer>();
        try {
            stm = con.createStatement();
            String sql1 = "select messagefrom from Message";
            ResultSet rs1 = stm.executeQuery(sql1);
            while (rs1.next()) {
                ids.add(rs1.getInt(1));
            }
            stm = con.createStatement();
            String sql2 = "select schedulefrom from Schedule";
            ResultSet rs2 = stm.executeQuery(sql2);
            while (rs2.next()) {
                ids.add(rs2.getInt(1));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return ids.get(random.nextInt(ids.size()));
    }

    public int getToUserID() {
        Connection con = MyJDBC.openConnection();
        Statement stm = null;
        List<Integer> ids = new ArrayList<Integer>();
        try {
            stm = con.createStatement();
            String sql1 = "select messageto from Message";
            ResultSet rs1 = stm.executeQuery(sql1);
            while (rs1.next()) {
                ids.add(rs1.getInt(1));
            }
            stm = con.createStatement();
            String sql2 = "select scheduleto from Schedule";
            ResultSet rs2 = stm.executeQuery(sql2);
            while (rs2.next()) {
                ids.add(rs2.getInt(1));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return ids.get(random.nextInt(ids.size()));
    }

    public void InsertFrom(int num) {
        Connection con = MyJDBC.openConnection();
        Statement stm = null;
        try {
            for (int i = 0; i < num; i++) {
                stm = con.createStatement();
                String sql = "insert into FromUser (id) values " + getFromUserID();
                System.out.println(sql);
                //stm.executeUpdate(sql);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertTo(int num) {
        Connection con = MyJDBC.openConnection();
        Statement stm = null;
        try {
            for (int i = 0; i < num; i++) {
                stm = con.createStatement();
                String sql = "insert into FromUser (id) values " + getToUserID();
                System.out.println(sql);
                //stm.executeUpdate(sql);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
