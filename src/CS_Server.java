import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CS_Server {
	
	//输出流列表，记录了所有客户端的的输出流
	public static List<PrintStream> outStream = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("服务器正在等待客户端的连接请求----");
		try {
			ServerSocket ss=new ServerSocket(38380);
			int count=0;
			// 用一个while循环可以同时响应多个客户端的请求
			while (true) {
				Socket sk = ss.accept();// 服务器监听对应端口的输入
				outStream.add(new PrintStream(sk.getOutputStream()));//将该客户端的输出流添加到输出流列表
				TestThrad t=new TestThrad(sk);
				t.start();
				System.out.println("一个已连接，当前客户端数:"+count++);
			}
		} catch (IOException e) {
			System.err.println("登录模块故障！");
		}
	}
	
	//向所有客户端发送消息
    public static void sendMessage(String mess){
		for(int i=0;i<outStream.size();i++){
			outStream.get(i).println(mess);
		}
	}
}
class TestThrad extends Thread{
	
	Socket socket;
	BufferedReader br;
	public TestThrad(Socket socket) {
		this.socket=socket;
	}
	/*
	 *开启一个线程循环读取该客户端的输出流，如果有新消息
	 *则向所有已连接的客户端发送该消息
	 */
	@Override
	public void run() {
		try {
			String string;
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				if (!(string=br.readLine()).equals("")) {
					System.out.println(socket.getInetAddress()+":"+string);
					CS_Server.sendMessage(string);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.run();
	}
}
