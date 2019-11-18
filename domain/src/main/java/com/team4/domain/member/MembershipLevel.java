package com.team4.domain.member;

public enum MembershipLevel {
    BRONZE(0, 1, 4),
    SILVER(10, 0.8, 6),
    GOLD(40, 0.7, 24);

    private final int monthlyCost;
    private final double parkingReduction;
    private final int parkingTime;

    MembershipLevel(int monthlyCost, double parkingReduction, int parkingTime) {
        this.monthlyCost = monthlyCost;
        this.parkingReduction = parkingReduction;
        this.parkingTime = parkingTime;
    }

}
