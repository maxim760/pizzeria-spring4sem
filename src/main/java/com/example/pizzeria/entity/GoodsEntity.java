package com.example.pizzeria.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "goods")
@Getter
@Setter
@ToString
public class GoodsEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "goods_type")
    private String goodsType;
    private int discount;
    private int price;
    private String name;
    private String description;
    private String img;

    @ManyToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<OrderEntity>();
}
