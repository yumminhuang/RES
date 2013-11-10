package edu.neu.res_clinet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class Email extends TabActivity{
    private TabHost myTabhost;
    private EditText SendReceiver, SendTopic, SendKey, SendText, SearchKey, SearchSender;
    private Button SendReset, SendSend, SearchReset, SearchSearch;
    private String strSendReceiver, strSendTopic, strSendKey, strSendText, strSearchKey, strSearchSender;
    private final static String PrKPath = "/sdcard/darkblue/";
    private final static int countMax = 1000;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myTabhost = this.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.email, myTabhost.getTabContentView(), true);
		
		/*
		 * 送信箱界面： 
		 */
        myTabhost.addTab(myTabhost.newTabSpec("One")
                .setIndicator("Inbox",getResources().getDrawable(R.drawable.email_inbox))
                .setContent(initInbox()));
		
		/*
		 * 发送邮件界面：
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Two")
                .setIndicator("Outbox",getResources().getDrawable(R.drawable.email_outbox))
                .setContent(R.id.Email_layout_outbox));
        // 实例化添加界面的控件。
        SendReceiver = (EditText) findViewById(R.id.ET_Email_SdReceiver);
        SendTopic = (EditText) findViewById(R.id.ET_Email_SdTopic);
        SendKey = (EditText) findViewById(R.id.ET_Email_SdKey);
        SendText = (EditText) findViewById(R.id.ET_Email_SdText);
        SendReset = (Button) findViewById(R.id.BU_Email_SdReset);
        SendSend = (Button) findViewById(R.id.BU_Email_SdSend);
        // 设置确定按钮。
        SendReset.setOnClickListener(new SendResetListener());
        SendSend.setOnClickListener(new SendSendListener());
		
		/*
		 * 搜索邮件界面：
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Three")
                .setIndicator("Search",getResources().getDrawable(R.drawable.email_search))
                .setContent(R.id.Email_layout_search));
        // 实例化搜索界面的控件。
        SearchKey = (EditText) findViewById(R.id.ET_Email_ShKey);
        SearchSender = (EditText) findViewById(R.id.ET_Email_ShSender);
        SearchReset = (Button) findViewById(R.id.BU_Email_ShReset);
        SearchSearch = (Button) findViewById(R.id.BU_Email_ShSearch);
        // 设置确定按钮。
        SearchReset.setOnClickListener(new SearchResetListener());
        SearchSearch.setOnClickListener(new SearchSearchListener());
    }

    /*
     *  初始化收信箱。
     */
    public Intent initInbox(){
        Intent intent = new Intent(Email.this, EmailInbox.class);
		/*
		// 连接服务器。
		try{
			String userId = util.getUserId();
			Socket server = util.getCon();
			BufferedReader in = new BufferedReader(new 
					InputStreamReader(server.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(server.getOutputStream())), true);
			// 将请求登录的信息发送到服务器。
			out.println("email");
			out.println(new String(Base64.encode(userId.getBytes())));
			out.println("inbox");
			
			int cnt = 0;
			String msg;
			String[] text = new String[countMax];
			
			byte[] privateKey = Base64.decode("MIHRAgEAMIGXBgkqhkiG9w0BAwEwgYkCQQD8poLOjhLKuibvzPcRDlJtsHiwXt7LzR60ogjzrhYXrgHzW5Gkfm32NBPF4S7QiZvNEyrNUNmRUb3EPuc3WS4XAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC+eUykAgIBgAQyAjBEysd7B1vWzRL2LDqfbgAOahffQYj0Cw9jREDRFBMLnlBGsbwf/Hd64+ejG0gvml0=");
				//Base64.decode(readPrK(userId));
			
			byte[] PAEnc = msgCoder.getPubKey();
			
			byte[] hashEnc = msgCoder.getHashID1();
			
			// 接收服务器返回的信息。
			msg = in.readLine();
			while(msg.equals("add")){
				
				// 从服务器读入匹配正确的会议记录。
				String sendPuK = in.readLine();
				byte[] KBEnc = Base64.decode("MIHfMIGXBgkqhkiG9w0BAwEwgYkCQQD8poLOjhLKuibvzPcRDlJtsHiwXt7LzR60ogjzrhYXrgHzW5Gkfm32NBPF4S7QiZvNEyrNUNmRUb3EPuc3WS4XAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC+eUykAgIBgANDAAJAOB4seVQZNqzNR8LlYh6DUvuPz5ZLbyM5G+fj3D9u85gP/EBj3PJtgakZdXvR3BI7TVbn1gc66PIA/XXp9J+MGw==");
					//Base64.decode(sendPuK.getBytes());
				
				byte[] msgEnc = Base64.decode(in.readLine().getBytes());
				byte[] ci = Base64.decode(in.readLine().getBytes());
				byte[] key = Base64.decode(in.readLine().getBytes());
				Message recText = new Message(msgEnc, ci, key);
				
				String clearText = msgCoder.persMsgDec(PAEnc, privateKey, KBEnc,
									hashEnc, recText);
				
				text[cnt ++] = strSearchSender+"#"+clearText;
				msg = in.readLine();
			}
			if(cnt == 0){
				cnt = 1;
				text[0] = "admin#无#系统通知#欢迎您使用本系统，您无其它的邮件...";
			}*/
        String t[] = new String[1];
        Bundle bundle = new Bundle();
        bundle.putInt("count", 0);
        bundle.putStringArray("msg", t);
        intent.putExtras(bundle);
			/*
			out.close();
			in.close();
			server.close();
		} catch (Exception e) {
			Toast.makeText(Email.this, "出现错误！"+e, Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}*/
        return intent;
    }

    /*
     *  响应重置信息按钮(outbox)单击事件：
     */
    class SendResetListener implements OnClickListener{
        public void onClick(View v) {
            SendReceiver.setText("");
            SendTopic.setText("");
            SendKey.setText("");
            SendText.setText("");
        }
    }

    /*
     *  响应发送按钮单击事件：
     */
    class SendSendListener implements OnClickListener{
        public void onClick(View v) {
            // 获取用户输入信息。
            strSendReceiver = SendReceiver.getText().toString().trim();
            strSendTopic = SendTopic.getText().toString().trim();
            strSendKey = SendKey.getText().toString().trim();
            strSendText = SendText.getText().toString().trim();
            if(strSendReceiver.equals("") || strSendKey.equals("")){
                Toast.makeText(Email.this, "请将发送的信息填写完整", Toast.LENGTH_LONG).show();
                return;
            }
            // 连接服务器。
            try{
                String userId = util.getUserId();
                //Socket server = util.getCon();
                //BufferedReader in = new BufferedReader(new
                //		InputStreamReader(server.getInputStream()));
                //PrintWriter out = new PrintWriter(new BufferedWriter(
                //		new OutputStreamWriter(server.getOutputStream())), true);

                // 将请求添加的信息发送到服务器。
                //out.println("email");
                //out.println(new String(Base64.encode(userId.getBytes())));
                //out.println("send");
                //out.println(new String(Base64.encode(strSendReceiver.getBytes())));

                // 接收处理服务器返回的信息。
                //String receverPuK = in.readLine();
                //if(receverPuK.equals("false")){
                //	showDialog("用户："+strSendReceiver+"不存在");
                //}else{

                int i = 0;


                String msg = strSendTopic+"#"+strSendKey+"#"+strSendText;
                byte[] publickey =
                        Base64.decode("MIHfMIGXBgkqhkiG9w0BAwEwgYkCQQD8poLOjhLKuibvzPcRDlJtsHiwXt7LzR60ogjzrhYXrgHzW5Gkfm32NBPF4S7QiZvNEyrNUNmRUb3EPuc3WS4XAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC+eUykAgIBgANDAAJAOB4seVQZNqzNR8LlYh6DUvuPz5ZLbyM5G+fj3D9u85gP/EBj3PJtgakZdXvR3BI7TVbn1gc66PIA/XXp9J+MGw==");
                //Base64.decode(receverPuK.getBytes());

                // 加密。
                byte[] privateKey =
                        Base64.decode("MIHRAgEAMIGXBgkqhkiG9w0BAwEwgYkCQQD8poLOjhLKuibvzPcRDlJtsHiwXt7LzR60ogjzrhYXrgHzW5Gkfm32NBPF4S7QiZvNEyrNUNmRUb3EPuc3WS4XAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC+eUykAgIBgAQyAjBEysd7B1vWzRL2LDqfbgAOahffQYj0Cw9jREDRFBMLnlBGsbwf/Hd64+ejG0gvml0=");
                //Base64.decode(readPrK(userId));

                byte[] PAEnc = msgCoder.getPubKey();

                byte[] hashEnc = msgCoder.getHashID1();


                //		showDialog("gegege");
                String clear = "个人信息";
                String keyword = "个";
                Message[] mes = new Message[10000];

                int len = 2000;
                showDialog(new Integer(len).toString());
                Long t1, t2, t3;
                t1 = System.currentTimeMillis();
                while(i < len) {

                    mes[i] = msgCoder.deptMsgEnc(PAEnc, privateKey, hashEnc, clear, keyword);
                    i ++;
                }
                t2 = System.currentTimeMillis();
                t3 = t2 - t1;
                showDialog("加密：" + t3.toString());




                i = 0;
                String clearText = "";
                t1 = System.currentTimeMillis();
                while(i < len) {
                    clearText = msgCoder.deptMsgDec(PAEnc, privateKey, hashEnc, mes[i]);
                    i ++;
                }
                t2 = System.currentTimeMillis();
                t3 = t2 - t1;
                showDialog("解密:" + t3.toString());



                t1 = System.currentTimeMillis();
                i = 0;
                while( i < len ){
                    byte[] Tw = msgCoder.getDeptTw(privateKey, PAEnc, hashEnc, "个");
                    i++;
                }
                t2 = System.currentTimeMillis();
                t3 = t2 - t1;
                showDialog("TW" + t3.toString());



                // 把加密的信息发送到服务器。
                //out.println(new String(Base64.encode(msgEnc.getMsgEnc())));
                //out.println(new String(Base64.encode(msgEnc.getCi())));
                //out.println(new String(Base64.encode(msgEnc.getKey())));

                // 接收服务器返回的信息。
                //msg = in.readLine();
                //if(msg.equals("success")){
                //	showDialog("已经发送成功！");
                //} else {
                //	showDialog("对不起，发送失败...");
                //}
                //}
                //out.close();
                //in.close();
                //server.close();
                //} catch (UnknownHostException e) {
                //	Toast.makeText(Email.this, "连接不上服务器！"+e, Toast.LENGTH_LONG).show();
                //	e.printStackTrace();
                //} catch (IOException e){
                //	Toast.makeText(Email.this, "网络异常！"+e, Toast.LENGTH_LONG).show();
                //	e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(Email.this, "加密失败！"+e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    /*
     *  响应重置信息按钮(search)单击事件：
     */
    class SearchResetListener implements OnClickListener{
        public void onClick(View v) {
            SearchKey.setText("");
            SearchSender.setText("");
        }
    }

    /*
     *  响应搜索按钮单击事件：
     */
    class SearchSearchListener implements OnClickListener{
        public void onClick(View v) {
            // 获取用户输入信息。
            strSearchKey = SearchKey.getText().toString().trim();
            strSearchSender = SearchSender.getText().toString().trim();
            if(strSearchKey.equals("") || strSearchSender.equals("")){
                Toast.makeText(Email.this, "请填写完整", Toast.LENGTH_LONG).show();
                return;
            }
            // 连接服务器。
            try{
                String userId = util.getUserId();

                Socket server = util.getCon();
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(server.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(server.getOutputStream())), true);

                // 将请求登录的信息发送到服务器。
                out.println("email");
                out.println(new String(Base64.encode(userId.getBytes())));
                out.println("search");

                out.println(new String(Base64.encode(strSearchSender.getBytes())));

                String strReceverPuK = in.readLine();

                byte[] receverPuK = Base64.decode("MIHfMIGXBgkqhkiG9w0BAwEwgYkCQQD8poLOjhLKuibvzPcRDlJtsHiwXt7LzR60ogjzrhYXrgHzW5Gkfm32NBPF4S7QiZvNEyrNUNmRUb3EPuc3WS4XAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC+eUykAgIBgANDAAJAOB4seVQZNqzNR8LlYh6DUvuPz5ZLbyM5G+fj3D9u85gP/EBj3PJtgakZdXvR3BI7TVbn1gc66PIA/XXp9J+MGw==");
                //Base64.decode(strReceverPuK.getBytes());


                String strSenderPuK = in.readLine();
                byte[] senderPuK = Base64.decode("MIHfMIGXBgkqhkiG9w0BAwEwgYkCQQD8poLOjhLKuibvzPcRDlJtsHiwXt7LzR60ogjzrhYXrgHzW5Gkfm32NBPF4S7QiZvNEyrNUNmRUb3EPuc3WS4XAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC+eUykAgIBgANDAAJAOB4seVQZNqzNR8LlYh6DUvuPz5ZLbyM5G+fj3D9u85gP/EBj3PJtgakZdXvR3BI7TVbn1gc66PIA/XXp9J+MGw==");
                //Base64.decode(strSenderPuK.getBytes());


                byte[] privateKey = Base64.decode("MIHRAgEAMIGXBgkqhkiG9w0BAwEwgYkCQQD8poLOjhLKuibvzPcRDlJtsHiwXt7LzR60ogjzrhYXrgHzW5Gkfm32NBPF4S7QiZvNEyrNUNmRUb3EPuc3WS4XAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC+eUykAgIBgAQyAjBEysd7B1vWzRL2LDqfbgAOahffQYj0Cw9jREDRFBMLnlBGsbwf/Hd64+ejG0gvml0=");
                //Base64.decode(readPrK(userId));
                byte[] PAEnc = msgCoder.getPubKey();
                byte[] hashEnc = msgCoder.getHashID1();



                // /////

                // 将Tw发送到服务器。
                byte[] Tw = msgCoder.getPersTw(privateKey, receverPuK, PAEnc, senderPuK,
                        hashEnc, strSearchKey);





                out.println(new String(Base64.encode(Tw)));

                int cnt = 0;
                String msg;
                String[] text = new String[countMax];

                // 接收服务器返回的信息。
                msg = in.readLine();
                while(msg.equals("add")){
                    strSearchSender = new String(Base64.decode(in.readLine().getBytes()));
                    // 从服务器读入匹配正确的会议记录。


                    byte[] msgEnc = Base64.decode(in.readLine().getBytes());
                    byte[] ci = Base64.decode(in.readLine().getBytes());
                    byte[] key = Base64.decode(in.readLine().getBytes());
                    Message recText = new Message(msgEnc, ci, key);

                    String clearText = msgCoder.persMsgDec(PAEnc, privateKey, senderPuK,
                            hashEnc, recText);

                    text[cnt ++] = strSearchSender+"#"+clearText;
                    msg = in.readLine();

                }
                if(cnt == 0){
                    showDialog("搜索不到相关邮件");
                } else {
                    Intent intent = new Intent();
                    intent.setClass(Email.this, EmailInbox.class);
                    // 封装要传递的数据：
                    Bundle bundle = new Bundle();
                    bundle.putInt("count", cnt);
                    bundle.putStringArray("msg", text);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                out.close();
                in.close();
                server.close();
            } catch (UnknownHostException e) {
                Toast.makeText(Email.this, "连接不上服务器！"+e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (IOException e){
                Toast.makeText(Email.this, "网络异常！"+e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(Email.this, "解密失败！"+e, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    /*
     *  在本机读取用户userId的私钥。
     */
    private byte[] readPrK(String userId){
        File file = new File(PrKPath+userId+"_PrK.key");
        try {
            InputStream in = new FileInputStream(file);
            byte[] Prk = new byte[(int) file.length()];
            in.read(Prk);
            return Prk;
        } catch (Exception e) {
            showDialog("密钥读取失败!"+e);
        }
        return null;
    }

    /*
     *  提示信息msg。
     */
    private void showDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
