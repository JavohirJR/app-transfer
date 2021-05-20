package com.javohir.apppayment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private String cardNumber;
    private Date expiredDate;
    private double balance;

}
