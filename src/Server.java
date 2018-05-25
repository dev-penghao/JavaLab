import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	// 输出流列表，记录了所有客户端的的输出流
	public static Socket[] online = new Socket[50];
	public static int count = 0;
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hello?USESSL=false";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "penghao";
	static final String PASS = "123456";
	
	public static Connection con = null;
	public static Statement statement = null;
	
	public static void main(String[] args) throws Exception {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("数据库驱动加载成功！");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("数据库连接成功！");
			
			statement = con.createStatement();
//			String sql;
//            sql = "SELECT id, name, num, password FROM user_lib";
//            ResultSet rs = statement.executeQuery(sql);
		} catch (ClassNotFoundException e) {
			System.err.println("驱动加载失败");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("服务器正在等待客户端的连接请求----");
		try {
			ServerSocket ss=new ServerSocket(38380);
			// 用一个while循环可以同时响应多个客户端的请求
			while (true) {
				Socket sk = ss.accept();// 服务器监听对应端口的输入
				for(int i=0;i<online.length;i++) {
					if (online[i]==null) {
						online[i]=sk;
						TestThrad t=new TestThrad(sk,i);
						t.start();
						break;
					}
				}
				System.out.println("一个已连接"+sk.getInetAddress().getHostAddress()+"，当前客户端数:"+count++);
			}
		} catch (IOException e) {
			System.err.println("登录模块故障！");
		}
	}
	
	//向所有客户端发送消息
    public static void sendMessage(String mess,int pid){
		for(int i=0;i<online.length;i++){
			if (pid!=i&&online[i]!=null) {
				try {
					new PrintStream(online[i].getOutputStream()).println(mess);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class TestThrad extends Thread{
	
	Socket socket;
	BufferedReader br;
	PrintStream pStream;
	int pid=0;
	
	public TestThrad(Socket socket,int pid) {
		this.socket=socket;
		this.pid=pid;
	}
	/*
	 *开启一个线程循环读取该客户端的输出流，如果有新消息
	 *则向所有已连接的客户端发送该消息
	 */
	@Override
	public void run() {
		try {
			String string="";
			char[] ch=new char[1];
			int isEOF=0;
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pStream=new PrintStream(socket.getOutputStream());
//			pStream.print("OK");
			finish:while(true) {
				while(true) {
					isEOF=br.read(ch);
					if (isEOF==-1) {
						break finish;
					}
					if (ch[0]==0) {
						break;
					}
					string+=ch[0];
				}
				analyseCmd(string);
				string="";
			}
			Server.online[pid]=null;
			Server.count--;
			System.out.println(socket.getInetAddress().getHostAddress()+"退出");
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.run();
	}
	
	public void analyseCmd(String cmd) {
		String[] ss=cmd.split("/");
		String result="";
		if (ss[0].equals("message")) {
			Server.sendMessage(ss[1],pid);
		}else if (ss[0].equals("test")) {
			pStream.println("OK");
		}else if (ss[0].equals("sign_in")) {
			result=sign_in(ss[1], ss[2]);
			System.out.println(result);
			pStream.println(result);
			System.out.println("已返回结果");
		}else if (ss[0].equals("sign_up")) {
			sign_up(ss[1], ss[2], ss[3]);
		}else {
			System.err.println("Bad cmd!");
		}
	}
	
	public boolean sign_up(String name,String num,String password) {
		String cmd="insert into user_lib (name,num,password) values ("+"\""+name+"\","+"\""+num+"\","+"\""+password+"\""+")";
		try {
			Server.statement.execute(cmd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String sign_in(String num,String password) {
		String cmd="select num,password from user_lib where num="+num;
		try {
			ResultSet result=Server.statement.executeQuery(cmd);
			if (result.next()) {
				String number=result.getString("num");
				String pwInLib=result.getString("password");
				System.out.println(number+"  "+pwInLib);
				if (pwInLib.equals(password)) {
					return "OK";
				}else {
					return "eorr passwprd";
				}
			}else {
				return "user not fond";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "unknow eorr";
		}
	}
}