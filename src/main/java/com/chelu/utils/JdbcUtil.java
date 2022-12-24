package com.chelu.utils;

import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JdbcUtil {
    // the name of database
    private static String USERNAME;
    // the password of database
    private static String PASSWORD;
    // the driver of database
    private static String DRIVER;
    // the url of database
    private static String URL;

    static {
        //loading the configuration of database
        loadConfig();
    }

    // the connection of database
    private Connection connection;
    // sql statement
    private PreparedStatement pstmt;
    // the result of sql statement
    private ResultSet resultSet;

    public JdbcUtil() {

    }

    /**
     * loading the configuration of database
     */
    public static void loadConfig() {
        try {
            InputStream inStream = JdbcUtil.class.getResourceAsStream("/jdbc.properties");
            Properties prop = new Properties();
            prop.load(inStream);
            USERNAME = prop.getProperty("jdbc.username");
            PASSWORD = prop.getProperty("jdbc.password");
            DRIVER = prop.getProperty("jdbc.driver");
            URL = prop.getProperty("jdbc.url");
        } catch (Exception e) {
            throw new RuntimeException("jdbc configuration incorrect!!!", e);
        }
    }

    //test method
    public static void main(String[] args) {
        JdbcUtil jdbcUtil = new JdbcUtil();
        jdbcUtil.getConnection();
        try {
            List<Map<String, Object>> result = jdbcUtil.findResult(
                    "select * from USER", null);
            for (Map<String, Object> m : result) {
                System.out.println(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection() {
        try {
            Class.forName(DRIVER); // 注册驱动
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 获取连接
        } catch (Exception e) {
            throw new RuntimeException("get connection error!", e);
        }
        return connection;
    }

    /**
     * 执行更新操作
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @return 执行结果
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql, List<?> params)
            throws SQLException {
        boolean flag = false;
        int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        // 填充sql语句中的占位符
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        result = pstmt.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     * 执行查询操作
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findResult(String sql, List<?> params)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        pstmt = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        resultSet = pstmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnLabel(i+1);

                Object cols_value = resultSet.getObject(cols_name);
                if (cols_name.equals("CREATE_DATE"))
                {

                    cols_value=(cols_value.toString());

                }
                if (cols_value == null) {
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 释放资源
     */
    public void releaseConn() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
