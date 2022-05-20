package com.Planner.entity;

import com.Planner.constant.Role;
import com.Planner.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Setter
@Getter
public class Member {
    @Id //기본키
    @Column(unique = true)
    private String id;

    private String name;

    private String password;

    private String nickNm;

    @Column(unique=true) //중복 없음
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member=new Member();
        member.setName(member.getName());
        member.setId(member.getId());
        member.setNickNm(member.getNickNm());
        member.setEmail(member.getEmail());
        //passwordEncoder.encode->암호화
        String password= passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }


}
