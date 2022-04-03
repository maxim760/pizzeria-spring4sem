package com.example.pizzeria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name = "date_create")
    private String dateCreate;
    @Column(name = "with_delivery")
    private boolean withDelivery;
    private int price;

    @JoinTable(
            name = "order_goods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<GoodsEntity> goods = new HashSet<>();

    public void addGoods(GoodsEntity goodsItem) {
        this.goods.add(goodsItem);
        List<OrderEntity> currentOrders = goodsItem.getOrders();
        currentOrders.add(this);
        goodsItem.setOrders(currentOrders);
    }
}
