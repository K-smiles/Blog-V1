package com.chelu.dao;



import com.chelu.pojo.MainCategory;
import com.chelu.utils.JdbcUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryDao {

    /**
     * 获取所有分类的信息
     * @return 分类查询结果的列表
     */
    public List<MainCategory> getCategory(MainCategory searchModel){
        List<MainCategory> result = new ArrayList<MainCategory>();
        // 存放查询参数
        List<Object> paramList = new ArrayList<Object>();
        int main_id = searchModel.getId();
        StringBuilder sql = new StringBuilder("select * from main_category where 1 = 1");
        if (main_id != 0 ) {
            sql.append(" and id = ?");
            paramList.add(main_id);
        }

        try {

            List<Map<String, Object>> categoryResult = JdbcUtil.findResult(sql.toString(), paramList);
            if (categoryResult != null) {
                for (Map<String, Object> map : categoryResult) {
                    MainCategory s = new MainCategory(map);
                    result.add(s);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询所有数据异常！", e);
        } finally {

        }
        return result;
    }

    /**
     * 添加主分类*/
    public boolean addMainCategory(MainCategory mainCategory){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("insert into main_category(name) values(?);");

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(mainCategory.getName());
        try {

            result = JdbcUtil.updateByPreparedStatement(sql.toString(), paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改主分类*/
    public boolean updateMainCategory(MainCategory mainCategory){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("update main_category set name = ? where id = ? ");
        List<Object> paramlist = new ArrayList<Object>();
        paramlist.add(mainCategory.getName());
        paramlist.add(mainCategory.getId());

        try {

            result = JdbcUtil.updateByPreparedStatement(sql.toString(),paramlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除主分类*/
    public boolean deleteMainCategory(int id){
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("delete from main_category where id = ?;");
        List<Object> paramlist = new ArrayList<Object>();
        paramlist.add(id);

        try {

            result = JdbcUtil.updateByPreparedStatement(sql.toString(),paramlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
