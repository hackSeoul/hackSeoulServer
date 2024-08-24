package com.hackseoul.converter;

import com.hackseoul.domain.Member;
import com.hackseoul.dto.member.MemberResponse;
public class MemberConverter {

    public static MemberResponse.myProfileMemberDTO toMyProfileDTO(Member member) {

        return MemberResponse.myProfileMemberDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .profileImg(member.getProfileImage())
                .updatedAt(String.valueOf(member.getUpdatedAt()))
                .build();

    }


}
