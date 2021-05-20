package com.javohir.apppayment.repository;

import com.javohir.apppayment.entity.Card;
import com.javohir.apppayment.entity.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutcomeRepository extends JpaRepository<Outcome, Integer> {

}
