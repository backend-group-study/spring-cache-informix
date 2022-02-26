package com.study.spring.app.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String name;
    private Integer age;

    @Builder
    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
    }

    public Member toEntity() {
        return Member.builder()
                .age(this.age)
                .name(this.name)
                .build();
    }

}
