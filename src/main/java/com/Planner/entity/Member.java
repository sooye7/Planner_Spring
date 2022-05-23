package com.Planner.entity;

import com.Planner.constant.Role;
import com.Planner.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

    @Id  // 기본키
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)  // 중복 없음
    private String email;

    private String password;

    private String nickNm;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member=new Member();
        member.setEmail(memberFormDto.getEmail());
        member.setNickNm(memberFormDto.getNickNm());
        //passwordEncoder.encode->암호화
        String password=passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);  // 처음에 USER로
        return member;
    }

}
