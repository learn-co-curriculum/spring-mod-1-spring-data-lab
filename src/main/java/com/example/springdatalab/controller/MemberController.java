package com.example.springdatalab.controller;

import com.example.springdatalab.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Uncomment the section below little-by-little as you work through these

/*
    @PostMapping("/member")
    public ResponseEntity<String> addMember(@Valid @RequestBody MemberDTO member) {
        String status = memberService.addMember(member);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/member/{lastName}")
    public MemberDTO getMember(@PathVariable String lastName) {
        return memberService.getMember(lastName);
    }

    @PutMapping("/member/{memberId}")
    public MemberDTO updateMember(@PathVariable Integer memberId, @RequestBody MemberDTO member) {
        return memberService.updateMember(memberId, member);
    }

    @DeleteMapping("/member/{memberId}")
    public String deleteMember(@PathVariable Integer memberId) {
        return memberService.deleteMember(memberId);
    }
*/

}
