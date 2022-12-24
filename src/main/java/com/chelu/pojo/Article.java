package com.chelu.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Article {
    private int id;
    private String title;
    private String mdContent;
    private String htmlContent;
    private String createDate;
    private int mainId;
    private String mname;
    private int top;

    public Article() {
    }

    public Article(int id, String title, String mdContent, String htmlContent, String createDate, int mainId, String mname, int top) {
        this.id = id;
        this.title = title;
        this.mdContent = mdContent;
        this.htmlContent = htmlContent;
        this.createDate = createDate;
        this.mainId = mainId;
        this.mname = mname;
        this.top = top;
    }

    public Article(Map<String, Object> map) {
        this.id = (int) map.get("ID");
        this.title = (String) map.get("TITLE");
        this.mdContent = (String) map.get("MD_CONTENT");
        this.htmlContent = (String) map.get("HTML_CONTENT");
        this.createDate = (String) map.get("CREATE_DATE");
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

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
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
                ", htmlContent='" + htmlContent + '\'' +
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
        list.add(htmlContent);
        list.add(createDate);
        list.add(mainId);
        list.add(top);
        return list;
    }
}
