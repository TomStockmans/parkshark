package com.team4.domain.allocation;

import com.team4.domain.member.Member;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AllocationRepository extends CrudRepository<Allocation, Long>, JpaSpecificationExecutor<Allocation> {
    List<Allocation> findAllByMemberIs(Member member);
    List<Allocation> findAllByStopTimeNullAndMember_Id(long id);
    List<Allocation> findAllByStopTimeNullAndParkingLot_Id(long id);

}
