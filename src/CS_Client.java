
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 *客户端类，客户端主要由两个模块组成，
 *一个不断从服务器读取新消息并显示，
 *一个循环读取键盘输入并发送到服务器
 */
public class CS_Client {

	static Socket sk;
	static PrintStream ps;
	static BufferedReader br;
	
	public static void main(String[] args) {
		
		try {
			sk=new Socket("127.0.0.1", 38380);
			ps=new PrintStream(sk.getOutputStream());
			br=new BufferedReader(new InputStreamReader(sk.getInputStream()));
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		 *开启一个线程循环从读取缓冲区，如果有新消息则显示，
		 *没有的话进入下一次循环
		 */
		new Thread(new Runnable(){
			@Override
			public void run()
			{
				try{
					String str;
					while(true){
						if(!(str=br.readLine()).equals("")){
							System.out.println(str);
						}
					}
				}catch(IOException e){}
			}
		}).start();
		
		Scanner scanner=new Scanner(System.in);
		String input;
		
		//获取键盘输入并向服务器发送
		while(!(input=scanner.nextLine()).equals("exit")) {
			ps.println(input);
		}
	}
}
