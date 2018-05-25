import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HttpServer {

	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hello?USESSL=false";

	// 数据库的用户名与密码，需要根据自己的设置
	static final String USER = "penghao";
	static final String PASS = "123456";

	public static void main(String[] args) {

		Connection con = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("数据库驱动加载成功！");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("数据库连接成功！");
			
			statement = con.createStatement();
			String sql;
            sql = "SELECT id, name, num, password FROM user_lib";
            ResultSet rs = statement.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String num = rs.getString("num");
                String password=rs.getString("password");
                
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", name: " + name);
                System.out.print(", num: " + num);
                System.out.println(", password: "+password);
                System.out.print("\n");
            }
		} catch (ClassNotFoundException e) {
			System.err.println("驱动加载失败");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}