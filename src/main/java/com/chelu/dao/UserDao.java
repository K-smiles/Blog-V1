package com.chelu.dao;

import com.chelu.utils.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    public boolean VerifyUser(String username,String password){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JdbcUtil jdbcUtil = new JdbcUtil();

        StringBuilder sql = new StringBuilder("select * from user where 1=1");
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(username);
        sql.append(" and user_name = ?");
        paramList.add(password);
        sql.append(" and pass_word = ?");
        List<Map<String, Object>> result = null;
        try {
            result = JdbcUtil.findResult(sql.toString(),paramList);
            if (result.size()!=0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println(userDao.VerifyUser("root","1234"));
    }
}
