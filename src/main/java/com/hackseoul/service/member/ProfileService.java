package com.hackseoul.service.member;

import com.hackseoul.apiPayload.code.status.ErrorStatus;
import com.hackseoul.apiPayload.exception.handler.MemberHandler;
import com.hackseoul.domain.Member;
import com.hackseoul.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final MemberRepository memberRepository;

    /**
     * 회원 정보 조회
     *
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
