package com.chelu.pojo;

import com.chelu.utils.DateParse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ArticleM {
    private int id;
    private String title;
    private String mdContent;
    private Date createDate;

    private MainCategory mainCategory;
    private int top;

    public ArticleM() {
    }

    public ArticleM(int id, String title, String mdContent, Date createDate,MainCategory mainCategory, int top) {
        this.id = id;
        this.title = title;
        this.mdContent = mdContent;
        this.createDate = createDate;
        this.mainCategory = mainCategory;
        this.top = top;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }



    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }


    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mdContent='" + mdContent + '\'' +
                ", createDate='" + createDate + '\'' +
                ", mainCategory=" + mainCategory +

                ", top=" + top +
                '}';
    }

    public List toList() {
        List list = new ArrayList();
        list.add(title);
        list.add(mdContent);
        list.add(createDate);
        list.add(mainCategory);
        list.add(top);
        return list;
    }
}
