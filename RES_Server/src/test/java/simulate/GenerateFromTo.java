package simulate;

import jdbc.MyJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: yummin
 * Date: 13-11-18
 */
public class GenerateFromTo {

    public static void main(String[] args) {
        GenerateFromTo gu = new GenerateFromTo();
        gu.InsertFrom(50);
        gu.InsertTo(50);
    }

    public void InsertFrom(int num) {
        Connection con = MyJDBC.openConnection();
        try {
            for (int i = 1; i <= num; i++) {
            	Statement stm = con.createStatement();
                String sql = "insert into FromUser (id) values (" + i + ");";
                //System.out.println(sql);
                stm.executeUpdate(sql);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertTo(int num) {
        Connection con = MyJDBC.openConnection();
        try {
            for (int i = 1; i <= num; i++) {
            	Statement  stm = con.createStatement();
                String sql = "insert into ToUser (id) values (" + i + ");";
                //System.out.println(sql);
                stm.executeUpdate(sql);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
