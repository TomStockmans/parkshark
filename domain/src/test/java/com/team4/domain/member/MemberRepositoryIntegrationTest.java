package com.team4.domain.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryIntegrationTest {

    private MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryIntegrationTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    void findAll_givenAMemberRepository_whenFindAll_returnListOfMembers() {
        memberRepository.save(new Member());
        Assertions.assertFalse(memberRepository.findAll().isEmpty());
    }

    @Test
    void saveMember_givenAMember_whenSaved_thenExistsInRepository() {
        Member member = new Member(new Name("Elvis", "Presley"), new Address("Main Boulevard", "69", "9999", "USA"), "0123456789", "elvis.presley@yahoo.com", new LicensePlate("KING888", "USA"));
        Member saved = memberRepository.save(member);
        Assertions.assertEquals(member, saved);
    }

}