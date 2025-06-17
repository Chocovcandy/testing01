package com.example.testing01;

import com.example.testing01.domain.Member;
import com.example.testing01.repository.MemberService;
import com.example.testing01.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    MemberService memberService = new MemberService();

    @AfterEach
    void afterEach() {
        // 각 테스트가 끝날때마다 실행된다
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    void findAll() {

        Member member1  = new Member();
        member1.setName("member1");
        memberService.join(member1);

        Member member2  = new Member();
        member2.setName("member2");
        memberService.join(member2);

        List <Member> list = memberService.findMembers();
        int memberSize = list.size();

        //
        Assertions.assertThat (memberSize).isEqualTo (2);
}


    @Test
     void findById(){
        Member member1 = new Member();
        member1.setName("member1");
        memberService.join(member1);

        Member member = memberService.findOne(1L).get();
        Assertions.assertThat (member.getName()).isEqualTo (member1.getName());
    }


    // Practice-01 : Find Value "DataBB"
    @Test
    void testStringOne() {
        Map<String, String> data = new HashMap<>();
        data.put("dataA", "DataAA");
        data.put("dataB", "DataBB");
        data.put("dataC", "DataCC");
        data.put("dataD", "DataDD");

//        Member result = repository.findByName("DataBB").get();
//        assertThat(result).isEqualTo(data.put("dataBB"));

        String resultName = data.get("dataB");

        for (String s: data.keySet()){
            if (data.get(s).equals(resultName)){
                System.out.println(s);
            }
        }

        // lanmda code Style
        /**
         return store.values() // map values
         . stream() // java stream function
         . filter (s -> s.equals (findValue))
         . findAny(); // get data
         */
    }






    // Practice-02 : Find Value Member Name "name3"
    @Test
    void testStringTwo() {
        Map<String, Member> data2 = new HashMap<>();
        data2.put("dataA", new Member(1L, "name1"));
        data2.put("dataB", new Member(2L, "name2"));
        data2.put("dataC", new Member(3L, "name3"));
        data2.put("dataD", new Member(4L, "name4"));

    }
}