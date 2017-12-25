package jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.out;

/**
 * Created by shifeixuan on 2017/11/27.
 */
public class JDBCUtil {

    public static Connection getConnection() {
        Connection connection =null;
        try {
            String username = "root";
            String password = "sfx123456";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_management?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false", username, password);
            if (connection != null) {
                out.println("数据库连接成功");
                return connection;
            } else {
                out.println("数据库连接失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
