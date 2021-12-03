package com.example.demo.repository;

import com.example.demo.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> getByTokenId(String tokenId);
    boolean existsByTokenId(String tokenId);
}
