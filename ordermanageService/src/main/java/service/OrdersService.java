package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.OrdersDao;
import dao.Orders_travellerDao;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private Orders_travellerDao orders_travellerDao;

    public PageInfo<Orders> findAllOrders(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Orders> allOrders = ordersDao.findAllOrders();
        return new PageInfo<>(allOrders);
    }

    public Orders findOrdersById(String id){
        return ordersDao.findOdersDetail(id);
    }

    public void addOrders(Orders orders, List<Traveller> listT){
        ordersDao.addOrders(orders);

        Orders_Travellers ot = new Orders_Travellers();
        for(Traveller t:listT){
            ot.setOrderId(orders.getId());
            ot.setTravellerId(t.getId());
            orders_travellerDao.ordersConnectTravellers(ot);
        }
    }

//    //    将订单信息存入redis中
//    public void addOrdersInRedis(Orders orders){
//        ObjectMapper o = new ObjectMapper();
//        String orderRedis = null;
//        try {
//            orderRedis = o.writeValueAsString(orders);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        Jedis jedis = jedisPool.getResource();
//        jedis.setex(orders.getId(),15*60,orderRedis);
//    }

//    public Orders findOrderInRedis(String key) {
//        ObjectMapper o = new ObjectMapper();
//        Jedis jedis = jedisPool.getResource();
//        String s = jedis.get(key);
//        Orders orderss = new Orders();
//        try {
//            orderss = o.readValue(s, Orders.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return orderss;
//    }

    public Orders packageOrder(String orderNum, Member m, Product p, List<Traveller> listT){
        Orders orders = new Orders();
        orders.setId(UUID.randomUUID().toString());
        orders.setOrderNum(orderNum);
        orders.setOrderTime(new Date());
        orders.setPeopleCount(listT.size());
        orders.setOrderDesc("无说明");
        orders.setPayType(2);
        orders.setOrderStatus(0);
        orders.setMember(m);
        orders.setProduct(p);
        return orders;
    }

    public void checkIfPay(){
        List<Orders> noPay = ordersDao.findNoPay();
        for(Orders o:noPay){
            long x = (new Date()).getTime()-o.getOrderTime().getTime();
            long y = 900000;
            if( x > y){
                ordersDao.delOrder(o.getId());
            }
        }
    }

    public void payOrders(String id){
        ordersDao.payOrders(id);
    }
}
