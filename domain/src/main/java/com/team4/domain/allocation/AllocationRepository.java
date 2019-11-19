package com.team4.domain.allocation;

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
}
