package com.example.RelayPay.repository;

import com.example.RelayPay.entity.RelayRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RelayRequestRepository extends MongoRepository<RelayRequest, String> {
    RelayRequest findByIdAndSecureToken(
            String id,
            String secureToken
    );
    List<RelayRequest> findByUserEmail(String userEmail);
}