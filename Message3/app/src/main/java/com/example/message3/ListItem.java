package com.example.message3;

import java.util.Date;

/**
 * Created by 欧阳 on 2017/4/17.
 */
public class ListItem {
    private String m_NewsTitle;
    private int m_NewsImageId;
    private String m_NewsSource;
    private Date m_NewsDate;
    private String m_NewsLabel;
    private String m_NewsData;
    public ListItem(String title,int imageid,String source,Date date,String label){
        this.m_NewsTitle=title;
        this.m_NewsImageId=imageid;
        this.m_NewsSource=source;
        this.m_NewsDate=date;
        this.m_NewsLabel=label;
        this.m_NewsData=null;
    }
    //重载构造函数
    public ListItem(String title,int imageid,String source,Date date,String label,String data){
        this.m_NewsTitle=title;
        this.m_NewsImageId=imageid;
        this.m_NewsSource=source;
        this.m_NewsDate=date;
        this.m_NewsLabel=label;
        this.m_NewsData=null;
        this.m_NewsData=data;
    }
    //获取实例属性数据
    public String getNewsTitle(){       //得到信息的标题
        return m_NewsTitle;
    }
    public int getNewsImageId(){        //得到信息的图标id
        return m_NewsImageId;
    }
    public String getNewsLabel(){       //得到消息的性质标签
        return m_NewsLabel;
    }
    public String getNewsSource(){      //得到消息的发布者
        return m_NewsSource;
    }
    public Date getNewsDate(){
        return m_NewsDate;
    }
    //设置实例属性数据
    public void setNewsTitle(String title){
        m_NewsTitle=title;
    }
    public void setNewsImageId(int imageid){
        m_NewsImageId=imageid;
    }
    public void setNewsLabel(String label){
        m_NewsLabel=label;
    }
    public void setNewsSource(String source){
        m_NewsSource=source;
    }
    public void setNewsDate(Date date){
        m_NewsDate=date;
    }
    public void setNewsData(String data){
        m_NewsData=data;
    }
}

