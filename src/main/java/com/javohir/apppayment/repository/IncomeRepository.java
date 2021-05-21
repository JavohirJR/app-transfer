package com.javohir.apppayment.repository;

import com.javohir.apppayment.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
    @Query(value = "select i.id, i.from_card_id, i.to_card_id, i.amount, i.date\n" +
            "from income as i\n" +
            "         inner join card c\n" +
            "                    on c.username =:user \n" +
            "where c.id = i.to_card_id", nativeQuery = true)
    List<Income> findIncomes(String user);
}
