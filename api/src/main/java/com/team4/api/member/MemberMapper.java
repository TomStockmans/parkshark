package com.team4.api.member;

import com.team4.domain.member.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public static MemberDto mapToMemberDto(Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        memberDto.setAddress(member.getAddress());
        memberDto.setPhoneNumber(member.getPhoneNumber());
        memberDto.setLicensePlate(member.getLicensePlate());
        memberDto.setRegistrationDate((member.getRegistrationDate()));
        return memberDto;
    }

    public static FindAllMembersDto mapToFindAllMemberDto(Member member) {
        FindAllMembersDto findAllMembersDto = new FindAllMembersDto();
        findAllMembersDto.setName(member.getName());
        findAllMembersDto.setPlateNumber(member.getLicensePlate().getPlateNumber());
        findAllMembersDto.setPhoneNumber(member.getPhoneNumber());
        findAllMembersDto.setRegistrationDate(member.getRegistrationDate());

        return findAllMembersDto;
    }
}
