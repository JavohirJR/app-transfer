package com.javohir.apppayment.controller;

import com.javohir.apppayment.payload.ApiResponse;
import com.javohir.apppayment.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

    @Autowired
    IncomeService incomeService;

    //USERLAR va ADMIN uchun incomelarni olish
    @GetMapping
    public HttpEntity<?> getAll(HttpServletRequest httpServletRequest) {
        ApiResponse response = incomeService.getAll(httpServletRequest);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

}
