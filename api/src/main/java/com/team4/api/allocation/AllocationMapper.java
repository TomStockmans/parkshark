package com.team4.api.allocation;

import com.team4.domain.allocation.Allocation;

public class AllocationMapper {

    static AllocationDto toDto(Allocation allocation){
        return new AllocationDto(
                allocation.getId(),
                allocation.getMember().getId(),
                allocation.getParkingLot().getId(),
                allocation.getStartTime().toString(),
                allocation.getStopTime().toString()
        );
    }
}
