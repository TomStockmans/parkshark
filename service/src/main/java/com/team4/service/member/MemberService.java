package com.team4.service.member;

import com.team4.domain.member.Member;
import com.team4.domain.member.MemberException;
import com.team4.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        var member = memberRepository.findById(id);
        if (member.isEmpty()) {
            throw new MemberException("Could not find member with that id: " + id);
        }
        return member.get();
    }

}
