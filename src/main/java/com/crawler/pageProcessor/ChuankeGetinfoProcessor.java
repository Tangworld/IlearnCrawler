package com.crawler.pageProcessor;


import com.crawler.util.DBConnection;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by tsj on 16-4-4.
 */
public class ChuankeGetinfoProcessor implements PageProcessor {
    private Site site = new Site();
    private DBConnection dbConnection = new DBConnection();
    private String title;
    private String url;
    private String imgurl;
    private String category_1;
    private String category_2;
    private int collection;
    private int remark;
    private int grade;
    private double satisfaction;
    private int join_number;
    private String source_web;

    @Override
    public void process(Page page) {
        getInfo(page);
//        dbConnection.insertintoit(title,url,imgurl,category_1,category_2,collection,
//                remark,grade,satisfaction,join_number,source_web);
        System.out.println("imgurl:_____________>"+imgurl);


        if(page.getUrl().toString().equals("http://www.chuanke.com/1018455-83980.html")){
            List<String> urls = dbConnection.qureyFromTemp();
            page.addTargetRequests(urls);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    private String exchangeCategory1(String category){
        String newcategory="";
        switch (category){
            case "编程语言": newcategory =  "编程语言";break;
            case "数据库管理": newcategory = "数据处理";break;
            case "系统运维": newcategory = "操作系统";break;
            case "移动互联网": newcategory = "移动开发";break;
            case "产品/编辑/运营": newcategory = "其他";break;
            case "设计制作": newcategory = "前端开发";break;
            case "网络安全": newcategory = "其他";break;
            case "嵌入式": newcategory = "硬件";break;

        }
        return newcategory;
    }
    private void getInfo(Page page){
        url = page.getUrl().toString();
        title = page.getHtml().xpath("//h3[@class='title']/text()").toString();
        imgurl = page.getHtml().xpath("//div[@class='details-topcon']//img/@src").toString();
        category_1 = page.getHtml().xpath("//div[@class='breadcrumbs']/a[2]/text()").toString();
        category_1 = exchangeCategory1(category_1);
        category_2 = page.getHtml().xpath("//div[@class='breadcrumbs']/a[3]/text()").toString();
        collection = Integer.parseInt(page.getHtml().xpath("//div[@class='apply']/a/span/text()").toString());
        String remarkstr = page.getHtml().xpath("//a[@class='c-666']/text()").toString();
        remark = Integer.parseInt(remarkstr.substring(remarkstr.indexOf("价") + 1, remarkstr.indexOf(" ")));
        grade = 0;
        join_number = Integer.parseInt(page.getHtml().xpath("//li[@class='purchase']em[1]/text()").toString()==null?"0":page.getHtml().xpath("//li[@class='purchase']em[1]/text()").toString());
        String satisfactionstr = "";
        satisfactionstr = page.getHtml().xpath("//li[@class='purchase']em[2]/text()").toString();
//        satisfaction = Double.parseDouble(satisfactionstr.substring(1,satisfactionstr.indexOf("%")))/100.0;
        System.out.println(satisfaction);
        source_web = "百度传课网";
    }

}
