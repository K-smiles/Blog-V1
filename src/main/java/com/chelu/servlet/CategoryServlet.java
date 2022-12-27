package com.chelu.servlet;

import com.chelu.pojo.MainCategory;
import com.chelu.service.CategoryService;
import com.google.gson.Gson;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CategoryServlet")
public class CategoryServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        if (request.getParameter("action").equals("getall"))
        {
            List<MainCategory> result = categoryService.getCategory(new MainCategory(0,null));
            request.setAttribute("mainCategory",result);
            request.getRequestDispatcher("/admin/category.jsp").forward(request,response);


        }else
        {
            ajaxMain(request, response);
        }


    }

    private void ajaxMain(HttpServletRequest request, HttpServletResponse response) {
        List<MainCategory> result = categoryService.getCategory(new MainCategory(0,null));
        Map<Integer, String> mainMap = new HashMap<Integer, String>();
        Gson gson = new Gson();
        for (MainCategory item : result)
            mainMap.put(item.getId(), item.getName());
        String json = gson.toJson(mainMap);
        response.setContentType("application/json");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }



}
