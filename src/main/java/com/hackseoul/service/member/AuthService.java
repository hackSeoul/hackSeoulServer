package com.hackseoul.service.member;

import com.hackseoul.apiPayload.code.status.ErrorStatus;
import com.hackseoul.apiPayload.exception.handler.MemberHandler;
import com.hackseoul.domain.Member;
import com.hackseoul.dto.member.MemberResponse;
import com.hackseoul.repository.member.MemberRepository;
import com.hackseoul.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;

    /**
     * 회원가입 : Riot 연동 포함
     *
     * @param email
     * @param password
     * @return
     */
    @Transactional
    public Member joinMember(String email, String password) {

        // 중복 확인하기
        if (memberRepository.existsByEmail(email)) {
            throw new MemberHandler(ErrorStatus.MEMBER_CONFLICT);
        }
        // DB 저장
        // 1. Riot 정보 제외 저장
        int randomProfileImage = ThreadLocalRandom.current().nextInt(1, 9);
        Member member = Member.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .profileImage(randomProfileImage)
                .build();


        memberRepository.save(member);
        return member;
    }

    /**
     * jwt refresh 토큰 검증
     *
     * @param refresh_token
     * @return
     */
    @Transactional
    public MemberResponse.RefreshTokenResponseDTO verifyRefreshToken(String refresh_token) {
        // refresh Token 검증하기
        Member member = memberRepository.findByRefreshToken(refresh_token)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.INVALID_TOKEN));

        // refresh 토큰에서 id 가져오기
        Long id = member.getId();

        // 토큰 생성하기
        String access_token = jwtUtil.createJwtWithId(id, 60 * 60 * 1000L);         // 1시간
        String new_refresh_token = jwtUtil.createJwt(60 * 60 * 24 * 30 * 1000L);    // 30일

        // refresh token 저장하기
        member.updateRefreshToken(new_refresh_token);
        memberRepository.save(member);

        return new MemberResponse.RefreshTokenResponseDTO(access_token, new_refresh_token);
    }


}
