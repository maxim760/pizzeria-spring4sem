package com.example.pizzeria.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "certificates")
@Getter
@Setter
@ToString
public class CertificateEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_user_id")
    @JsonIgnore
    private UserEntity toUser;
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_user_id")
    @JsonIgnore
    private UserEntity fromUser;
    private int price;
    @Column(name = "date_create")
    private String dateCreate;

}
