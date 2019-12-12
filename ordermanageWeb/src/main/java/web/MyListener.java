//package web;
//
//import util.RedisServerUtil;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class MyListener implements ServletContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        RedisServerUtil.startRedis();
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        RedisServerUtil.stopRedis();
//    }
//}
