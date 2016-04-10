package com.crawler;

import com.crawler.pageProcessor.MoocProcessor;
import com.crawler.pageProcessor.MoocProcessorImg;
import com.crawler.pipeline.MoocPipeline;
import us.codecraft.webmagic.Spider;

/**
 * Created by tsj on 16-4-1.
 */
public class StartSpider {


    public static void main(String args[]){
//       Spider spider = new  Spider(new MoocProcessorImg());//
//        spider.addUrl("http://www.imooc.com/view/347");
//        spider.addUrl("http://www.imooc.com/course/list?page=24");
//        spider.run();

        Spider spider = new  Spider(new MoocProcessor());//
        spider.addUrl("http://www.imooc.com/view/474");
        spider.run();
    }

}