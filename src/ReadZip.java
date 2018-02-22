import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ReadZip {

	public static void main(String[] args) {
		new ReadZip().main();
	}

	public void main() {
		String path="eclipse.zip";
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
