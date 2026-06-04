package com.example.RelayPay.services;

import com.example.RelayPay.dto.RequestStatsDTO;
import com.example.RelayPay.dto.CreateRequestDTO;
import com.example.RelayPay.entity.RelayRequest;
import com.example.RelayPay.repository.RelayRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RelayRequestService {

    @Autowired
    private RelayRequestRepository relayRequestRepository;

    public RelayRequest createRequest(CreateRequestDTO requestDTO) {

        RelayRequest request = new RelayRequest();

        request.setUserEmail(requestDTO.getUserEmail());

        request.setRequestType(requestDTO.getRequestType());

        request.setStatus("PENDING");

        // Generate secure token
        String token = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        request.setSecureToken(token);

        request.setCreatedAt(LocalDateTime.now());

        // Expiry after 5 minutes
        request.setExpiryTime(
                LocalDateTime.now().plusMinutes(5)
        );

        return relayRequestRepository.save(request);
    }

    public String approveRequest(
            String requestId,
            String secureToken
    ) {

        RelayRequest request =
                relayRequestRepository.findByIdAndSecureToken(
                        requestId,
                        secureToken
                );

        if (request == null) {
            return "Invalid request or token";
        }

        // Check expiry
        if (request.getExpiryTime().isBefore(LocalDateTime.now())) {
            return "Request expired";
        }

        request.setStatus("APPROVED");

        relayRequestRepository.save(request);

        return "Request approved successfully";
    }

    public List<RelayRequest> getUserRequests(String email) {

        return relayRequestRepository.findByUserEmail(email);
    }

    @Scheduled(fixedRate = 60000)
    public List<RelayRequest> updateExpiredRequests() {

        List<RelayRequest> requests =
                relayRequestRepository.findAll();

        for (RelayRequest request : requests) {

            if (
                    request.getStatus().equals("PENDING")
                            &&
                            request.getExpiryTime().isBefore(LocalDateTime.now())
            ) {

                request.setStatus("EXPIRED");

                relayRequestRepository.save(request);
            }
        }

        return requests;
    }

    public RequestStatsDTO getRequestStats(String email) {

        List<RelayRequest> requests =
                relayRequestRepository.findByUserEmail(email);

        RequestStatsDTO stats = new RequestStatsDTO();

        stats.setTotalRequests(requests.size());

        int approved = 0;
        int pending = 0;
        int expired = 0;

        for (RelayRequest request : requests) {

            switch (request.getStatus()) {

                case "APPROVED":
                    approved++;
                    break;

                case "PENDING":
                    pending++;
                    break;

                case "EXPIRED":
                    expired++;
                    break;
            }
        }

        stats.setApprovedRequests(approved);
        stats.setPendingRequests(pending);
        stats.setExpiredRequests(expired);

        return stats;
    }

    public String deleteRequest(String requestId) {

        relayRequestRepository.deleteById(requestId);

        return "Request Deleted Successfully";
    }

    public List<RelayRequest> getAllRequests() {

        return relayRequestRepository.findAll();
    }
}