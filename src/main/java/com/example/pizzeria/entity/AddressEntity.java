package com.example.pizzeria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@ToString
public class AddressEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String country;
    private String city;
    private String street;
    private String house;
    private String entrance;
    private int flat;
    private String commentary;

}
