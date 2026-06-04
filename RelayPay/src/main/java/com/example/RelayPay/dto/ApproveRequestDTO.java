package com.example.RelayPay.dto;

public class ApproveRequestDTO {

    private String requestId;

    private String secureToken;

    public ApproveRequestDTO() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSecureToken() {
        return secureToken;
    }

    public void setSecureToken(String secureToken) {
        this.secureToken = secureToken;
    }
}