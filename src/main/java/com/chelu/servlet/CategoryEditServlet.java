package com.chelu.servlet;

import com.chelu.pojo.MainCategory;
import com.chelu.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryEditServlet")
public class CategoryEditServlet extends HttpServlet {

    MainCategory mainCategory;
    boolean result;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.equals("delete")) {

                if (new CategoryService().deleteMainCategory(Integer.parseInt(request.getParameter("id")))){
                    response.getWriter().write("删除成功");
                }


        } else {
            int main_id = 0;
            if (request.getParameter("maincategory")!=null)
                main_id = Integer.parseInt(request.getParameter("maincategory")); //没有主分类为0

            mainCategory = new MainCategory();
            if (action.equals("add")){

                    mainCategory.setName(request.getParameter("subcategory"));
                    result = new CategoryService().addMainCategory(mainCategory);

            }else if (action.equals("update")){

                    mainCategory.setName(request.getParameter("maincategoryname"));
                    mainCategory.setId(Integer.parseInt(request.getParameter("main_id")));
                    result = new CategoryService().updateMainCategory(mainCategory);

            }
            if (result)
                request.getRequestDispatcher(request.getContextPath() + "/success.jsp").forward(request, response);
            else
                request.getRequestDispatcher(request.getContextPath() + "/error.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
