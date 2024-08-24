package com.hackseoul.security;

import com.hackseoul.apiPayload.exception.handler.CustomUserException;
import com.hackseoul.domain.Member;
import com.hackseoul.repository.member.MemberRepository;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 로그인 DB 로직
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return memberRepository.findByEmail(email)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new JwtException("No Member"));
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new JwtException("No Member"));
        return new CustomUserDetails(member);
    }


}

