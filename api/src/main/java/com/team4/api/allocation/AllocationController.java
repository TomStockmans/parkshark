package com.team4.api.allocation;

import com.team4.domain.allocation.Allocation;
import com.team4.domain.allocation.AllocationFilter;
import com.team4.domain.allocation.OrderFilter;
import com.team4.service.allocation.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AllocationController.RESOURCE_URL)
public class AllocationController {

    public static final String RESOURCE_URL = "/allocations";
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;
    private final AllocationService allocationService;

    @Autowired
    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AllocationDto startAllocation(@RequestBody CreateAllocationDto createAllocationDto) {
        Allocation allocation = allocationService.startAllocation(
                createAllocationDto.memberId,
                createAllocationDto.licensePlateNumber,
                createAllocationDto.parkingLotId
        );
        return AllocationMapper.toDto(allocation);
    }


    @GetMapping
    public List<AllocationDto> getAllAllocations(@RequestParam int start,
                                                 @RequestParam int limit,
                                                 @RequestParam AllocationFilter allocationFilter,
                                                 @RequestParam OrderFilter orderFilter) {

        return allocationService.getAllAllocations(start, limit, allocationFilter, orderFilter)
                .stream()
                .map(a -> AllocationMapper.toDto(a))
                .collect(Collectors.toList());

    }

    @PostMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AllocationDto stopAllocation(@PathVariable long id) {
        return AllocationMapper.toDto(allocationService.stopAllocation(id));

    }

    @GetMapping(params = "member", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<AllocationDto> getAllocationsForMember(@RequestParam long member,
                                                       @RequestParam AllocationFilter filter) {
        List<Allocation> allocations = allocationService.getByMemberId(member, filter);
        return allocations.stream()
                .map(AllocationMapper::toDto)
                .collect(Collectors.toList());
    }

}
