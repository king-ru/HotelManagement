package utils;

/**
 * @program: RoomManagement
 * @description:
 * @author: Mrs.CeYi
 * @create: 2019-01-09 21:07
 **/
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCUtils {

    private String driver;
    private String url;
    private String username;
    private String password;

    private final static String INIT_FILE = "D:\\ideaProject\\HotelManagement\\src\\sqlsever.ini";

    private static volatile JDBCUtils sInstance;

    private JDBCUtils() {
    }

    public static JDBCUtils get() {
        if (null == sInstance) {
            synchronized (JDBCUtils.class) {
                if (null == sInstance) {
                    sInstance = new JDBCUtils();
                }
            }
        }
        sInstance.initParam(INIT_FILE);
        return sInstance;
    }

    private void initParam(String paramFile) {
        try {
            // 使用 properties 类类加载属性文件
            Properties prop = new Properties();
            prop.load(new FileInputStream(paramFile));
            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

            // 加载驱动
            Class.forName(driver);
            connection = getConnection();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 增删改
     * @param sql
     * @param params
     * @return
     * list即为参数列表
     * @throws SQLException
     */
    public boolean update(String sql, List<Object> params) throws SQLException {

        int result = -1;
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                //设置占位符参数
                pstmt.setObject(index++, params.get(i));
            }
        }

        result = pstmt.executeUpdate();
        return result > 0;
    }

    /**
     * ResultSet executeQuery(String sql) throws SQLException 执行给定的 SQL
     * 语句，该语句返回单个 ResultSet 对象

     * Statement createStatement() 创建一个 Statement 对象来将 SQL 语句发送到数据库。
     * 使用prepareStatement便于传递sql参数，避免插入时候写sql语句容易出错。
     * 使用PreparedStatement:是Statement的子接口,可以传入带占位符的SQL语句，提供了补充占位符变量的方法。
     * String sql="insert into examstudent values(?,?,?,?,?,?,?)";参数用list传递
     *
     */

    /**
     * find_btn单条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public Map<String, Object> getQueryResult(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();
        //getMetaData即为返回结果中的元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columns = metaData.getColumnCount();
        while (resultSet.next()) {
            getResultSet(metaData, columns, map);
        }
        return map;
    }

    public List<Map<String, Object>> getQueryResults(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (null != params && !params.isEmpty()) {
            for (int i = 0; i < params.size() ; i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }

        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columns = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String , Object> map = new HashMap<>();
            getResultSet(metaData, columns, map);
            list.add(map);
        }
        return list;
    }



    private void getResultSet(ResultSetMetaData metaData, int columns, Map<String, Object> map) throws SQLException {
        for (int i = 1; i <= columns ; i++) {
            String name = metaData.getColumnName(i);
            Object value = resultSet.getObject(name);
            if (null == value) value = "";
            map.put(name, value);
        }
    }

}
