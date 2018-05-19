import java.io.*;

/**
*	Created by Penghao on onknow.
*	该程序可以统计某文件夹中所包含文件的行数
*/

public class PrintLineNum {
	
	long line_num=0;
	public static void main(String[] args){
		new PrintLineNum().main();
	}
	
	public void main(){
		File input=new File("/home/penghao/WorkSpace/minix/src/minix");
		if(!input.exists()) {
			System.out.println("文件："+input.getName()+"不存在！");
		}
		printFile(input);
		//getLineNum(input);
		System.out.println("Donw!  "+line_num);
	}
	
	//统计某文件夹中的所有文件的行数
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

	//统计单个文件的行数
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
