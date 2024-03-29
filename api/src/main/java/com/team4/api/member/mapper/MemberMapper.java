package com.team4.api.member.mapper;

import com.team4.api.member.dto.CreateMemberDto;
import com.team4.api.member.dto.FindAllMembersDto;
import com.team4.api.member.dto.MemberDto;
import com.team4.domain.member.Member;
import com.team4.domain.member.MembershipLevel;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public static MemberDto toDto(Member member) {
        return new MemberDto(member.getId(),
                NameMapper.toNameDto(member.getName()),
                AddressMapper.toAddressDto(member.getAddress()),
                LicensePlateMapper.toLicensePlateDto(member.getLicensePlate()),
                member.getPhoneNumber(),
                member.getEmail(),
                member.getMembershipLevel().name());
    }

    public static FindAllMembersDto toFindAllMemberDto(Member member) {
        return new FindAllMembersDto(member.getId(), NameMapper.toNameDto(member.getName()), member.getLicensePlate().getPlateNumber(), member.getPhoneNumber(), member.getEmail(), member.getRegistrationDate());
    }

    public static Member toDomain(CreateMemberDto createMemberDto) {
        return new Member(createMemberDto.getName(), createMemberDto.getAddress(), createMemberDto.getPhoneNumber(), createMemberDto.getEmail(), createMemberDto.getLicensePlate(), MembershipLevel.valueOf(createMemberDto.getMembershipLevel()));

    }
}
