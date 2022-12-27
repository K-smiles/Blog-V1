package com.chelu.pojo;

import com.chelu.utils.DateParse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Article {
    private int id;
    private String title;
    private String mdContent;
    private Date createDate;
    private int mainId;
    private String mname;
    private int top;

    public Article() {
    }

    public Article(int id, String title, String mdContent, Date createDate, int mainId, String mname, int top) {
        this.id = id;
        this.title = title;
        this.mdContent = mdContent;
        this.createDate = createDate;
        this.mainId = mainId;
        this.mname = mname;
        this.top = top;
    }

    public Article(Map<String, Object> map) {
        this.id = (int) map.get("ID");
        this.title = (String) map.get("TITLE");
        this.mdContent = (String) map.get("MD_CONTENT");
        this.createDate = DateParse.parseDate((String) map.get("CREATE_DATE"));
        this.mainId = (int) map.get("MAIN_ID");
        this.mname = (String) map.get("mname");
        this.top = (int) map.get("TOP");
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

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mdContent='" + mdContent + '\'' +
                ", createDate='" + createDate + '\'' +
                ", mainId=" + mainId +
                ", mname='" + mname + '\'' +
                ", top=" + top +
                '}';
    }

    public List toList() {
        List list = new ArrayList();
        list.add(title);
        list.add(mdContent);
        list.add(createDate);
        list.add(mainId);
        list.add(top);
        return list;
    }
}
