package com.team4.api.allocation;

import com.team4.domain.allocation.Allocation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class AllocationMapper {

    static AllocationDto toDto(Allocation allocation){
        return new AllocationDto(
                allocation.getId(),
                allocation.getMember().getId(),
                allocation.getParkingLot().getId(),
                allocation.getStartTime().toString(),
                allocation.getStopTime() == null ? null : allocation.getStopTime().toString(),
                calculateDuration(allocation)
        );
    }

    private static String calculateDuration(Allocation allocation){

        Duration dur = Duration.between(allocation.getStartTime(),LocalDateTime.now());
        if(allocation.getStopTime() != null){
            dur = Duration.between(allocation.getStartTime(), allocation.getStopTime());
        }
        long millis = dur.toMillis();

        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }
}
