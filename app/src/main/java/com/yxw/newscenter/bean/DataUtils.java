package com.yxw.newscenter.bean;

public class DataUtils {
    public static NewsData dataObject(){
        NewsData newsData = new NewsData();
        NewsData.NewsMenuData menuData1 = new NewsData.NewsMenuData();
        menuData1.title = "新闻";
        NewsData.NewsTabData tabData1 = new NewsData.NewsTabData();
        tabData1.title = "北京";
        menuData1.children.add(tabData1);
        NewsData.NewsTabData tabData2 = new NewsData.NewsTabData();
        tabData2.title = "中国";
        menuData1.children.add(tabData2);
        NewsData.NewsTabData tabData3 = new NewsData.NewsTabData();
        tabData3.title = "国际";
        menuData1.children.add(tabData3);
        NewsData.NewsTabData tabData4 = new NewsData.NewsTabData();
        tabData4.title = "体育";
        menuData1.children.add(tabData4);
        NewsData.NewsTabData tabData5 = new NewsData.NewsTabData();
        tabData5.title = "生活";
        menuData1.children.add(tabData5);
        NewsData.NewsTabData tabData6 = new NewsData.NewsTabData();
        tabData6.title = "旅游";
        menuData1.children.add(tabData6);
        NewsData.NewsTabData tabData7 = new NewsData.NewsTabData();
        tabData7.title = "科技";
        menuData1.children.add(tabData7);
        NewsData.NewsTabData tabData8 = new NewsData.NewsTabData();
        tabData8.title = "军事";
        menuData1.children.add(tabData8);
        NewsData.NewsTabData tabData9 = new NewsData.NewsTabData();
        tabData9.title = "时尚";
        menuData1.children.add(tabData9);
        newsData.data.add(menuData1);
        NewsData.NewsMenuData menuData2 = new NewsData.NewsMenuData();
        menuData2.title = "专题";
        newsData.data.add(menuData2);
        NewsData.NewsMenuData menuData3 = new NewsData.NewsMenuData();
        menuData3.title = "组图";
        newsData.data.add(menuData3);
        NewsData.NewsMenuData menuData4 = new NewsData.NewsMenuData();
        menuData4.title = "互动";
        newsData.data.add(menuData4);
        return newsData;
    }

}
