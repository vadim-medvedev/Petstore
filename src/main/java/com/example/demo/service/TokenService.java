package com.example.demo.service;


import com.example.demo.model.Token;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TokenService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    public Token generate(String username){
        User user = userRepository.getByUsername(username).get();
        String tokenId = UUID.randomUUID().toString();
        Token token = new Token(0,tokenId,user);
        tokenRepository.save(token);
        return token;
    }

    public boolean remove(String tokenId){
        if (tokenRepository.existsByTokenId(tokenId)){
            tokenRepository.delete(tokenRepository.getByTokenId(tokenId).get());
            return true;
        }else {
            return false;
        }
    }
}
