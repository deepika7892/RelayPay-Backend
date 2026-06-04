package com.example.RelayPay.controller;

import com.example.RelayPay.dto.ApproveRequestDTO;
import com.example.RelayPay.dto.CreateRequestDTO;
import com.example.RelayPay.dto.RequestStatsDTO;
import com.example.RelayPay.entity.RelayRequest;
import com.example.RelayPay.services.RelayRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin("*")
public class RelayRequestController {

    @Autowired
    private RelayRequestService relayRequestService;

    @PostMapping("/create")
    public RelayRequest createRequest(
            @RequestBody CreateRequestDTO requestDTO
    ) {

        return relayRequestService.createRequest(requestDTO);
    }
    @PostMapping("/approve")
    public String approveRequest(
            @RequestBody ApproveRequestDTO requestDTO
    ) {

        return relayRequestService.approveRequest(
                requestDTO.getRequestId(),
                requestDTO.getSecureToken()
        );
    }
    @GetMapping("/user/{email}")
    public List<RelayRequest> getUserRequests(
            @PathVariable String email
    ) {

        return relayRequestService.getUserRequests(email);
    }
    @GetMapping("/update-expired")
    public List<RelayRequest> updateExpiredRequests() {

        return relayRequestService.updateExpiredRequests();
    }
    @GetMapping("/stats/{email}")
    public RequestStatsDTO getRequestStats(
            @PathVariable String email
    ) {

        return relayRequestService.getRequestStats(email);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteRequest(
            @PathVariable String id
    ) {

        return relayRequestService.deleteRequest(id);
    }

    @GetMapping("/all")
    public List<RelayRequest> getAllRequests() {

        return relayRequestService.getAllRequests();
    }
}