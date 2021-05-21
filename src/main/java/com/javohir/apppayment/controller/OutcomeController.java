package com.javohir.apppayment.controller;

import com.javohir.apppayment.payload.ApiResponse;
import com.javohir.apppayment.payload.OutcomeDto;
import com.javohir.apppayment.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {

    @Autowired
    OutcomeService outcomeService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody OutcomeDto outcomeDto, HttpServletRequest httpServletRequest) {
        ApiResponse response = outcomeService.add(outcomeDto, httpServletRequest);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    //USERLAR va ADMIN uchun outcomelarni olish
    @GetMapping
    public HttpEntity<?> get(HttpServletRequest httpServletRequest) {
        ApiResponse response = outcomeService.getOutcomes(httpServletRequest);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }


}
