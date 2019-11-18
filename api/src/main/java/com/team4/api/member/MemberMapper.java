package com.team4.api.member;

import com.team4.domain.member.Member;
import com.team4.domain.member.MembershipLevel;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public static MemberDto mapToMemberDto(Member member){
        return new MemberDto(member.getId(), member.getName(), member.getAddress(), member.getLicensePlate(), member.getPhoneNumber(), member.getEmail(), member.getMembershipLevel().name());
    }

    public static FindAllMembersDto mapToFindAllMemberDto(Member member) {
        return new FindAllMembersDto(member.getId(), member.getName(), member.getLicensePlate().getPlateNumber(), member.getPhoneNumber(), member.getEmail(), member.getRegistrationDate());
    }

    public static Member mapToMember(MemberDto memberDto) {
        return new Member(memberDto.getName(), memberDto.getAddress(), memberDto.getPhoneNumber(), memberDto.getEmail(), memberDto.getLicensePlate(), MembershipLevel.valueOf(memberDto.getMembershipLevel()));
    }
}
