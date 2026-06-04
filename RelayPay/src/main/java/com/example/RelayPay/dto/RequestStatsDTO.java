package com.example.RelayPay.dto;

public class RequestStatsDTO {

    private int totalRequests;
    private int approvedRequests;
    private int pendingRequests;
    private int expiredRequests;

    public RequestStatsDTO() {
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public int getApprovedRequests() {
        return approvedRequests;
    }

    public void setApprovedRequests(int approvedRequests) {
        this.approvedRequests = approvedRequests;
    }

    public int getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(int pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    public int getExpiredRequests() {
        return expiredRequests;
    }

    public void setExpiredRequests(int expiredRequests) {
        this.expiredRequests = expiredRequests;
    }
}