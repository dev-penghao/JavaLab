
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
public class Client {

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
		
		
		Scanner scanner=new Scanner(System.in);
		String input;
		System.out.print("'0'登录，'1'注册：");
		if ((input=scanner.nextLine()).equals("0")) {
			Requset requset=new Requset();
			requset.setType("sign_in");
			System.out.print("账号：");
			String num=scanner.nextLine();
			System.out.print("密码：");
			String password=scanner.nextLine();
			ps.print("sign_in/"+num+"/"+password);
			ps.write(0);			
		}else if (input.equals("1")) {
			System.out.print("用户名：");
			String name=scanner.nextLine();
			System.out.print("账号：");
			String num=scanner.nextLine();
			System.out.print("密码：");
			String password=scanner.nextLine();
			ps.print("sign_up/"+name+"/"+num+"/"+password);
			ps.write(0);			
		}else {
			System.err.println("你应该输入0或1");
			return;
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
		
		//获取键盘输入并向服务器发送
		while(!(input=scanner.nextLine()).equals("exit")) {
			ps.print("message/"+input);
			ps.write(0);
		}
	}
}

class Requset{
	
	String request="";
	
	public void setType(String type) {
		request=type+"/";
	}
	
	public void add(String item) {
		request+=item+"/";
	}
	
	@Override
	public String toString() {
		return request;
	}
}
