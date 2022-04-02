package com.example.pizzeria.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
public class EmployeeEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private Long salary;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_start")
    private String dateStart;
    @Column(name = "date_end")
    private String dateEnd;
    private String profession;
}
