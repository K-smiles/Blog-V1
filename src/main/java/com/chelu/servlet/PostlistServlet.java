package com.chelu.servlet;



import com.chelu.pojo.Article;
import com.chelu.pojo.MainCategory;
import com.chelu.pojo.Pager;
import com.chelu.service.ArticleService;
import com.chelu.service.CategoryService;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

public class PostlistServlet extends javax.servlet.http.HttpServlet {
    ArticleService articleService = new ArticleService();
    CategoryService categoryService = new CategoryService();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //默认值
        int pageNum = 1;
        int pageSize = 10;
        int main_id = 0;

        int id = 0;
        // 接收request里的参数
        String role = request.getParameter("role");
        // 组装查询条件+
        Article searchModel = new Article();
        if (request.getParameter("main_id")!=null)
            main_id = Integer.parseInt(request.getParameter("main_id"));

        if (main_id!=0) //分类
            searchModel.setMainId(Integer.parseInt(request.getParameter("main_id")));

        if (request.getParameter("id")!=null)
            searchModel.setId(Integer.parseInt(request.getParameter("id")));
        if (request.getParameter("pageNum")!=null)
            pageNum = Integer.parseInt(request.getParameter("pageNum")); //显示第几页数据
        if (request.getParameter("pageSize")!=null)
            pageSize = Integer.parseInt(request.getParameter("pageSize"));  // 每页显示多少条记录
        //调用service 获取查询结果
        Pager<Article> result = articleService.findArticle(searchModel, pageNum, pageSize);
        List<MainCategory> mainCategory = categoryService.getCategory(new MainCategory(0,null));
        // 返回结果到页面
        request.setAttribute("result", result);
        request.setAttribute("mainCategory", mainCategory);

        if (role.equals("0"))
        {
            System.out.println(request.getContextPath());
//
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/blog.jsp");

            requestDispatcher.forward(request, response);
//            response.sendRedirect(request.getContextPath()+"/blog.jsp");
        }
        else if(role.equals("1"))
            request.getRequestDispatcher("/admin/postlist.jsp").forward(request, response);
        else if (role.equals("2"))
            request.getRequestDispatcher("/article.jsp").forward(request, response);
        else if (role.equals("3"))
//            response.sendRedirect(request.getContextPath()+"/admin/test.jsp");
            request.getRequestDispatcher("/admin/updatepost.jsp").forward(request, response);
        else if (role.equals("4"))
            request.getRequestDispatcher("/project.jsp").forward(request, response);
    }
}
