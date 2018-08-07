package com.yxw.newscenter.bean;


import java.util.ArrayList;

public class NewsData {
    public int retCode;
    public ArrayList<NewsMenuData> data = new ArrayList<>();

    //侧边栏数据
    public static class NewsMenuData {
        public String id;
        public String title;
        public int type;
        public String url;
        public ArrayList<NewsTabData> children = new ArrayList<>();

        @Override
        public String toString() {
            return "NewsMenuData{" +
                    "title='" + title + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    //新闻页面下11个子页签的数据
    public static class NewsTabData {
        public String id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "NewsTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsData{" +
                "data=" + data +
                '}';
    }
}
