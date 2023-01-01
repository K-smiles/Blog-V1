package com.chelu.dao;

import com.chelu.pojo.Article;
import com.chelu.pojo.Pager;
import com.chelu.utils.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleDao {


    /**
     * 根据查询条件，查询文章分页信息
     * @param searchModel 封装查询条件
     * @param pageNum 查询第几页数据
     * @param pageSize 每页显示多少条记录
     * @return 查询结果
     */
    public Pager<Article> findArticle(Article searchModel, int pageNum, int pageSize) {
        Pager<Article> result = null;
        // 存放查询参数
        List<Object> paramList = new ArrayList<Object>();
        int id = searchModel.getId();
        int main_id = searchModel.getMainId();
        StringBuilder sql = new StringBuilder("select a.*,m.name mname from " +
                    "article a left join main_category m on a.main_id=m.id where 1=1 ");
        StringBuilder countSql = new StringBuilder("select count(id) as totalRecord from article where 1=1 ");
        if (id != 0 ) {
            sql.append(" and a.id = ?");
            countSql.append(" and id = ?");
            paramList.add(id);
        }

        if (main_id != 0 ) {
            sql.append(" and a.main_id= ? ");
            countSql.append(" and main_id = ? ");
            paramList.add(main_id);
        }
        sql.append(" ORDER BY top desc,create_date desc ");
        countSql.append(" ORDER BY top desc,create_date desc ");
        // 起始索引
        int fromIndex = pageSize * (pageNum - 1);
        // 使用limit关键字，实现分页
        sql.append(" limit " + fromIndex + ", " + pageSize);
        // 存放所有查询出的文章对象
        List<Article> studentList = new ArrayList<Article>();

        try {
            // 获取总记录数
            List<Map<String, Object>> countResult = JdbcUtil.findResult(countSql.toString(), paramList);
            Map<String, Object> countMap = countResult.get(0);
            int totalRecord = ((Number) countMap.get("totalRecord")).intValue();
            // 获取查询的文章记录
            List<Map<String, Object>> articleList = JdbcUtil.findResult(sql.toString(), paramList);
            if (articleList != null) {
                for (Map<String, Object> map : articleList) {
                    Article s = new Article(map);
                    studentList.add(s);
                }
            }

            //获取总页数
            int totalPage = totalRecord / pageSize;
            if (totalRecord % pageSize != 0) {
                totalPage++;
            }
            // 组装pager对象
            result = new Pager<Article>(pageSize, pageNum,totalRecord, totalPage, studentList);

        } catch (SQLException e) {
            throw new RuntimeException("查询所有数据异常！", e);
        } finally {

        }
        return result;
    }
    /**
     * 添加新文章
     * @param article 文章对象
     * @return 插入结果
     */
    public boolean addArticle(Article article) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("insert into article(title,md_content," +
                            "create_date,main_id,top) values(?,?,?,?,?);");
        try {

            result = JdbcUtil.updateByPreparedStatement(sql.toString(),article.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 修改文章
     * @param article 文章对象
     * @param article 文章
     * @return 更新结果
     */
    public boolean updateArticle(Article article,int id) {
        boolean result = false;
        StringBuilder sql = new StringBuilder("update article set title = ?,md_content = ?," +
                    "create_date = ?,main_id = ?,top = ?  where id = ? ");


        ArrayList paramList = (ArrayList) article.toList();
        paramList.add(id);
        try {
            result = JdbcUtil.updateByPreparedStatement(sql.toString(),paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除文章
     * @param id 文章id
     * @return 删除结果
     */
    public boolean deleteArticle(int id) {
        boolean result = false;
        StringBuilder sql =
                new StringBuilder("delete from article where id = ?;");

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(id);
        try {

            result = JdbcUtil.updateByPreparedStatement(sql.toString(),paramList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
