package com.javohir.apppayment.repository;

import com.javohir.apppayment.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
    boolean existsByCardNumber(String cardNumber);
}
