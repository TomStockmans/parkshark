package com.team4.api.member;

import com.team4.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(MemberController.RESOURCE_URL)
public class MemberController {

    public static final String RESOURCE_URL = "/member";
    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<MemberDto> getAllMembers() {
        return memberService.getAllMembers().stream()
                .map(MemberMapper::mapToMemberDto)
                .collect(Collectors.toList());
    }

}
