package com.javohir.apppayment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(unique = true)
    private String cardNumber;
    private double balance = 0;

    private Date expiredDate;

    private boolean active = true;
}
