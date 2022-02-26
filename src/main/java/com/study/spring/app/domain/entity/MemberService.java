package com.study.spring.app.domain.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final static MemberDTO memberDTO = new MemberDTO();

    private final MemberRepository memberRepository;

    private final ApplicationContext applicationContext;

    @Cacheable(cacheNames = "memberData", key = "#id")
    public MemberDTO getMemberDTO(final Long id) {
        log.info("캐시 키 값을 찾지 못했습니다.");
        return cacheMemberDTO(id);
    }

    @CachePut(cacheNames = "memberData", key = "#id")
    public MemberDTO cacheMemberDTO(final Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return new MemberDTO(member);
    }

    @CacheEvict(cacheNames = "memberData", key = "#id")
    public boolean deleteCacheData(final Long id) {
        log.info("캐시 삭제");
        return true;
    }

    @Transactional
    public Long saveMember(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO.toEntity()).getId();
    }

    @Transactional
    public MemberDTO updateMember(MemberDTO memberDTO) {
        Member member = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new RuntimeException(""));
        member.setName(memberDTO.getName());
        member.setAge(memberDTO.getAge());
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        memberService.deleteCacheData(memberDTO.getId());
        return new MemberDTO(memberRepository.save(member));
    }

}
