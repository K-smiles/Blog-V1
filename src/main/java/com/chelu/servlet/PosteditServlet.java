package com.chelu.servlet;

import com.chelu.pojo.Article;
import com.chelu.service.ArticleService;
import com.chelu.utils.DateParse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PosteditServlet")
public class PosteditServlet extends HttpServlet {
    ArticleService articleService;
    Article article;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        articleService = new ArticleService();
        String action = request.getParameter("action");
        int id=0;
        if (action.equals("delete")) {
            if (articleService.deleteArticle(Integer.parseInt(request.getParameter("id")))) {
                response.getWriter().write("删除成功");
            }
        }
        else {
            article = new Article();
            article.setTitle(request.getParameter("title"));
            article.setMdContent(request.getParameter("test-editormd-markdown-doc"));

            int top = request.getParameter("top") == null ? 0 : 1;
            article.setTop(top);

            String createDate =  request.getParameter("timeDate");
            article.setCreateDate(DateParse.parseDate(createDate));

            article.setMainId(Integer.parseInt(request.getParameter("main_id")));

            if(request.getParameter("id")!=null)
                id = Integer.parseInt(request.getParameter("id"));
            System.out.println(article);
            if (action.equals("add")) {
                if (articleService.addArticle(article) == true) {
                    request.getRequestDispatcher("/success.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } else if (action.equals("update")) {
                if (articleService.updateArticle(article, id) == true) {
                    request.getRequestDispatcher( "/success.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher( "/error.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}