package com.team4.api.allocation;


public class AllocationDto {
    public final long allocationId;
    public final long memberId;
    public final long parkingLotId;
    public final String startTime;
    public final String stopTime;

    public AllocationDto(long allocationId, long memberId, long parkingLotId, String startTime, String stopTime) {
        this.allocationId = allocationId;
        this.memberId = memberId;
        this.parkingLotId = parkingLotId;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }
}
