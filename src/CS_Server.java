import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CS_Server {
	public static List<String> messageList;
	public static List<PrintStream> outStream;
	public static List<BufferedReader> brReader;
	public static ServerSocket ss = null;
	
	public static void main(String[] args) throws Exception {
		try
		{
			ss = new ServerSocket(38380);
		}
		catch (IOException e)
		{}
		messageList = new ArrayList<>();
		outStream= new ArrayList<>();
		brReader=new ArrayList<>();

		System.out.println("服务器正在等待客户端的连接请求----");
		try {
			// 用一个while循环可以同时响应多个客户端的请求
			while (true) {
				Socket sk = ss.accept();// 服务器监听对应端口的输入
				outStream.add(new PrintStream(sk.getOutputStream()));
				TestThrad t=new TestThrad(sk);
				t.start();
				System.out.println("一个已连接");
			}
		} catch (IOException e) {
			System.out.println("登录模块故障！");
		}
	}
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
	@Override
	public void run() {
		try {
			String string;
			socket.getOutputStream();
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