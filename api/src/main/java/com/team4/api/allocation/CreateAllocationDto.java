package com.team4.api.allocation;

public class CreateAllocationDto {
    public final long memberId;
    public final String licensePlateNumber;
    public final long parkingLotId;

    public CreateAllocationDto(long memberId, String licensePlateNumber, long parkingLotId) {
        this.memberId = memberId;
        this.licensePlateNumber = licensePlateNumber;
        this.parkingLotId = parkingLotId;
    }
}
