package main.zhangqing.admin;

import java.sql.*;

public class Sql {

    Connection conn = null; //数据库连接
    PreparedStatement pstmt = null; //用于数据库查询语句
    ResultSet rs = null;  //用于返回结果集

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

    public ResultSet executeQuery(String preparedSql) {
        try {
            //参数实现多次查询
            pstmt = conn.prepareStatement(preparedSql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

             rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdata(String preparedSql) {
        int num = 0;
        try {
            pstmt = conn.prepareStatement(preparedSql);

            num = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
