package main.xinyan.DateBase;

import java.sql.*;

public class DBoper {
    //public final static String URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Hotel1;user=sa;password=SQLSEVER";
    //public final static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public Connection getConn() throws Exception {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Room", "sa", "140103ru");
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

    public void Autocommit(){
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Autocommit1(){
        try {
            conn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void commit(){
        try {
            conn.commit();//提交JDBC事务，如果没问题，这时才真正的删除了
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//提交JDBC事务，如果没问题，这时才真正的删除了;
    }

    public void Back(){
        try {
            conn.rollback();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
