package com.team4.domain.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    private MemberRepository memberRepository;

    @Test
    void findAll_givenAMemberRepository_whenFindAll_returnListOfMembers() {
        memberRepository.save(new Member());
        Assertions.assertFalse(memberRepository.findAll().isEmpty());
    }
}