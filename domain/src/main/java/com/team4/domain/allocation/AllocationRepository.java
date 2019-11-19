package com.team4.domain.allocation;

import org.springframework.data.domain.Pageable;
import com.team4.domain.member.Member;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AllocationRepository extends PagingAndSortingRepository<Allocation, Long>, JpaSpecificationExecutor<Allocation> {

    List<Allocation> findAllByMemberIs(Member member);
    List<Allocation> findAllByMember_Id(long id);
    List<Allocation> findAllByStopTimeNullAndMember_Id(long id);
    List<Allocation> findAllByStopTimeNotNullAndMember_Id(long id);
    List<Allocation> findAllByStopTimeNullAndParkingLot_Id(long id);

    List<Allocation> findBy(Pageable pageable);

    List<Allocation> findByStopTimeNull(Pageable pageable);

    List<Allocation> findByStopTimeNotNull(Pageable pageable);

}
