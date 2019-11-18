package com.team4.domain.allocation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AllocationRepository extends PagingAndSortingRepository<Allocation, Long> {


    //findall
    public List<Allocation> getAllByOrderByStartTime(Pageable pageable);

    //filter on active allocations
    public List<Allocation> findAllByStopTimeIsNullAndOrderByStartTime(Pageable pageable);

    //filter on stopped allocations
    public List<Allocation> findAllByStopTimeIsNotNullAndOrderByStartTime(Pageable pageable);





}
