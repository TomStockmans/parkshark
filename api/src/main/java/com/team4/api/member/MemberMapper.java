package com.team4.api.member;

import com.team4.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public static Member mapToMember(MemberDto memberDto) {
        return new Member(memberDto.getName(), memberDto.getAddress(), memberDto.getPhoneNumber(), memberDto.getEmail(), memberDto.getLicensePlate());
    }

    public static MemberDto mapToMemberDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        memberDto.setAddress(member.getAddress());
        memberDto.setPhoneNumber(member.getPhoneNumber());
        memberDto.setLicensePlate(member.getLicensePlate());
        memberDto.setRegistrationDate((member.getRegistrationDate()));
        return memberDto;
    }
}
