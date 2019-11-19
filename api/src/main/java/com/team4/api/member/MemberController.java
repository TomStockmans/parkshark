package com.team4.api.member;

import com.team4.api.member.dto.CreateMemberDto;
import com.team4.api.member.dto.FindAllMembersDto;
import com.team4.api.member.dto.MemberDto;
import com.team4.api.member.mapper.MemberMapper;
import com.team4.domain.member.Member;
import com.team4.domain.member.MemberException;
import com.team4.service.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(MemberController.RESOURCE_URL)
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    public static final String RESOURCE_URL = "/members";
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<FindAllMembersDto> getAllMembers() {
        return memberService.getAllMembers().stream()
                .map(MemberMapper::toFindAllMemberDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MemberDto getMemberById(@PathVariable long id) {
        try {
            Member member = memberService.getMemberById(id);
            return MemberMapper.toDto(member);
        } catch (MemberException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto registerMember(@RequestBody CreateMemberDto createMemberDto) {
        Member memberToCreate = memberService.registerMember(MemberMapper.toDomain(createMemberDto));
        return MemberMapper.toDto(memberToCreate);
    }

}