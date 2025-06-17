package com.example.testing01.controller;

import com.example.testing01.domain.Member;
import com.example.testing01.repository.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final MemberService memberService;

    public HomeController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping ("/")
    public String home() {
        return "home";
    }

    @GetMapping ("/form")
    public String memberAddForm() {
        return "memberAddForm";
    }

//      @PostMapping("/form")
//      public String memberAddForm(
//        @RequestParam String name,
//        @RequestParam String name1,
//        @RequestParam String name2,
//      ){
//            System.out.println("memberAddForm : " + memberAddForm.getName());
//            Member member = new Member();
//            member.setName (name);
//            memberService.Join(member);
//
//            return "memberAddForm";
//
//        }

        @PostMapping("/form")
        public String memberAddForm(
            @RequestParam String name,
            @RequestParam(required = false) String name1,
            @RequestParam(required = false) String name2,
            @RequestParam(required = false) String name3
        ){

            System.out.println("Received name: " + name);

            Member member = new Member();
            member.setName(name);

            memberService.join(member);

            return "memberAddForm";
        }


        @GetMapping("/list")
        public String list(Model model) {
            List<Member> members = memberService.findMembers();
            model.addAttribute("members", members); // fixed this
            return "memberList";
        }
    }
