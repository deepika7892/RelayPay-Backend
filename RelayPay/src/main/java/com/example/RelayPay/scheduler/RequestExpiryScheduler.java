package com.example.RelayPay.scheduler;

import com.example.RelayPay.services.RelayRequestService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RequestExpiryScheduler {

    private final RelayRequestService relayRequestService;

    public RequestExpiryScheduler(
            RelayRequestService relayRequestService
    ) {
        this.relayRequestService = relayRequestService;
    }

    // Runs every 1 minute
    @Scheduled(fixedRate = 60000)
    public void autoExpireRequests() {

        System.out.println(
                "Checking expired requests..."
        );

        relayRequestService.updateExpiredRequests();
    }
}