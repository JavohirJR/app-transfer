package com.javohir.apppayment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutcomeDto {

    private Integer fromCardId;
    private Integer toCardId;
    private double amount;
    private double commission;

}
