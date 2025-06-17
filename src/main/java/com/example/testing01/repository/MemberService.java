package com.example.testing01.repository;

import com.example.testing01.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    public MemberService(SpringDataJpaMemberRepository repository) {
        this.repository = repository;
    }

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
 private final SpringDataJpaMemberRepository repository;
    // 회원 가입 // this logic
    public Long join (Member member) {
        // Question
//        System.out.println("before member id : "+ member.getId()); // ?
//        memberRepository.save(member);
//        System.out.println("after member id : "+ member.getId()); // ?


        Optional<Member> findMember = repository.findByName(member.getName());
        if (findMember.isEmpty()){
           repository.save(member);
            return member.getId();
        }else{
            throw new IllegalStateException ("duplicate member");
        }
    }

    // 전체 회원 목록
    public List<Member> findMembers() {
        return repository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }

    public Optional<Member> findByName(String memberid) {
        return repository.findByName(memberid);
    }

//    public Optinal<Member>(String name) {
//        return memberRepository.findById(memberId);
//    }
}