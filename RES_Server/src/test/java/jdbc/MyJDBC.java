package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * User: yummin
 * Date: 13-10-12
 */


public abstract class MyJDBC {
    /**
     * Close connection to database
     *
     * @param conn
     */
    public static void closeConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open connection to database
     *
     * @return
     */
    public static Connection openConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/Oh!Data";
        String user = "root";
        String pass = "123456";
        Connection con = null;
        try {
            //load connection
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}