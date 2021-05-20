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
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Card fromCard;

    @ManyToOne
    private Card toCard;

    private Double amount;

    private Date date;
}
