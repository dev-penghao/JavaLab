import java.io.*;

public class Main {
	
	long line_num=0;
	public static void main(String[] args){
		new Main().main();
	}
	
	public void main(){
		File input=new File("/home/penghao/AndroidStudioProjects/File_plroe/app/src/main/java/com/penghao/file_plroe/MainActivity.java");		
//		printFile(input);
		getLineNum(input);
		System.out.println("Donw!  "+line_num);
	}
	
	public void printFile(File home){
		File[] files=home.listFiles();
		for(File f:files){
			if(f.isDirectory()){
				printFile(f);
			}else{
				System.out.print("正在统计:"+f.getAbsolutePath()+"    ");
				getLineNum(f);
				System.out.println(line_num);
			}
		}
	}

	public void getLineNum(File f){
		try
		{
			FileInputStream fis=new FileInputStream(f);
			int readed;
			while((readed=fis.read())!=-1){
				if(readed==0x0a){
					line_num++;
				}
			}
			fis.close();
		}
		catch (IOException e)
		{}
	}
}
