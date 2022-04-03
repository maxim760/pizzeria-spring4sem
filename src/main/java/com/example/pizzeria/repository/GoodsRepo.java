package com.example.pizzeria.repository;

import com.example.pizzeria.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GoodsRepo extends JpaRepository<GoodsEntity, UUID> {
    List<GoodsEntity> findAllByNameContainingIgnoreCaseAndPriceBetweenOrDescriptionContainingIgnoreCaseAndPriceBetweenOrGoodsTypeContainingIgnoreCaseAndPriceBetween(String name,int min1, int max1, String description, int min2, int max2, String goodsType, int min3, int max3);

    default List<GoodsEntity> filterByFields(String name, int minPrice, int maxPrice) {
        return findAllByNameContainingIgnoreCaseAndPriceBetweenOrDescriptionContainingIgnoreCaseAndPriceBetweenOrGoodsTypeContainingIgnoreCaseAndPriceBetween(name, minPrice, maxPrice, name, minPrice, maxPrice, name, minPrice, maxPrice);

    }
}
