import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Huffman {

	public static void main(String[] args) {
		new Huffman().main();
	}
	
	public void main() {
		File file=new File("/home/penghao/eclipse-workspace/Java实验/src/Huffman.java");
		byte[] readed=new byte[1024*1024];
		long[] count=new long[256];
		if (!file.exists()) {
			System.err.println("文件不存在！");
			return;
		}
		try {
			FileInputStream fis=new FileInputStream(file);
			while(fis.read(readed)!=-1) {
				for(int i=0;i<readed.length;i++) {
					count[readed[i]&0x0ff]++;
//					System.out.println(readed[i]&0x0ff);
				}
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long total=file.length();
		float probability;
		float sum=0;
		for(int i=0;i<count.length;i++) {
			probability=((float)count[i])/total;
			sum=sum+probability;
			System.out.println(count[i]+" 的概率是"+probability);
		}
		System.out.println("和是"+sum);
	}
}