import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HttpServer {
	
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/hello?useSSL=false";
 
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "penghao";
    static final String PASS = "123456";
    
	public static void main(String[] args) {
		
		Connection con=null;
		Statement statement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("数据库驱动加载成功！");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.err.println("驱动加载失败");
		}
		
		try {
			con=DriverManager.getConnection(DB_URL,USER,PASS);
			statement=con.createStatement();
			System.out.println("数据库连接成功！");
		} catch (SQLException e1) {
			System.out.println("连接数据库失败");
		}
	}
}