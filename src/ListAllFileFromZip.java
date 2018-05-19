import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
*	Created by Penghao,日期未知
*	该程序从zip压缩包中列出所有文件,由于使用Java自带的库，
*所以效率不高
*/

public class ListAllFileFromZip {

	public static void main(String[] args) {
		new ListAllFileFromZip().main();
	}

	public void main() {
		String path="eclipse.zip";//用于测试的zip文件的路径
		readZip(path);
	}
	
	public void readZip(String zipName) {
		File input=new File(zipName);
		if (!input.exists()) {
			System.out.println("文件不存在！");
			return;
		}
		try {
			FileInputStream fis=new FileInputStream(input);
			ZipInputStream zis=new ZipInputStream(fis);
			ZipEntry zEntry;
			while((zEntry=zis.getNextEntry())!=null) {
				System.out.println(zEntry.getName());
			}
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
