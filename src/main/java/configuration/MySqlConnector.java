package configuration;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {
    static final String URL = "jdbc:mysql://localhost:3306/hopital";
    static final String USERNAME = "root";
    static final String PASSWORD = "123456";

    // Hàm lấy ra connection để kết nối và truy vấn đến database
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            return null;
        }
    }
}