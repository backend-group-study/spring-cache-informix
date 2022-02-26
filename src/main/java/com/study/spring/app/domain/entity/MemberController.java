package com.study.spring.app.domain.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public Long saveMember(@RequestBody MemberDTO memberDTO) {
        return memberService.saveMember(memberDTO);
    }

    @GetMapping("/member/{id}")
    public MemberDTO findMember(@PathVariable("id") Long id) {
        return memberService.getMemberDTO(id);
    }

    @PutMapping("/member")
    public MemberDTO updateMember(@RequestBody MemberDTO memberDTO) {
        return memberService.updateMember(memberDTO);
    }

}
