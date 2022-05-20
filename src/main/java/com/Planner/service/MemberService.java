package com.Planner.service;

import com.Planner.entity.Member;
import com.Planner.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  // 트랜잭션 설정 :성공하면 그대로 적용, 실패하면 롤백
@RequiredArgsConstructor  // final 또는 @Nonnull 명령어가 붙으면 객체를 자동으로 붙임
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    //Controller -> Service
    public Member saveMember(Member member){
        validateDuplicateMember(member);  //.같은 이메일 있는지 확인
        return memberRepository.save(member);  //DB에 저장
    }

    private void validateDuplicateMember(Member member){
        Member findMember=memberRepository.findById(member.getId());
        if(findMember!=null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member=memberRepository.findById(id);

        if(member==null){
            throw new UsernameNotFoundException(id);
        }
        return User.builder().username(member.getId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
