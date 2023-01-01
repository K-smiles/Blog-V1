package com.chelu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JdbcUtil {

    public static DataSource dataSource;


    static {
        //loading the configuration of database
        loadConfig();
    }

    // the connection of database
    // sql statement
    private static PreparedStatement pstmt;
    // the result of sql statement
    private static ResultSet resultSet;

    private static Connection connection;

    public JdbcUtil() {

    }

    /**
     * loading the configuration of database
     */
    public static void loadConfig() {
        try {
            InputStream inStream = JdbcUtil.class.getResourceAsStream("/druid.properties");
            Properties prop = new Properties();
            prop.load(inStream);
             dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new RuntimeException("jdbc configuration incorrect!!!", e);
        }
    }

    //test method
    public static void main(String[] args) {
        JdbcUtil jdbcUtil = new JdbcUtil();
        try {
            List<Map<String, Object>> result = jdbcUtil.findResult(
                    "select * from USER", null);

            List<Map<String, Object>> result2 = jdbcUtil.findResult(
                    "select md_content from article where id =34;", null);

            for (Map<String, Object> m : result2) {
                System.out.println(m.get("md_content"));
//                System.out.println(m.values());
            }
            String name = "insert into  article(title,md_content,create_date,main_id,top) \n" +
                    "value ('文档','文档','2022-12-07',1,1);";
            System.out.println(name);
            JdbcUtil.updateByPreparedStatement(name,null);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.releaseConn();
        }
    }


    /**
     * 执行更新操作
     *
     * @param sql    sql语句
     * @param params 执行参数
     * @return 执行结果
     * @throws SQLException
     */
    public static boolean updateByPreparedStatement(String sql, List<?> params)
            throws SQLException {
        boolean flag = false;
        int result = -1;// 表示当用户执行添加删除和修改的时候所影响数据库的行数

        connection= dataSource.getConnection();
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
    public static List<Map<String, Object>> findResult(String sql, List<?> params)
            throws SQLException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        connection= dataSource.getConnection();
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
