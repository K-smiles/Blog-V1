package com.chelu.service;

import com.chelu.dao.CategoryDao;
import com.chelu.pojo.MainCategory;


import java.util.List;

public class CategoryService {
    CategoryDao categoryDao;
    public CategoryService(){
        categoryDao = new CategoryDao();
    }
    /**
     * 获取所有分类的信息
     * @return 主分类查询结果的列表
     */
    public List<MainCategory> getCategory(MainCategory searchModel){
        return categoryDao.getCategory(searchModel);
    }


    /**
     * 添加主分类*/
    public boolean addMainCategory(MainCategory mainCategory){
        return categoryDao.addMainCategory(mainCategory);
    }

    /**
     * 修改主分类*/
    public boolean updateMainCategory(MainCategory mainCategory) {
        return categoryDao.updateMainCategory(mainCategory);
    }

    /**
     * 删除主分类*/
    public boolean deleteMainCategory(int id){
        System.out.println("service执行");
        return categoryDao.deleteMainCategory(id);
    }

}
