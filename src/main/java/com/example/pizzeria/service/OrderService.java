package com.example.pizzeria.service;

import com.example.pizzeria.DTO.CreateOrderDTO;
import com.example.pizzeria.Helpers.Utilities;
import com.example.pizzeria.entity.GoodsEntity;
import com.example.pizzeria.entity.OrderEntity;
import com.example.pizzeria.entity.UserEntity;
import com.example.pizzeria.repository.GoodsRepo;
import com.example.pizzeria.repository.OrderRepo;
import com.example.pizzeria.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private GoodsRepo goodsRepo;
    @Autowired
    private UserRepo userRepo;

    @Value("${delivery.price}")
    private int deliveryPrice;

    public OrderEntity createOrder(CreateOrderDTO orderDTO, UserEntity currentUser) throws Exception {
        if(orderDTO.getGoodsIds().size() == 0) {
            throw new Exception("В заказе должен быть минимум 1 товар");
        }
        OrderEntity newOrder = new OrderEntity();
        Date currentDate = new Date();
        SimpleDateFormat formatter = Utilities.getDefaultDateFormat();
        String createDate = formatter.format(currentDate);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Long timestampTime = timestamp.getTime();
        for(UUID goodsItemId: orderDTO.getGoodsIds()) {
            GoodsEntity goodsItemFromDb = goodsRepo.findById(goodsItemId).orElseThrow(() -> new Exception("Товар не найден"));
            newOrder.addGoods(goodsItemFromDb);
        }
        newOrder.setUser(currentUser);
        newOrder.setDateCreate(createDate);
        int price = 0;
        for(GoodsEntity goodsItem: newOrder.getGoods()) {
            price += goodsItem.getCurrentPrice();
        }
        boolean withDelivery = orderDTO.getWithDelivery() != null;
        if(withDelivery) {
            price += deliveryPrice;
        }
        if(price > currentUser.getCash()) {
            throw new Exception("Недостаточно денег на балансе");
        }
        newOrder.setPrice(price);
        currentUser.setCash(currentUser.getCash() - price);
        newOrder.setTimestamp(timestampTime);
        newOrder.setWithDelivery(withDelivery);
        for(GoodsEntity goodsItem: newOrder.getGoods()) {
            goodsRepo.save(goodsItem);
        }
        orderRepo.save(newOrder);
        userRepo.save(currentUser);
        return newOrder;

    }

    public List<OrderEntity> getOrders(UserEntity user) {
        return orderRepo.getAllOrders(user.getId());
    }

    public Long getSummaryCost(UserEntity user) {
        Long result = orderRepo.selectTotalCost(user.getId());
        return result == null ? 0L : result;
    }
}
