package com.javohir.apppayment.service;

import com.javohir.apppayment.entity.Card;
import com.javohir.apppayment.payload.ApiResponse;
import com.javohir.apppayment.payload.CardDto;
import com.javohir.apppayment.repository.CardRepository;
import com.javohir.apppayment.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    JwtProvider jwtProvider;


    public ApiResponse add(CardDto cardDto, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        token = token.substring(7);

        String username = jwtProvider.getUsernameFromToken(token);//username

        if (cardRepository.existsByCardNumber(cardDto.getCardNumber())) return new ApiResponse("Already exist", false);
        Card card = new Card();
        card.setUsername(username);
        card.setCardNumber(cardDto.getCardNumber());
        card.setExpiredDate(cardDto.getExpiredDate());

        cardRepository.save(card);
        return new ApiResponse("New card added!", true);
    }


    public ApiResponse edit(Integer id, CardDto cardDto, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        token = token.substring(7);

        String username = jwtProvider.getUsernameFromToken(token);//username

        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) return new ApiResponse("Not Found Card Id", false);

        if (!username.equals(optionalCard.get().getUsername())) return new ApiResponse("Szda bunaqa karta yo'q", false);

        Card card = optionalCard.get();
        if (card.isActive()) {
            if (cardDto.getBalance() > 0) {
                card.setBalance(cardDto.getBalance());
            } else {
                return new ApiResponse("Tranzaksiya summasi notogri!", false);
            }
            if (cardDto.getExpiredDate() != null) {
                card.setExpiredDate(cardDto.getExpiredDate());
            }
        } else {
            return new ApiResponse("Karta bloklangan!", false);
        }
        cardRepository.save(card);
        return new ApiResponse("Card Edited!", true);
    }
}
