package todo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconn {
    private static Connection con = null;

    public static Connection getConn() {
        try {
            if (con == null || con.isClosed()) {
                // 1. Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 2. Establish DB Connection (adjust username/password as per MySQL setup)
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_db", "root", "1234");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
