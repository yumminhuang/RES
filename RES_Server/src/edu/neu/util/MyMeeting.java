package edu.neu.util;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

public class MyMeeting implements DBHandler{
	BufferedReader in;
	PrintWriter out;

    /**
     *
     * @param in
     * @param out
     */
	public MyMeeting(BufferedReader in, PrintWriter out){
		this.in = in;
		this.out = out;
	}

    /**
     * Schedule a new meeting
     * @throws Exception
     */
    @Override
    public void add() throws Exception {
        String secretkey, encodedMsg, md, CT, sql;
        System.out.println("Schedule a new meeting...");
        secretkey = in.readLine();
        encodedMsg = in.readLine();
        md = in.readLine();
        CT = in.readLine();
        // Connect to database
        Connection con = MyJDBC.openConnection();
        if(con == null){
            System.out.println("Cannot connect to database");
            out.println("false");
            return;
        }
        Statement statement = con.createStatement();
        sql = "insert into meetings set "+
                "secretkey = '"+secretkey+"',"+
                "encodedMsg = '"+encodedMsg+"', "+
                "md = '"+md+"', "+
                "CT = '"+CT+"'  ";
        // return the number of updated rows
        int re = statement.executeUpdate(sql);
        if(re == 0){
            System.out.println("Schedule failed...");
            out.println("false");
        } else {
            System.out.println("Schedule successfully...");
            out.println("success");
        }
        // Close connection
        MyJDBC.closeConn(con);
        System.out.println("Successfully scheduled a meeting...");
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void update() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void search() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
