package main.zhangqing.admin;
/**
 * 
 */
/**
 * @author LENOVO
 *
 */
import java.sql.*;

public class Sql {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public Connection getConn() throws Exception {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Room", "sa", "140103ru");
        //System.out.println("chhhhhdhdh");
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

    public ResultSet executeQuery(String preparedSql, String[] param) {
        try {
            pstmt = conn.prepareStatement(preparedSql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(i + 1, param[i]);
                }
            }
             rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdata(String preparedSql, String[] param) {
        int num = 0;
        try {
            pstmt = conn.prepareStatement(preparedSql);
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(i + 1, param[i]);
                }
            }
            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
