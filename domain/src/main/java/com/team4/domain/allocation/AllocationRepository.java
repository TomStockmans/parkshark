package com.team4.domain.allocation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface AllocationRepository extends CrudRepository<Allocation, Long>, JpaSpecificationExecutor<Allocation> {

    static Specification<Allocation> hasParkingLotId(long id) {
        return (allocation, query, builder) -> builder.equal(allocation.get("parkingLot"), id);
    }

    static Specification<Allocation> hasMemberId(long id) {
        return (allocation, query, builder) -> builder.equal(allocation.get("member"), id);
    }

    static Specification<Allocation> isActive() {
        return (allocation, query, builder) -> builder.isNull(allocation.get("stopTime"));
    }
/*

    //findall
    public List<Allocation> getAllByOrderByStartTime(Pageable pageable);

    //filter on active allocations
    public List<Allocation> findAllByStopTimeIsNullAndOrderByStartTime(Pageable pageable);

    //filter on stopped allocations
    public List<Allocation> findAllByStopTimeIsNotNullAndOrderByStartTime(Pageable pageable);
    */
}
