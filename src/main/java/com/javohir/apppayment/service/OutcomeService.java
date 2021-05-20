package com.javohir.apppayment.service;

import com.javohir.apppayment.entity.Card;
import com.javohir.apppayment.entity.Income;
import com.javohir.apppayment.entity.Outcome;
import com.javohir.apppayment.payload.ApiResponse;
import com.javohir.apppayment.payload.OutcomeDto;
import com.javohir.apppayment.repository.CardRepository;
import com.javohir.apppayment.repository.IncomeRepository;
import com.javohir.apppayment.repository.OutcomeRepository;
import com.javohir.apppayment.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Optional;

@Service
public class OutcomeService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    OutcomeRepository outcomeRepository;


    public ApiResponse add(OutcomeDto outcomeDto, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);//username

        Optional<Card> optionalCardFrom = cardRepository.findById(outcomeDto.getFromCardId());
        if (!optionalCardFrom.isPresent()) return new ApiResponse("from card not found!", false);

        Optional<Card> optionalCardTo = cardRepository.findById(outcomeDto.getToCardId());
        if (!optionalCardTo.isPresent()) return new ApiResponse("To card not found!", false);

        if (outcomeDto.getAmount() < 0) return new ApiResponse("Manfiy bo'lamsin", false);

        if (!username.equals(optionalCardFrom.get().getUsername()))
            return new ApiResponse("BU szni karta emas!", false);

        if (outcomeDto.getAmount() > optionalCardFrom.get().getBalance() - (outcomeDto.getAmount() * outcomeDto.getCommission()))
            return new ApiResponse("Pul yetmadi!", false);

        if (optionalCardFrom.get().getCardNumber().equals(optionalCardTo.get().getCardNumber()))
            return new ApiResponse("O'zingdan o'zingga pul ko'chirma mashennik!", false);

        if (optionalCardFrom.get().isActive() && optionalCardTo.get().isActive()) {

            Outcome outcome = new Outcome();
            outcome.setAmount(outcomeDto.getAmount());
            outcome.setCommissionAmount(outcomeDto.getCommission());
            outcome.setFromCard(optionalCardFrom.get());
            outcome.setToCard(optionalCardTo.get());
            outcome.setDate(new Date(System.currentTimeMillis()));

            Income income = new Income();
            income.setAmount(outcomeDto.getAmount());
            income.setFromCard(optionalCardFrom.get());
            income.setToCard(optionalCardTo.get());
            income.setDate(new Date(System.currentTimeMillis()));


            Card cardFrom = optionalCardFrom.get();
            double total = cardFrom.getBalance() - outcomeDto.getAmount() - (outcomeDto.getAmount() * outcomeDto.getCommission());
            cardFrom.setBalance(total);

            Card cardTo = optionalCardTo.get();
            cardTo.setBalance(cardTo.getBalance() + outcomeDto.getAmount());

            outcomeRepository.save(outcome);
            incomeRepository.save(income);
            cardRepository.save(cardFrom);
            cardRepository.save(cardTo);
        }
        return new ApiResponse("Successfully transferred!!!", true);
    }

    public ApiResponse getOutcomes(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getHeader("Authorization");
        token = token.substring(7);
        String username = jwtProvider.getUsernameFromToken(token);

        outcomeRepository


        return new ApiResponse("Success", true);
    }

}
