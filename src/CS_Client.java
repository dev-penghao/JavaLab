
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
		
		while(!(input=scanner.nextLine()).equals("exit")) {
			ps.println(input);
		}
	}
}