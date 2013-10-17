package edu.neu.main;

import edu.neu.util.MyJDBC;
import edu.neu.util.MyMeeting;
import edu.neu.util.MyPost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * User: yummin
 * Date: 13-10-12
 */

public class ClientThread implements Runnable{
    Socket client;

    public ClientThread(Socket client){
        this.client = client;
    }

    /**
     *
     */
    public void run(){
        String userId, req, res;
        System.out.println("start...");
        try{
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(client.getOutputStream())), true);
            // receive a request from client
            req = in.readLine();
            System.out.println("read:"+req);
            // classify request
            if(req.equals("login")){
                res = login(in);
                out.println(res);
            } else if(req.equals("register")){
                res = register(in);
                out.println(res);
            } else if(req.equals("meeting")){
                MyMeeting MyMe = new MyMeeting(in, out);
                req = in.readLine();
                if(req.equals("add")){
                    userId = in.readLine();
                    // 将userId的公钥发送给客户端。
                    out.println(findPuK(userId));
                    MyMe.add();
                } else if(req.equals("search")){
                    MyMe.search();
                }
            } else if(req.equals("post")){
                userId = in.readLine();
                MyPost MyEm = new MyPost(userId, in, out);
                req = in.readLine();
                if(req.equals("inbox")){
                    MyEm.initInbox();
                }else if(req.equals("send")){
                    String reciever = in.readLine();
                    String recieverPuK = findPuK(reciever);
                    // 将收信人的公钥发送给客户端。
                    out.println(recieverPuK);
                    if(!recieverPuK.equals("false")){
                        MyEm.Send(reciever);
                    }
                } else if(req.equals("search")){
                    String sender = in.readLine();
                    out.println(findPuK(userId));
                    out.println(findPuK(sender));
                    MyEm.Search(sender);
                }
            }
            out.close();
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            System.out.println("close...");
        }
    }

    /**
     * Log in to RES
     * @param in
     * @return
     * @throws Exception
     */
    public String login(BufferedReader in) throws Exception{
        String userId, password, sql;
        // Get user information
        userId = in.readLine();
        password = in.readLine();
        // Connect to database
        Connection con = MyJDBC.openConnection();
        if(con == null){
            System.out.println("Cannot connect to database");
            return "false";
        }
        Statement statement = con.createStatement();
        //Query user's identity
        sql = "select id from users "+
                "where userid = '"+userId+"' "+
                "and password = '"+password+"';";
        ResultSet rs = statement.executeQuery(sql);
        // Close connection
        MyJDBC.closeConn(con);
        if(!rs.next()) return "false";
        System.out.println("Successfully Login...");
        return "success";
    }

    /**
     *
     * @param in
     * @return
     * @throws Exception
     */
    public String register(BufferedReader in) throws Exception{
        String userId, password, name, job, department, publicKey, sql;
        // 读入用户注册的信息。
        userId = in.readLine();
        password = in.readLine();
        name = in.readLine();
        job = in.readLine();
        department = in.readLine();
        publicKey = in.readLine();
        // 用户名不能和公共用户相同。
        if(userId.equals("ALL")) return "false";
        // 连接数据库。
        Connection con = MyJDBC.openConnection();
        if(con == null){
            System.out.println("Cannot connect to database");
            return "false";
        }
        Statement statement = con.createStatement();
        // 先查询这个用户是否存在。
        sql = "select id from users " + "where userid = '"+userId+"' ";
        ResultSet rs = statement.executeQuery(sql);
        // 如果存在，这返回“false”。
        if(rs.next()) return "false";
        // 没有存在的话新建用户。
        System.out.println("努力添加用户中...");
        sql = "insert into users set "+
                "userId = '"+userId+"',"+
                "password = '"+password+"',"+
                "name = '"+name+"',"+
                "job = '"+job+"',"+
                "department = '"+department+"',"+
                "publicKey = '"+publicKey+"' ";
        // re返回数据库中受影响的条数。
        int re = statement.executeUpdate(sql);
        // 关闭数据库。
        MyJDBC.closeConn(con);
        if(re == 0) return "false";
        System.out.println("用户注册成功...");
        return "success";
    }

    /**
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public String findPuK(String userId) throws Exception {
        String sql;
        // 连接数据库
        Connection con = MyJDBC.openConnection();
        if(con == null){
            System.out.println("Cannot connect to database");
            return "false";
        }
        Statement statement = con.createStatement();
        // 先查询这个用户是否存在。
        sql = "select publicKey from users "+
               "where userid = '"+userId+"' ";
        ResultSet rs = statement.executeQuery(sql);
        // 关闭数据库。
        MyJDBC.closeConn(con);
        // 如果存在，这返回“false”。
        if(!rs.next()) return "false";
        String p = rs.getString("publicKey");
        return p;
    }
}
