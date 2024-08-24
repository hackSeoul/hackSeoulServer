package com.hackseoul.service.member;

import com.hackseoul.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입 : Riot 연동 포함
     *
     * @param email
     * @param password
     * @return
     */
    @Transactional
    public void joinMember(String email, String password) {

        return;
    }

}
