package com.team4.domain.allocation;

import org.springframework.data.domain.Pageable;
import com.team4.domain.member.Member;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AllocationRepository extends PagingAndSortingRepository<Allocation, Long>, JpaSpecificationExecutor<Allocation> {

    List<Allocation> findAllByMemberIs(Member member);
    List<Allocation> findAllByStopTimeNullAndMember_Id(long id);
    List<Allocation> findAllByStopTimeNullAndParkingLot_Id(long id);

    static Specification<Allocation> hasParkingLotId(long id) {
        return (allocation, query, builder) -> builder.equal(allocation.get("parkingLot"), id);
    }

    static Specification<Allocation> hasMemberId(long id) {
        return (allocation, query, builder) -> builder.equal(allocation.get("member"), id);
    }

    static Specification<Allocation> isActive() {
        return (allocation, query, builder) -> builder.isNull(allocation.get("stopTime"));
    }

    List<Allocation> findBy(Pageable pageable);

    List<Allocation> findByStopTimeNull(Pageable pageable);

    List<Allocation> findByStopTimeNotNull(Pageable pageable);

}
