import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
	
	public static void main(String[] args){
		new Main().main();
	}

	private void main(){
		try {
			InetAddress ip=InetAddress.getLocalHost();
			System.out.println(ip.getHostName());
			System.out.println(ip.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
