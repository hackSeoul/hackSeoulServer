package com.hackseoul.util;

import com.hackseoul.apiPayload.code.status.ErrorStatus;
import com.hackseoul.apiPayload.exception.handler.MemberHandler;
import com.hackseoul.domain.Member;

public class MemberUtils {


    /**
     * 두 회원이 서로 다른 회원인지 검증 및 검증 실패 시 에러 처리
     *
     * @param memberId1
     * @param memberId2
     * @param errorStatus
     */
    public static void validateDifferentMembers(Long memberId1, Long memberId2,
        ErrorStatus errorStatus) {
        if (memberId1.equals(memberId2)) {
            throw new MemberHandler(errorStatus);
        }
    }


}
