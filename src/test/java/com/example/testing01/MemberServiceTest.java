package com.example.testing01;

import com.example.testing01.domain.Member;
import com.example.testing01.repository.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void memberJoin() {
        Member pmem = new Member();
        pmem.setName("ppppppppp");
        //
        long returnMemberId = memberService.join(pmem);

        //
        Assertions.assertThat(returnMemberId).isEqualTo(1);

//        // why ?
//        Member member2 = new Member();
//        member2.setName("member2");
//        //
//        long returnMemberId2 = memberService.join(member2);
//
//        //
//        Assertions.assertThat(returnMemberId2).isEqualTo(1);

    }


    @Test
    void memberNameCheck(){
        Member member1 = new Member();
        member1.setName("member1");
        Member member2 = new Member();
        member1.setName("member1");
        //
        memberService.join(member1);
        memberService.join(member2);
        //
       Member memberFindByName =  memberService.findByName("member1").get();
       Assertions.assertThat(memberFindByName.getName()).isEqualTo("member1");
    }
}
