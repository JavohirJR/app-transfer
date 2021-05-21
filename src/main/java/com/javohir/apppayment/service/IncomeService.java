package com.javohir.apppayment.service;

import com.javohir.apppayment.entity.Income;
import com.javohir.apppayment.payload.ApiResponse;
import com.javohir.apppayment.repository.IncomeRepository;
import com.javohir.apppayment.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class IncomeService {


    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    IncomeRepository incomeRepository;

    public ApiResponse getAll(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);

        if (username.equals("admin")) {
            List<Income> all = incomeRepository.findAll();
            if (all.isEmpty())
                return new ApiResponse("Transaksiya hali amalga oshirilmagan", false);
            return new ApiResponse("Success", true, all);
        }

        List<Income> incomes = incomeRepository.findIncomes(username);
        if (incomes.isEmpty()) {
            return new ApiResponse("Transaksiya hali amalga oshirilmagan", false);
        }
        return new ApiResponse("Success", true, incomes);
    }

}
