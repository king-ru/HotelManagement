package main.haoda.account;

/**
 * @program: HotelManagement
 * @description: 数据库连接文件
 * @author: Mrs.CeYi
 * @create: 2019-01-16 18:54
 **/
import java.sql.*;


public class DBoper {
    //public final static String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Hotel1;user=sa;password=SQLSEVER";
    //public final static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    public Connection getConn() throws Exception {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Room", "sa", "140103ru");
        return conn;
    }

    public void closeAll() {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String a) {
        try {
            pstmt = conn.prepareStatement(a,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdata(String a) {
        int num = 0;
        try {
            pstmt = conn.prepareStatement(a);
            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
